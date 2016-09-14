package uk.co.jambirch.jersey.controller;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import uk.co.jambirch.jersey.model.Category;
import uk.co.jambirch.jersey.model.CategoryAllocation;
import uk.co.jambirch.jersey.model.Cycle;
import uk.co.jambirch.jersey.model.PromotionalSpace;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    private final DynamoDBMapper mapper;

    public MyResource() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        client.setEndpoint("http://localhost:9020");
        mapper = new DynamoDBMapper(client);
    }

    @GET
    @Path("cycle/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cycle getCycle(@PathParam("name") String name) {
        Cycle out = new Cycle();
        out.setName(name);

        DynamoDBQueryExpression<Cycle> queryExpression = new DynamoDBQueryExpression<Cycle>()
                .withHashKeyValues(out);

        List<Cycle> cycles = mapper.query(Cycle.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }

    @GET
    @Path("category/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("name") String name) {
        Category out = new Category();
        out.setName(name);

        DynamoDBQueryExpression<Category> queryExpression = new DynamoDBQueryExpression<Category>()
                .withHashKeyValues(out);

        List<Category> cycles = mapper.query(Category.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }

    @GET
    @Path("promotionalSpace/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public PromotionalSpace getPromotionalSpace(@PathParam("name") String name) {
        PromotionalSpace out = new PromotionalSpace();
        out.setName(name);

        DynamoDBQueryExpression<PromotionalSpace> queryExpression = new DynamoDBQueryExpression<PromotionalSpace>()
                .withHashKeyValues(out);

        List<PromotionalSpace> cycles = mapper.query(PromotionalSpace.class, queryExpression);
        if (cycles.size() != 1) {
            System.out.println("returned = " + cycles.size());
            return null;
        } else {
            return cycles.get(0);
        }
    }

    @GET
    @Path("categoryAllocation/{cycle}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryAllocation> getCategoryAllocation(@PathParam("cycle") String cycle) {
        CategoryAllocation out = new CategoryAllocation();
        out.setCycleName(cycle);

        DynamoDBQueryExpression<CategoryAllocation> queryExpression = new DynamoDBQueryExpression<CategoryAllocation>()
                .withHashKeyValues(out);

        return  mapper.query(CategoryAllocation.class, queryExpression);
    }
}
