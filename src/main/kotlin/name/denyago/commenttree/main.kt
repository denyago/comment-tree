package name.denyago.commenttree

import io.github.rybalkinsd.kohttp.client.defaultHttpClient
import name.denyago.commenttree.api.Client
import name.denyago.commenttree.api.Clientable
import name.denyago.commenttree.api.ToDoApi
import name.denyago.commenttree.api.ToDoApiable
import name.denyago.commenttree.data.Node
import name.denyago.commenttree.tree.TreeEnrichable
import name.denyago.commenttree.tree.TreeEnricher
import name.denyago.commenttree.tree.TreePrintable
import name.denyago.commenttree.tree.TreePrinter
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.slf4j.LoggerFactory
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

val runDependencies = module {
    single<Logable> { Logger(LoggerFactory.getLogger("CommentTree")) }
    single { defaultHttpClient }

    single<TreeEnrichable> { TreeEnricher }
    single<TreePrintable> { TreePrinter }
    single<Clientable> { Client() }

    single<ToDoApiable> { ToDoApi(getProperty("url"), get(), get()) }
}

fun main() {
    startKoin {
        modules(runDependencies).properties(mapOf("url" to "https://jsonplaceholder.typicode.com/todos/"))
    }

    println(
        TreePopulateApplication(tree).populatedToString()
    )

    exitProcess(SUCCESS)
}
