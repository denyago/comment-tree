package name.denyago.commenttree.tree

import name.denyago.commenttree.data.Comment
import name.denyago.commenttree.data.Node

interface TreeEnrichable {
    fun enrichTree(node: Node, comments: Map<Int, Comment>): Node
}

object TreeEnricher : TreeEnrichable {
    override fun enrichTree(node: Node, comments: Map<Int, Comment>): Node {
        val comment = comments[node.id] ?: Comment(node.id)
        return Node(comment, node.children.map { enrichTree(it, comments) })
    }
}
