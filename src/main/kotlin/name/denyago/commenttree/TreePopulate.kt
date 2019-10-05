package name.denyago.commenttree

import name.denyago.commenttree.api.Client
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnricher
import name.denyago.commenttree.tree.TreePrinter

class TreePopulate(private val url: String, private val tree: Node) {

    fun populatedToString(): String = TreePrinter.toStringWithPadding(populate())

    private fun populate(): Node {
        val ids = tree.toList().map { it.id }
        val comments = Client(url).getComments(ids)

        return TreeEnricher.enrichTree(tree, comments)
    }

}
