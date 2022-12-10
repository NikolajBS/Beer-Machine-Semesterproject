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
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
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

        //Speed
        NodeId nodeSpeed  = new NodeId(6, "::Program:Cube.Command.MachSpeed");
        // client.writeValue(nodeSpeed, DataValue.valueOnly(new Variant((float)600.0)));

        //Parameters
        NodeId nodePara0 = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
        NodeId nodePara1  = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
        NodeId nodePara2  = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");
        // client.writeValue(nodePara0, DataValue.valueOnly(new Variant((float)10.0)));
        //  client.writeValue(nodePara2, DataValue.valueOnly(new Variant((float)200.0)));


        //Control
        NodeId nodeControl  = new NodeId(6, "::Program:Cube.Command.CntrlCmd");
        //client.writeValue(nodeControl, DataValue.valueOnly(new Variant(2)));
        NodeId nodeSend  = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");
        //client.writeValue(nodeSend, DataValue.valueOnly(new Variant(true)));


    }


}
