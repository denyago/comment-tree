package name.denyago.commenttree

import name.denyago.commenttree.api.Client
import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.data.Node

class TreePopulate(val url: String, val tree: Node) {

    fun populatedToString(): String = populate().toStringWithPadding()

    private fun populate(): Node {
        val ids = tree.toList().map { it.id }
        val comments = Client(url).getComments(ids)

        return enrichTree(tree, comments)
    }

    private fun enrichTree(node: Node, comments: Map<Int, Comment>): Node {
        val comment = comments[node.id] ?: Comment(node.id)
        return Node(comment, node.children.map { enrichTree(it, comments) })
    }

    private fun Node.toStringWithPadding(padding: Int = 0): String {
        val prefix = (1..padding).joinToString("") { " " }
        return "$prefix|- ${comment.id} ${comment.text}\n" +
                children.joinToString("") {
                    it.toStringWithPadding(padding + 2)
                }
    }
}
