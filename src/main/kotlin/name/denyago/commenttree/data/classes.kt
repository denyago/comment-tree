package name.denyago.commenttree.data

data class Comment(val id: Int, val text: String) {
    constructor(id: Int) : this(id, "")
}

data class Node(val comment: Comment, val children: List<Node>) {
    constructor(id: Int) : this(Comment(id), emptyList<Node>())
    constructor(id: Int, children: List<Node>) : this(Comment(id), children)

    val id: Int by lazy { comment.id }

    fun toList(): List<Node> = listOf(Node(id)) + children.flatMap { it.toList() }
}
