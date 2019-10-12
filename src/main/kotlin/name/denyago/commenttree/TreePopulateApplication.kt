package name.denyago.commenttree

import name.denyago.commenttree.api.Client
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnricher
import name.denyago.commenttree.tree.TreePrinter
import org.koin.core.KoinComponent
import org.koin.core.inject

class TreePopulateApplication(private val tree: Node) : KoinComponent {

    private val enricher by inject<TreeEnricher>()
    private val printer by inject<TreePrinter>()
    private val client by inject<Client>()

    fun populatedToString(): String = printer.toStringWithPadding(populate())

    private fun populate(): Node {
        val ids = tree.toList().map { it.id }
        val comments = client.getComments(ids)

        return enricher.enrichTree(tree, comments)
    }

}
