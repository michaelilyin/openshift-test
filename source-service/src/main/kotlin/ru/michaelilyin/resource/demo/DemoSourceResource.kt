package ru.michaelilyin.resource.demo

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 19.02.2017.
 */
@Path("/source")
@Produces(MediaType.APPLICATION_JSON)
open class DemoSourceResource {

    private val id = System.getenv()

    @GET
    open fun getData(): String {
        return "source service environment: ${id.entries}";
    }

}
