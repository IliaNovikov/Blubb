package com.novikov.blubb.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class Message(val text:String, val sendDate:LocalDateTime, val sender: User) : Parcelable
