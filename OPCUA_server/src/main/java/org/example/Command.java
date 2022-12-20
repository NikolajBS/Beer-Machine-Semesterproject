package org.example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aqs
 */

import java.util.List;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;


public final class Command {
    private static Command instance;
    private static OpcUaClient client;

    private Command(){
        try
        {
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();

            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/
            for (int i = 0; i < endpoints.size(); i++) {
                if(endpoints.get(i).getSecurityMode().name().equals("None")){ //None or SignAndEncrypt
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }

           client = OpcUaClient.create(cfg.build());
            client.connect().get();
        }
        catch(Throwable ex)
        {
            ex.printStackTrace();
        }
    }
    public static Command getInstance()
    {
        if(instance == null)
        {
            instance = new Command();
        }
        return instance;
    }
    public void beerParameters(float batchId, float beerId,float speed, float amountBeer){
        NodeId machineSpeed  = new NodeId(6, "::Program:Cube.Command.MachSpeed");
        //BatchID
        NodeId id = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
        //BeerId
        NodeId productId  = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
        //AmountBeer
        NodeId amount  = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");

        client.writeValue(machineSpeed, DataValue.valueOnly(new Variant(speed)));
        client.writeValue(id, DataValue.valueOnly(new Variant(batchId)));
        client.writeValue(productId, DataValue.valueOnly(new Variant(beerId)));
        client.writeValue(amount, DataValue.valueOnly(new Variant(amountBeer)));
    }
    public void sendCommand(int command){
        //Control
        NodeId nodeControl = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(command)));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Execute command
        NodeId nodeSend = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }


    public static IdentityProvider getIdentityProvider() {
        return new UsernameProvider("sdu", "1234");
    }

}
