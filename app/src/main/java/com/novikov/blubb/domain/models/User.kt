package com.novikov.blubb.domain.models

import android.graphics.Bitmap

data class User(val id: String, val nickname:String, val email:String, val password:String, val userImage:Bitmap)
