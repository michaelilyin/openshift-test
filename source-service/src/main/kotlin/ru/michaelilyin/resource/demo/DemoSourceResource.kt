package ru.michaelilyin.resource.demo

import javax.ejb.Stateless
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 19.02.2017.
 */
@Stateless
@Path("/source")
@Produces(MediaType.APPLICATION_JSON)
open class DemoSourceResource {

    @GET
    fun getData(): String {
        return "data from source service";
    }

}