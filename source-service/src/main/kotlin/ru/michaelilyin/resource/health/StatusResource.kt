package ru.michaelilyin.resource.health

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 17.02.2017.
 */
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
open class StatusResource {

    @GET
    @Path("/live")
    open fun getLive(): Response {
        return Response.ok().build()
    }

    @GET
    @Path("/ready")
    open fun getReady(): Response {
        return Response.ok().build()
    }
}
