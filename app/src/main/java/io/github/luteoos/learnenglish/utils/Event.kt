package io.github.luteoos.learnenglish.utils

import java.util.*

object Event {

    class MessageWithUUID(val uuid: String, val message: String)
    class MessageWithObject<out T>(val message: String, val value: T)
}