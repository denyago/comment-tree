package name.denyago.commenttree

import name.denyago.commenttree.api.Client
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnricher
import name.denyago.commenttree.tree.TreePrinter
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.system.exitProcess

@Suppress("MagicNumber")
val tree = Node(
    1, listOf(
        Node(2),
        Node(
            3, listOf(
                Node(4),
                Node(5)
            )
        ),
        Node(
            6, listOf(
                Node(7)
            )
        )
    )
)
const val SUCCESS = 0

val myModule = module {
    single { TreeEnricher }
    single { TreePrinter }
    single { Client(getProperty("url")) }
}

fun main() {
    startKoin {
        modules(myModule).properties(mapOf("url" to "https://jsonplaceholder.typicode.com/todos/"))
    }

    println(
        TreePopulateApplication(tree).populatedToString()
    )

    exitProcess(SUCCESS)
}
