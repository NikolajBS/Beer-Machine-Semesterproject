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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;


public class ReadExample {

    public static void main(String[] args) {
        try
        {
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();

            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/

            for (int i = 0; i < endpoints.size(); i++) {
                if(endpoints.get(i).getSecurityMode().name().equals("Basic128Rsa15")){
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }

            //OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();
            //cfg.setEndpoint(endpoints.get(1));
            //EndpointDescription configPoint = EndpointUtil.updateUrl("127.0.0.1", 4840);
            // Configuring user and password connection
            OpcUaClientConfig config = cfg.setIdentityProvider(getIdentityProvider()).build();

            OpcUaClient client = OpcUaClient.create(config);
            client.connect().get();


            /* Random endpoint */
            //NodeId nodeId  = NodeId.parse("ns=2;i=1002");
            NodeId nodeId  = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");

            /* unwrapping the final value */
            DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
            System.out.println("DataValue= " + dataValue);

            Variant variant = dataValue.getValue();
            System.out.println("Variant= " + variant);

            int myVariable = (int)variant.getValue();
            System.out.println("myVariable= " + myVariable);



        }
        catch(Throwable ex)
        {
            System.out.println("*****************************************");
            ex.printStackTrace();
        }

    }
    public static IdentityProvider getIdentityProvider() {
        return new UsernameProvider("sdu", "1234");
    }

}
