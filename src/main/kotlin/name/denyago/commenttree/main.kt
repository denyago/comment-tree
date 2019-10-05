package name.denyago.commenttree

import name.denyago.commenttree.data.Node

fun main() {
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

    println(
        TreePopulate("https://jsonplaceholder.typicode.com/todos/", tree).populatedToString()
    )

    System.exit(0)
}





