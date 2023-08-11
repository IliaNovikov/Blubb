package com.novikov.blubb.domain.models

import java.time.LocalDateTime
import java.util.Date

data class Message(val text:String, val sendDate:LocalDateTime, val sender: User)
