package com.novikov.blubb.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(var id: String, var nickname:String, var email:String, var password:String? = null, var userImage:Bitmap? = null) :
    Parcelable
