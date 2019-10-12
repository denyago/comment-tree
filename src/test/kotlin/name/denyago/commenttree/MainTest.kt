package name.denyago.commenttree

import io.kotlintest.Spec
import io.kotlintest.shouldNotThrowAny
import io.kotlintest.specs.DescribeSpec
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class MainTest : DescribeSpec(), KoinTest {

    init {
        describe("dependencies") {
            it("are getting resolved") {
                shouldNotThrowAny {
                    startKoin {
                        modules(runDependencies)
                            .properties(mapOf("url" to "https://jsonplaceholder.typicode.com/todos/"))
                            .logger(PrintLogger(Level.DEBUG)).checkModules()
                    }
                }
            }
        }
    }

    override fun afterSpec(spec: Spec) {
        super.afterProject()
        stopKoin()
    }
}
