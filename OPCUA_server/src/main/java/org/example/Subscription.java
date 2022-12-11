package org.example;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Subscription {

    public static void main(String[] args) {
        try
        {

            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();


            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/
            for (int i = 0; i < endpoints.size(); i++) {
                if(endpoints.get(i).getSecurityMode().name().equals("None")){
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }
            OpcUaClient client = OpcUaClient.create(cfg.build());
            client.connect().get();

            //total amount
            nodeCreation(client,"::Program:Cube.Admin.ProdProcessedCount");
            //defect amount
            nodeCreation(client,"::Program:Cube.Admin.ProdDefectiveCount");
            //delete this
            nodeCreation(client,"::Program:Cube.Command.MachSpeed");
            //temperature
            nodeCreation(client,"::Program:Cube.Status.Parameter[3].Value");
            //humidity
            nodeCreation(client,"::Program:Cube.Status.Parameter[2].Value");
            // vibration
            nodeCreation(client,"::Program:Cube.Status.Parameter[4].Value");
            //batch id

            // let the example run forever
            while(true)
            {
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // remake this method, so that it redirects the values and POSTs to our website.
    private static void onSubscriptionValue(UaMonitoredItem item, DataValue value) {

        // read  to get batch id
        // System.out.println("subscription value received: item="+ item.getReadValueId().getNodeId().getIdentifier() + ", value=" + value.getValue().getValue());
//
        String itemName = (String) item.getReadValueId().getNodeId().getIdentifier();
        System.out.println(itemName);
        try {
            switch (itemName) {
                case "::Program:Cube.Admin.ProdProcessedCount" -> TestJSON.sendPOST("producedAmount", value.getValue().getValue(), 1);
                case "::Program:Cube.Admin.ProdDefectiveCount" -> TestJSON.sendPOST("defective", value.getValue().getValue(), 1);
                case "::Program:Cube.Command.MachSpeed" -> TestJSON.sendPOST("speed", (Float) value.getValue().getValue(), 1);
                case "::Program:Cube.Status.Parameter[3].Value" -> TestJSON.sendPOST("temperature", value.getValue().getValue(), 1);
                case "::Program:Cube.Status.Parameter[2].Value" -> TestJSON.sendPOST("humidity", value.getValue().getValue(), 1);
                case "::Program:Cube.Status.Parameter[4].Value" -> TestJSON.sendPOST("vibration", value.getValue().getValue(), 1);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void nodeCreation(OpcUaClient client, String identifier) throws ExecutionException, InterruptedException {
        NodeId humidity  = new NodeId(6, identifier);
        ReadValueId read = new ReadValueId(humidity, AttributeId.Value.uid(), null, null);

        UaSubscription subscription = client.getSubscriptionManager().createSubscription(1000.0).get();

        UInteger clientHandle = subscription.getSubscriptionId();
        MonitoringParameters parameters = new MonitoringParameters(
                clientHandle,
                1000.0,     // sampling interval
                null,       // filter, null means use default
                Unsigned.uint(10),   // queue size
                true        // discard oldest
        );
        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(read, MonitoringMode.Reporting, parameters);
        UaSubscription.ItemCreationCallback onItemCreated =  (item, id) -> item.setValueConsumer(Subscription::onSubscriptionValue);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(TimestampsToReturn.Both, Arrays.asList(request), onItemCreated).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                System.out.println("item created for nodeId=" + item.getReadValueId().getNodeId());
            } else{
                System.out.println("failed to create item for nodeId=" + item.getReadValueId().getNodeId() + " (status=" + item.getStatusCode() + ")");
            }
        }
    }
}