package name.denyago.commenttree

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import name.denyago.commenttree.api.Clientable
import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnrichable
import name.denyago.commenttree.tree.TreePrintable
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class TreePopulateApplicationTest : DescribeSpec(), KoinTest {

    init {
        val tree = Node(1, listOf(Node(2, listOf(Node(3)))))
        val rawCommnets = mapOf(2 to Comment(2, "I am comment"))
        val enrichedTree = Node(1, listOf(Node(Comment(2, "I am comment"), listOf(Node(3)))))
        val finalTreeString = "...here is the tree with comments..."

        val testTreePrinter = object : TreePrintable {
            override fun toStringWithPadding(tree: Node, padding: Int): String {
                tree shouldBe enrichedTree
                padding shouldBe 0

                return finalTreeString
            }
        }

        val testTreeEnricher = object : TreeEnrichable {
            override fun enrichTree(node: Node, comments: Map<Int, Comment>): Node {
                node shouldBe tree
                comments shouldBe rawCommnets

                return enrichedTree
            }
        }

        val testClient = object : Clientable {
            override fun getComments(ids: List<Int>): Map<Int, Comment> {
                ids shouldBe listOf(1, 2, 3)

                return rawCommnets
            }
        }

        describe("populatedToString") {

            it("returns a nice Tree representations") {
                startKoin {
                    modules(
                        module {
                            single<TreePrintable> { testTreePrinter }
                            single<TreeEnrichable> { testTreeEnricher }
                            single<Clientable> { testClient }
                        })
                }

                TreePopulateApplication(tree).populatedToString() shouldBe finalTreeString
            }
        }
    }
}


