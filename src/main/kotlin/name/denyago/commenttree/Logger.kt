package name.denyago.commenttree

import org.slf4j.Logger

interface Logable {
    fun log(msg: String?)
}

class Logger(private val logger: Logger) : Logable {

    override fun log(msg: String?) {
        logger.info(msg)
    }
}
