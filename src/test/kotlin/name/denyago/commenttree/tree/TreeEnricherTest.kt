package name.denyago.commenttree.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.data.Node

class TreeEnricherTest : DescribeSpec() {

    init {
        val tree = Node(1, listOf(Node(2)))
        val comments = mapOf(
            1 to Comment(1, "One"),
            2 to Comment(2, "Two")
        )

        describe("enrichTree") {
            it("enriches a Tree with Comments") {
                TreeEnricher.enrichTree(tree, comments) shouldBe Node(
                    Comment(1, "One"), listOf(
                        Node(Comment(2, "Two"), emptyList())
                    )
                )
            }
            context("when some Comments are missing") {
                val missingComments = mapOf(2 to Comment(2, "Two"))

                it("enriches with whatever present") {
                    TreeEnricher.enrichTree(tree, missingComments) shouldBe Node(
                        Comment(1, ""), listOf(
                            Node(Comment(2, "Two"), emptyList())
                        )
                    )
                }
            }
        }
    }
}
