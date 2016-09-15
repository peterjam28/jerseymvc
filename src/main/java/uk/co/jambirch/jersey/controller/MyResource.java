package uk.co.jambirch.jersey.controller;

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
    public MyResource() {
    }

    @GET
    @Path("cycle/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cycle getCycle(@PathParam("name") String name) {
        Cycle out = new Cycle();
        out.setName(name);

        return out.queryOne();
    }

    @GET
    @Path("category/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("name") String name) {
        Category out = new Category();
        out.setName(name);

        return out.queryOne();
    }

    @GET
    @Path("promotionalSpace/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public PromotionalSpace getPromotionalSpace(@PathParam("name") String name) {
        PromotionalSpace out = new PromotionalSpace();
        out.setName(name);

        return out.queryOne();
    }

    @GET
    @Path("categoryAllocation/{cycle}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryAllocation> getCategoryAllocation(@PathParam("cycle") String cycle) {
        CategoryAllocation out = new CategoryAllocation();
        out.setCycleName(cycle);

        return out.queryAll();
    }
}
