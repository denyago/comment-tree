package name.denyago.commenttree.tree

import name.denyago.commenttree.data.Node

object TreePrinter {
    fun toStringWithPadding(tree: Node, padding: Int = 0): String {
        val prefix = (1..padding).joinToString("") { " " }
        return "$prefix|- ${tree.comment.id} ${tree.comment.text}" +
                tree.children.joinToString("", "\n") {
                    toStringWithPadding(it, padding + 2)
                }
    }
}
