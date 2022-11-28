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

import org.bouncycastle.jcajce.provider.keystore.BCFKS;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
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

    OpcUaClient client;

    public static void main(String[] args) {

        ReadExample server = new ReadExample();

        server.connectServer();

        //ADMIN CALLS

        System.out.println(server.getNode("::Program:Cube.Admin.ProdProcessedCount"));
        System.out.println(server.getNode("::Program:Cube.Admin.ProdDefectiveCount"));

        System.out.println(server.getNode("::Program:Cube.Admin.StopReason.ID"));
        System.out.println(server.getNode("::Program:Cube.Admin.StopReason.Value"));
        System.out.println(server.getNode("::Program:Cube.Admin.Parameter[0].Value"));

        //STATUS CALLS
        System.out.println(server.getNode("::Program:Cube.Status.StateCurrent"));
        System.out.println(server.getNode("::Program:Cube.Status.MachSpeed"));
        System.out.println(server.getNode("::Program:Cube.Status.CurMachSpeed"));
        System.out.println(server.getNode("::Program:Cube.Status.Parameter[0].Value"));
        System.out.println(server.getNode("::Program:Cube.Status.Parameter[1].Value"));
        System.out.println(server.getNode("::Program:Cube.Status.Parameter[2].Value"));
        System.out.println(server.getNode("::Program:Cube.Status.Parameter[3].Value"));
        System.out.println(server.getNode("::Program:Cube.Status.Parameter[4].Value"));

        //COMMAND CALLS
        System.out.println(server.getNode("::Program:Cube.Command.MachSpeed"));
        System.out.println(server.getNode("::Program:Cube.Command.CntrlCmd"));
        System.out.println(server.getNode("::Program:Cube.Command.CmdChangeRequest"));
        System.out.println(server.getNode("::Program:Cube.Command.Parameter[0].Value"));
        System.out.println(server.getNode("::Program:Cube.Command.Parameter[1].Value"));
        System.out.println(server.getNode("::Program:Cube.Command.Parameter[2].Value"));

        System.out.println("prut");


        /* Random endpoint */
        //NodeId nodeId  = NodeId.parse("ns=2;i=1002");

        //NodeId nodeId = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");

        /* unwrapping the final value */
        //DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
        //System.out.println("DataValue= " + dataValue);

        //Variant variant = dataValue.getValue();
        //System.out.println("Variant= " + variant);

        //int myVariable = (int) variant.getValue();
        //System.out.println("myVariable= " + myVariable);

    }



    public void connectServer() {
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
    }

    public float getNode(String name) {
        try {
            NodeId nodeId = new NodeId(6, name);
            DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
            Variant variant = dataValue.getValue();
            return (float) variant.getValue();
        } catch (Exception e) { //java.lang.InterruptedException, java.util.concurrent.ExecutionException
            System.out.println("lol");
        }
        return 0;
    }



    public static IdentityProvider getIdentityProvider() {
        return new UsernameProvider("sdu", "1234");
    }
}
