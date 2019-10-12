package name.denyago.commenttree.api

import com.beust.klaxon.Klaxon
import io.github.rybalkinsd.kohttp.dsl.async.httpGetAsync
import io.github.rybalkinsd.kohttp.ext.url
import name.denyago.commenttree.Logable

data class ToDoItem(val userId: Int, val id: Int, val title: String, val completed: Boolean) {
    constructor(id: Int, title: String) : this(0, id, title, false)
}

interface ToDoApiable {
    suspend fun getToDoItem(id: Int): ToDoItem?
}

class ToDoApi(private val url: String, private val logger: Logable) : ToDoApiable {
    override suspend fun getToDoItem(id: Int): ToDoItem? {
        val body = rawToDoItem(id).orEmpty()
        return Klaxon().parse<ToDoItem>(body)
    }

    suspend fun rawToDoItem(id: Int): String? {
        return httpGetAsync {
            url(url + id.toString())
        }.also {
            logger.log("[ $id ] Calling $url}")
        }.await().use { response ->
            response.body()?.string()
        }.also {
            logger.log("[ $id ] got $it")
        }
    }
}
