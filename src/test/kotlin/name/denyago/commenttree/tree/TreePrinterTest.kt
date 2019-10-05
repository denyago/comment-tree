package name.denyago.commenttree.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.data.Node

class TreePrinterTest : DescribeSpec() {

    init {
        val tree = Node(1, listOf(Node(Comment(2, "I am comment"), listOf(Node(3)))))

        describe("toStringWithPadding") {
            it("returns a nice Tree representations") {
                TreePrinter.toStringWithPadding(tree).trimEnd() shouldBe """
                    |- 1 
                      |- 2 I am comment
                        |- 3
                """.trimIndent()
            }
        }
    }

}