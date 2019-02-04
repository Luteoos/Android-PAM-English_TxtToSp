package io.github.luteoos.learnenglish.utils

import android.arch.lifecycle.MutableLiveData

object Bus {
    val messageWithUUID : MutableLiveData<Event.MessageWithUUID> = MutableLiveData()

    init {
        messageWithUUID.value = Event.MessageWithUUID("","")
    }

    fun sendMessageWithUUID(event: Event.MessageWithUUID){
        this.messageWithUUID.value = event
        messageWithUUID.value = Event.MessageWithUUID("","")
    }
}