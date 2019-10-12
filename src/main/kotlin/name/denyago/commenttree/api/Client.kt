package name.denyago.commenttree.api

import com.beust.klaxon.Klaxon
import io.github.rybalkinsd.kohttp.ext.httpGetAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import name.denyago.commenttree.Logable
import name.denyago.commenttree.data.Comment
import org.koin.core.KoinComponent
import org.koin.core.inject

interface Clientable {
    fun getComments(ids: List<Int>): Map<Int, Comment>
}

class Client(private val url: String) : Clientable, KoinComponent {
    data class ToDoItem(val userId: Int, val id: Int, val title: String, val completed: Boolean)

    private val logger by inject<Logable>()

    override fun getComments(ids: List<Int>): Map<Int, Comment> =
        runBlocking(Dispatchers.Default) {
            ids.map { id ->
                async {
                    logger.log("[ $id ] Calling ${(url + "$id")}")
                    (url + "$id").httpGetAsync().await().use { response ->
                        val body = response.body()?.string()
                        Klaxon().parse<ToDoItem>(body ?: "").also { item ->
                            logger.log("[ $id ] got $item")
                        }
                    }
                }
            }.awaitAll().filterNotNull().fold(mutableMapOf<Int, Comment>()) { acc, item ->
                acc[item.id] = Comment(item.id, item.title)
                acc
            }
        }
}
