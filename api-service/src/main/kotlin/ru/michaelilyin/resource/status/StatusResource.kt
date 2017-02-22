package ru.michaelilyin.resource.status

import javax.ejb.Stateless
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * TODO: javadoc
 * Created by Michael Ilyin on 17.02.2017.
 */
@Stateless
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
open class StatusResource {

    @GET
    fun getStatus(): String {
        return "OK"
    }

}