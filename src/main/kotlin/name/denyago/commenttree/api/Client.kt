package name.denyago.commenttree.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import name.denyago.commenttree.data.Comment
import org.koin.core.KoinComponent
import org.koin.core.inject

interface Clientable {
    fun getComments(ids: List<Int>): Map<Int, Comment>
}

class Client : Clientable, KoinComponent {

    private val toDoApi by inject<ToDoApiable>()

    override fun getComments(ids: List<Int>): Map<Int, Comment> =
        runBlocking(Dispatchers.Default) {
            ids
                .map { async { toDoApi.getToDoItem(it) } }.awaitAll().filterNotNull()
                .fold(mutableMapOf<Int, Comment>()) { acc, item ->
                acc[item.id] = Comment(item.id, item.title)
                acc
            }
        }
}
