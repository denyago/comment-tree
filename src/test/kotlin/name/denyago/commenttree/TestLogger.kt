package name.denyago.commenttree

import org.slf4j.Marker

abstract class TestLogger : org.slf4j.Logger {
    override fun info(msg: String?) = Unit
    override fun info(format: String?, arg: Any?) = Unit
    override fun info(format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun info(format: String?, vararg arguments: Any?) = Unit
    override fun info(msg: String?, t: Throwable?) = Unit
    override fun info(marker: Marker?, msg: String?) = Unit
    override fun info(marker: Marker?, format: String?, arg: Any?) = Unit
    override fun info(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun info(marker: Marker?, format: String?, vararg arguments: Any?) = Unit
    override fun info(marker: Marker?, msg: String?, t: Throwable?) = Unit
    override fun warn(msg: String?) = Unit
    override fun warn(format: String?, arg: Any?) = Unit
    override fun warn(format: String?, vararg arguments: Any?) = Unit
    override fun warn(format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun warn(msg: String?, t: Throwable?) = Unit
    override fun warn(marker: Marker?, msg: String?) = Unit
    override fun warn(marker: Marker?, format: String?, arg: Any?) = Unit
    override fun warn(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun warn(marker: Marker?, format: String?, vararg arguments: Any?) = Unit
    override fun warn(marker: Marker?, msg: String?, t: Throwable?) = Unit
    override fun error(msg: String?) = Unit
    override fun error(format: String?, arg: Any?) = Unit
    override fun error(format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun error(format: String?, vararg arguments: Any?) = Unit
    override fun error(msg: String?, t: Throwable?) = Unit
    override fun error(marker: Marker?, msg: String?) = Unit
    override fun error(marker: Marker?, format: String?, arg: Any?) = Unit
    override fun error(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun error(marker: Marker?, format: String?, vararg arguments: Any?) = Unit
    override fun error(marker: Marker?, msg: String?, t: Throwable?) = Unit
    override fun debug(msg: String?) = Unit
    override fun debug(format: String?, arg: Any?) = Unit
    override fun debug(format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun debug(format: String?, vararg arguments: Any?) = Unit
    override fun debug(msg: String?, t: Throwable?) = Unit
    override fun debug(marker: Marker?, msg: String?) = Unit
    override fun debug(marker: Marker?, format: String?, arg: Any?) = Unit
    override fun debug(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun debug(marker: Marker?, format: String?, vararg arguments: Any?) = Unit
    override fun debug(marker: Marker?, msg: String?, t: Throwable?) = Unit
    override fun trace(msg: String?) = Unit
    override fun trace(format: String?, arg: Any?) = Unit
    override fun trace(format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun trace(format: String?, vararg arguments: Any?) = Unit
    override fun trace(msg: String?, t: Throwable?) = Unit
    override fun trace(marker: Marker?, msg: String?) = Unit
    override fun trace(marker: Marker?, format: String?, arg: Any?) = Unit
    override fun trace(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) = Unit
    override fun trace(marker: Marker?, format: String?, vararg argArray: Any?) = Unit
    override fun trace(marker: Marker?, msg: String?, t: Throwable?) = Unit
    override fun isWarnEnabled(): Boolean = true
    override fun isWarnEnabled(marker: Marker?): Boolean = true
    override fun isTraceEnabled(): Boolean = true
    override fun isTraceEnabled(marker: Marker?): Boolean = true
    override fun isDebugEnabled(): Boolean = true
    override fun isDebugEnabled(marker: Marker?): Boolean = true
    override fun isInfoEnabled(): Boolean = true
    override fun isInfoEnabled(marker: Marker?): Boolean = true
    override fun isErrorEnabled(): Boolean = true
    override fun isErrorEnabled(marker: Marker?): Boolean = true
    override fun getName(): String = "change me"
}
