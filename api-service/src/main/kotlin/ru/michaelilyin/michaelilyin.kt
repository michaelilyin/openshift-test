package ru.michaelilyin

import javax.ws.rs.core.Response

fun <T> Response.use(action: (Response) -> T): T {
    try {
        return action(this)
    } finally {
        this.close()
    }
}
