package name.denyago.commenttree

import name.denyago.commenttree.data.Node
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

fun main() {
    println(
        TreePopulate("https://jsonplaceholder.typicode.com/todos/", tree).populatedToString()
    )

    exitProcess(SUCCESS)
}
