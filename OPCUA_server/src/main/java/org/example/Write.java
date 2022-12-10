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
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
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
            //cfg.setIdentityProvider(getIdentityProvider()).build();

            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/
            for (int i = 0; i < endpoints.size(); i++) {
                if (endpoints.get(i).getSecurityMode().name().equals("None")) {
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }

            client = OpcUaClient.create(cfg.build());
            client.connect().get();
        } catch (Throwable ex) {
            //System.out.println("*****************************************");
            ex.printStackTrace();
        }

        //datatype hvis float skal castes som (float)value
        //datatype hvis det er enten en boolean eller en int skal den IKKE CASTES!
    }

    public void resetButton(){
        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(1)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void startButton(){
        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(2)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void stopButton(){
        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(3)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void abortButton(){
        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(4)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void clearButton(){
        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        client.writeValue(nodeControl, DataValue.valueOnly(new Variant(5)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));
    }

    public void changeSpeed(float speed){
        //Speed
        NodeId nodeSpeed  = new NodeId(6, "::Program:Cube.Command.MachSpeed");
        client.writeValue(nodeSpeed, DataValue.valueOnly(new Variant((float)speed)));
    }

    public void setBatchId(float batchId){
        NodeId nodePara0 = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
        client.writeValue(nodePara0, DataValue.valueOnly(new Variant((float)batchId)));
    }

    public void setBeerId(float beerId){
        NodeId nodePara1  = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
        client.writeValue(nodePara1, DataValue.valueOnly(new Variant((float)beerId)));
    }

    public void setAmount(float amountBeer){
        NodeId nodePara2  = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");
        client.writeValue(nodePara2, DataValue.valueOnly(new Variant((float)amountBeer)));
    }


}
