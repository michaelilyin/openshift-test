package ru.michaelilyin.resource.demo

import ru.michaelilyin.use
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
open class DemoApiResource {

    private val client = ClientBuilder.newClient()
    private val url = System.getenv("source_service")

    @GET
    open fun getData(): String {
        val sourceResponse = client.target("$url/api/source").request().get()
        return sourceResponse.use {
            val response = it.readEntity(String::class.java)
            "external service [$url] get the following data from source service: [$response]"
        }
    }
}
