package org.example;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.*;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Subscription {

    private static Connection conn;
    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/beers", "root", "secret");
            List<EndpointDescription> endpoints = DiscoveryClient.getEndpoints("opc.tcp://127.0.0.1:4840").get();

            OpcUaClientConfigBuilder cfg = new OpcUaClientConfigBuilder();

            /*Selecting the endpoint connection with Security Mode/Security Policy == "None"*/
            for (int i = 0; i < endpoints.size(); i++) {
                if (endpoints.get(i).getSecurityMode().name().equals("None")) {
                    EndpointDescription configPoint = EndpointUtil.updateUrl(endpoints.get(i), "127.0.0.1", 4840);
                    cfg.setEndpoint(configPoint);
                    break;
                }
            }

            OpcUaClient client = OpcUaClient.create(cfg.build());
            client.connect().get();

            String[] nodes = {"::Program:Cube.Admin.ProdProcessedCount","::Program:Cube.Admin.ProdDefectiveCount",
                    "::Program:Cube.Status.Parameter[3].Value","::Program:Cube.Status.Parameter[2].Value",
                    "::Program:Cube.Status.Parameter[4].Value","::Program:Inventory.Barley",
                    "::Program:Inventory.Hops","::Program:Inventory.Malt","::Program:Inventory.Wheat",
                    "::Program:Inventory.Yeast","::Program:Maintenance.Counter"};
            for (String node : nodes
            ) {
                NodeId nodeId = new NodeId(6, node);
                ReadValueId readValueId = new ReadValueId(nodeId, AttributeId.Value.uid(), null, null);

                UaSubscription subscription = client.getSubscriptionManager().createSubscription(1000.0).get();
                UInteger totalAmount = subscription.getSubscriptionId();
                double interval;
                UInteger num;
                if(node.equals("::Program:Cube.Admin.ProdProcessedCount")){
                    interval=200.0;
                    num = Unsigned.uint(5);
                }
                else{
                    interval=10000.0;
                    num = Unsigned.uint(10);
                }
                MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(readValueId,
                        MonitoringMode.Reporting, new MonitoringParameters(
                        totalAmount,
                        interval,     // sampling interval
                        null,       // filter, null means use default
                        num,   // queue size
                        true        // discard oldest
                ));
                UaSubscription.ItemCreationCallback onItemCreated = (item, id) -> item.setValueConsumer(Subscription::onSubscriptionValue);

                List<UaMonitoredItem> items = subscription.createMonitoredItems(TimestampsToReturn.Both, Arrays.asList(request), onItemCreated).get();

                for (UaMonitoredItem item : items) {
                    if (item.getStatusCode().isGood()) {
                        System.out.println("item created for nodeId=" + item.getReadValueId().getNodeId());
                    } else {
                        System.out.println("failed to create item for nodeId=" + item.getReadValueId().getNodeId() + " (status=" + item.getStatusCode() + ")");
                    }
                }
            }
        } catch (UaException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true){
        }
    }
        // remake this method, so that it redirects the values and POSTs to our website.
    private static void onSubscriptionValue(UaMonitoredItem item, DataValue value) {

        String itemName = (String) item.getReadValueId().getNodeId().getIdentifier();
        createEntry(itemName,value.getValue().getValue());
//        try {
//            Server.sendPOST(itemName, value.getValue().getValue());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private static void createEntry(String name, Object val){
        String sql = null;
        switch (name){
            case "::Program:Cube.Admin.ProdProcessedCount":
                sql = "UPDATE batches SET producedAmount = ?,acceptedAmount = (producedAmount-defectAmount) WHERE id = (SELECT MAX(id) from (select id from batches) as subquery)";
                break;
            case "::Program:Cube.Status.Parameter[3].Value":
                sql = "INSERT INTO temperatures (temperature,batch_id) VALUES(?,(SELECT MAX(id) from (select id from batches) as subquery))";
                break;
            case "::Program:Cube.Admin.ProdDefectiveCount":
                sql = "UPDATE batches SET defectAmount = ? WHERE id = (SELECT MAX(id) from (select id from batches) as subquery)";
                break;
            case "::Program:Cube.Status.Parameter[2].Value":
                sql = "INSERT INTO humidities (humidity,batch_id) VALUES(?,(SELECT MAX(id) from (select id from batches) as subquery))";
                break;
            case "::Program:Cube.Status.Parameter[4].Value":
                sql = "INSERT INTO vibrations (vibration,batch_id) VALUES(?,(SELECT MAX(id) from (select id from batches) as subquery))";
                break;
            case "::Program:Inventory.Barley":
                sql = "UPDATE inventories SET barley = ? WHERE id=1";
                break;
            case "::Program:Inventory.Hops":
                sql = "UPDATE inventories SET hops = ? WHERE id=1";
                break;
            case "::Program:Inventory.Malt":
                sql = "UPDATE inventories SET malt = ? WHERE id=1";
                break;
            case "::Program:Inventory.Wheat":
                sql = "UPDATE inventories SET wheat = ? WHERE id=1";
                break;
            case "::Program:Inventory.Yeast":
                sql = "UPDATE inventories SET yeast = ? WHERE id=1";
                break;
            case "::Program:Maintenance.Counter":
                sql = "UPDATE inventories SET maintenance = ? WHERE id=1";
                break;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1,val);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
