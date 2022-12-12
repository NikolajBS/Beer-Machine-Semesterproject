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
import java.util.concurrent.ExecutionException;


import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;


public class Write {

    static OpcUaClient client;

    public static void main(String[] args) {
            try {
                List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();
                OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();

                for (int i = 0; i < endpoints.size(); i++) {
                        if (endpoints.get(i).getSecurityMode().name().equals("None")) {
                            EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                            cfg.setEndpoint(configPoint);
                            break;
                        }
                    }

                    client = OpcUaClient.create(cfg.build());
                    client.connect().get();

                    /* myVariable endpoint */
                    //NodeId nodeId  = NodeId.parse("ns=2;i=1002);

                } catch (UaException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

                //datatype hvis float skal castes som (float)value
                //datatype hvis det er enten en boolean eller en int skal den IKKE CASTES!

    public void sendCommand(int buttonId){
        //Control
        NodeId nodeControl = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(buttonId)));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Execute command
        NodeId nodeSend = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void beerParameters(float speed, float batchId, float beerId, float amountBeer){
        //Speed
        NodeId nodeSpeed  = new NodeId(6, "::Program:Cube.Command.MachSpeed");
        //BatchID
        NodeId nodePara0 = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
        //BeerId
        NodeId nodePara1  = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
        //AmountBeer
        NodeId nodePara2  = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");

        client.writeValue(nodeSpeed, DataValue.valueOnly(new Variant((float)speed)));
        client.writeValue(nodePara0, DataValue.valueOnly(new Variant((float)batchId)));
        client.writeValue(nodePara1, DataValue.valueOnly(new Variant((float)beerId)));
        client.writeValue(nodePara2, DataValue.valueOnly(new Variant((float)amountBeer)));
    }
}
