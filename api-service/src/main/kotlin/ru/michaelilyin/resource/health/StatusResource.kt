package ru.michaelilyin.resource.health

import ru.michaelilyin.use
import javax.ejb.Stateless
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
@Stateless
@Produces(MediaType.APPLICATION_JSON)
open class StatusResource {

    private val client = ClientBuilder.newClient()
    private val url = System.getenv("source_service")

    @GET
    @Path("/live")
    fun getLive(): Response {
        return Response.ok().build()
    }

    @GET
    @Path("/ready")
    fun getReady(): Response {
        val sourceResponse = client.target("$url/api/health").request().get()
        return sourceResponse.use {
            if (sourceResponse.status != 200)
                Response.status(503).build()
            else
                Response.ok().build()
        }
    }

}
