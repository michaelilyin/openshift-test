package ru.michaelilyin.resource.health

import ru.michaelilyin.use
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 17.02.2017.
 */
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
open class StatusResource {

    private val client = ClientBuilder.newClient()
    private val url = System.getenv("source_service")

    @GET
    @Path("/live")
    open fun getLive(): Response {
        return Response.ok().build()
    }

    @GET
    @Path("/ready")
    open fun getReady(): Response {
        try {
            val sourceResponse = client.target("$url/api/ready").request().get()
            return sourceResponse.use {
                if (sourceResponse.status != 200)
                    Response.status(503).build()
                else
                    Response.ok().build()
            }
        } catch (e: Exception) {
            return Response.status(503).build()
        }
    }

}
