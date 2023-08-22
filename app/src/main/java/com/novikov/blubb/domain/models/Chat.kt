package com.novikov.blubb.domain.models

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(val id: String,
                val user1: User,
                val user2: User,
                val messages: MutableList<Message>) : Parcelable
