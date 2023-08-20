package com.novikov.blubb.domain.models

import android.graphics.Bitmap

data class User(var id: String, val nickname:String, val email:String, val password:String? = null, val userImage:Bitmap? = null)
