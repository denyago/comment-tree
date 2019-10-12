package name.denyago.commenttree

import name.denyago.commenttree.api.Clientable
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnrichable
import name.denyago.commenttree.tree.TreePrintable
import org.koin.core.KoinComponent
import org.koin.core.inject

class TreePopulateApplication(private val tree: Node) : KoinComponent {

    private val enricher by inject<TreeEnrichable>()
    private val printer by inject<TreePrintable>()
    private val client by inject<Clientable>()

    fun populatedToString(): String = printer.toStringWithPadding(populate())

    private fun populate(): Node {
        val ids = tree.toList().map { it.id }
        val comments = client.getComments(ids)

        return enricher.enrichTree(tree, comments)
    }

}
