package name.denyago.commenttree

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec

class LoggerTest : DescribeSpec() {

    init {
        describe("log") {
            val testMessage = "test"
            val testSlf4jLogger = object : TestLogger() {
                override fun info(msg: String?) {
                    msg shouldBe testMessage
                }
            }

            it("logs the message using a logger implementation provided") {
                Logger(testSlf4jLogger).log(testMessage) shouldBe Unit
            }
        }
    }
}
