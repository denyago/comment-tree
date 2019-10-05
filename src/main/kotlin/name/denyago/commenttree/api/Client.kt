package name.denyago.commenttree.api

import com.beust.klaxon.Klaxon
import io.github.rybalkinsd.kohttp.ext.httpGetAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.log

class Client(val url: String) {
    data class ToDoItem(val userId: Int, val id: Int, val title: String, val completed: Boolean)

    fun getComments(ids: List<Int>): Map<Int, Comment> =
        runBlocking(Dispatchers.Default) {
            ids.map {
                async {
                    val id = it
                    log("[ $id ] Calling ${(url + "$id")}")
                    (url + "$id").httpGetAsync().await().use {
                        val body = it.body()?.string()
                        Klaxon().parse<ToDoItem>(body ?: "").also {
                            log("[ $id ] got $it")
                        }
                    }
                }
            }.awaitAll().filterNotNull().fold(mutableMapOf<Int, Comment>()) { acc, item ->
                acc[item.id] = Comment(item.id, item.title)
                acc
            }
        }
}

//suspend fun getComment(id: Int, tryNo: Int = 0) {
//    val result = ...${id}"
//        .httpGet()
//        .responseString()
//
//    when (result.third) {
//        is Result.Failure -> {
//            val ex = (result.third as Result.Failure<FuelError>).getException()
//            log(ex.toString())
//            if (tryNo < 3) getComment(id, tryNo + 1)
//        }
//        is Result.Success -> {
//            val data = result.first.responseJson { request: Request, response: Response, parseResult: Result<FuelJson, FuelError> ->
//                when (parseResult) {
//                    is FuelError -> {
//                        val ex = parseResult.exception
//                        log(ex.toString())
//                        if (tryNo < 3) launch { getComment(id, tryNo + 1) }
//                    }
//                    else -> {
//                        val comment = parseResult.get().obj()["title"].toString()
//                        launch { updates.send(Comment(id, comment)) }
//                    }
//                }
//            }
//        }
//    }
//}