package name.denyago.commenttree

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("CommentTree")

fun log(msg: String?) {
    log.info(msg)
}
