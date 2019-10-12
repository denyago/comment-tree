package name.denyago.commenttree.api

import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import name.denyago.commenttree.Logger
import name.denyago.commenttree.TestLogger
import okhttp3.mock.HttpCode.HTTP_404_NOT_FOUND
import okhttp3.mock.MediaTypes.MEDIATYPE_JSON
import okhttp3.mock.MockInterceptor
import okhttp3.mock.eq
import okhttp3.mock.rule
import okhttp3.mock.url

class ToDoApiTest : DescribeSpec() {

    init {
        describe("getToDoItem") {
            val testLogger = Logger(object : TestLogger() {})

            context("when the response is OK") {
                val rawJsonBody = """
                  {
                    "userId":1,
                    "id":1,
                    "title":"delectus aut autem",
                    "completed":false
                  }
                  """.trimIndent()
                val interceptor = MockInterceptor().apply {
                    rule(url eq "http://localhost/1") {
                        respond(rawJsonBody, MEDIATYPE_JSON)
                    }
                }
                val httpClient = defaultHttpClient
                    .newBuilder()
                    .addInterceptor(interceptor)
                    .build()

                it("returns a ToDoItem") {
                    ToDoApi("http://localhost/", testLogger, httpClient)
                        .getToDoItem(1) shouldBe ToDoItem(1, "delectus aut autem")
                }
            }

            context("when the body is not a JSON") {
                val interceptor = MockInterceptor().apply {
                    rule(url eq "http://localhost/2") {
                        respond(HTTP_404_NOT_FOUND)
                    }
                }
                val httpClient = defaultHttpClient
                    .newBuilder()
                    .addInterceptor(interceptor)
                    .build()

                it("returns a NULL") {
                    ToDoApi("http://localhost/", testLogger, httpClient)
                        .getToDoItem(2) shouldBe null
                }
            }
        }
    }
}
