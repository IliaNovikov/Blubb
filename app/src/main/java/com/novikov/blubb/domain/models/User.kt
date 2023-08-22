package com.novikov.blubb.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(var id: String, val nickname:String, val email:String, val password:String? = null, val userImage:Bitmap? = null) :
    Parcelable
