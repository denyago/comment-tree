package name.denyago.commenttree.api

import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import name.denyago.commenttree.data.Comment
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class ClientTest : DescribeSpec(), KoinTest {

    override fun afterTest(testCase: TestCase, result: TestResult) {
        stopKoin()
    }

    init {
        describe("getComments") {
            val toDoItems = mapOf(1 to ToDoItem(1, "one"), 3 to ToDoItem(3, "three"))
            val testToDoApi = object : ToDoApiable {
                override suspend fun getToDoItem(id: Int): ToDoItem? = toDoItems[id]
            }

            it("returns a map of Comments") {
                startKoin {
                    modules(module {
                        single<ToDoApiable> { testToDoApi }
                    })
                }

                Client()
                    .getComments(listOf(1, 2, 3)) shouldBe mapOf(
                    1 to Comment(1, "one"),
                    3 to Comment(3, "three")
                )
            }
        }
    }
}
