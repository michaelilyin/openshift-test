package ru.michaelilyin.resource.demo

import javax.ejb.Stateless
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

@Stateless
@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
open class DemoApiResource {

    private val client = ClientBuilder.newClient()
    private val url = System.getenv("source_service")

    @GET
    fun getData(): String {
        val sourceResponse = client.target("$url/api/source").request().get()
        try {
            val response = sourceResponse.readEntity(String::class.java)
            return "external service [$url] get the following data from source service: [$response]"
        } finally {
            sourceResponse.close()
        }
    }
}
