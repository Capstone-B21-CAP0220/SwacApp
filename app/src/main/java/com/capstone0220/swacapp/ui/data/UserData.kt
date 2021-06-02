package com.capstone0220.swacapp.ui.data



import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class UserData (
   var name : String?="",
   var position: String?="",
   var location: String?="",
   var email: String?="",
   var no_telp : String?="",
   var description: String?=""
) : Parcelable