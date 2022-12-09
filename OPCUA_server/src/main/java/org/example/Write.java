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

    public static void main(String[] args) {
        try 
        {
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();
            OpcUaClientConfig config;
            for (int j = 0; j < endpoints.size(); j++) {
                System.out.println("Endpoint "+ j + " is " + endpoints.get(j).getSecurityMode().name());

            }
            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/
            for (int i = 0; i < endpoints.size(); i++) {
                if(endpoints.get(i).getSecurityMode().name().equals("None")){ //None or SignAndEncrypt
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }

            config = cfg.setIdentityProvider(getIdentityProvider()).build();
            OpcUaClient client = OpcUaClient.create(config);
            client.connect().get();

            /* myVariable endpoint */
            //NodeId nodeId  = NodeId.parse("ns=2;i=1002);
            NodeId nodeId  = new NodeId(6, "::Program:Cube.Command.MachSpeed");
            System.out.println(client.writeValue(nodeId, DataValue.valueOnly(new Variant((float)5.0))).get());

        }
        catch(Throwable ex)
        {
            ex.printStackTrace();
        }

    }
    public static IdentityProvider getIdentityProvider() {
        return new UsernameProvider("sdu", "1234");
    }

}
