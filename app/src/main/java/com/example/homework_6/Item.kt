package com.example.homework_6

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Item(
    var name: String,
    var surname: String,
    var phone: String,
    var age: String,
    var dob: String

): Parcelable {

}