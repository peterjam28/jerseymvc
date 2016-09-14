package uk.co.jambirch.jersey.controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.*;
import uk.co.jambirch.jersey.Main;
import uk.co.jambirch.jersey.model.Category;
import uk.co.jambirch.jersey.model.CategoryAllocation;
import uk.co.jambirch.jersey.model.Cycle;
import uk.co.jambirch.jersey.model.PromotionalSpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private static HttpServer server;
    private static WebTarget target;

    private static Cycle testCycle;
    private static Category testCategory;
    private static PromotionalSpace testPromotionalSpace;
    private static CategoryAllocation testCategoryAllocation;

    private static final Class[] testTables = {Cycle.class, Category.class, PromotionalSpace.class, CategoryAllocation.class};

    @BeforeClass
    public static void setUp() throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:9020");
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        List<String> tables = client.listTables().getTableNames();
        for (Class table : testTables) {
            if (tables.contains(table.getSimpleName())) {
                client.deleteTable(table.getSimpleName());
            }

            CreateTableRequest req = mapper.generateCreateTableRequest(table);
            req.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
            client.createTable(req);

        }

        testCycle = new Cycle("Xmas 2017");
        testCycle.setStartDate(123);
        mapper.save(testCycle);

        testCategory = new Category("Bakery");
        mapper.save(testCategory);

        testPromotionalSpace = new PromotionalSpace("plinth1");
        mapper.save(testPromotionalSpace);

        testCategoryAllocation = new CategoryAllocation("catAlloc1");
        testCategoryAllocation.setCycleName(testCycle.getName());
        testCategoryAllocation.setCategoryName(testCategory.getName());
        testCategoryAllocation.setPromotionalSpaceName(testPromotionalSpace.getName());
        mapper.save(testCategoryAllocation);


        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetCycle() {
        Cycle responseMsg = target.path("myresource/cycle/Xmas 2017").request().get(Cycle.class);
        assertEquals(testCycle, responseMsg);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetCategory() {
        Category responseMsg = target.path("myresource/category/Bakery").request().get(Category.class);
        assertEquals(testCategory, responseMsg);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetPromotionalSpace() {
        PromotionalSpace responseMsg =
                target.path("myresource/promotionalSpace/plinth1").request().get(PromotionalSpace.class);
        assertEquals(testPromotionalSpace, responseMsg);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetCategoryAllocation() {
        List responseMsg = target.path("myresource/categoryAllocation/Xmas 2017").request().get(List.class);
        assertEquals(1, responseMsg.size());
        Object alloc = responseMsg.get(0);
        ObjectMapper mapper = new ObjectMapper();
        CategoryAllocation alloc2 = mapper.convertValue(alloc, CategoryAllocation.class);
        System.out.println("alloc2 = " + alloc2.getClass().getName());
        assertEquals(testCategoryAllocation, alloc2);
    }
}
