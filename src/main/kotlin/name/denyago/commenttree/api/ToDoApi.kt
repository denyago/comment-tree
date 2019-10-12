package name.denyago.commenttree.api

import com.beust.klaxon.Klaxon
import io.github.rybalkinsd.kohttp.dsl.async.httpGetAsync
import io.github.rybalkinsd.kohttp.ext.url
import name.denyago.commenttree.Logable
import okhttp3.OkHttpClient

data class ToDoItem(val id: Int, val title: String)

interface ToDoApiable {
    suspend fun getToDoItem(id: Int): ToDoItem?
}

class ToDoApi(
    private val url: String,
    private val logger: Logable,
    private val httpClient: OkHttpClient
) : ToDoApiable {
    override suspend fun getToDoItem(id: Int): ToDoItem? {
        val body = rawToDoItem(id)
        return if (body.isEmpty()) null else Klaxon().parse<ToDoItem>(body)
    }

    private suspend fun rawToDoItem(id: Int): String {
        return httpGetAsync(httpClient) {
            url(url + id.toString())
        }.also {
            logger.log("[ $id ] Calling $url$id}")
        }.await().use { response ->
            response.body()?.string().orEmpty()
        }.also {
            logger.log("[ $id ] got $it")
        }
    }
}
