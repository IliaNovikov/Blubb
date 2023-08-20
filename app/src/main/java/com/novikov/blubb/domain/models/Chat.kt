package com.novikov.blubb.domain.models

import androidx.lifecycle.MutableLiveData

data class Chat(val id: String,
                val user1: User,
                val user2: User,
                val messages: MutableList<Message>)
