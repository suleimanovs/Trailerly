package kz.android.tron.presentation.util

import android.text.Editable
import android.text.TextUtils
import android.widget.TextView

fun Editable?.isEmpty(): Boolean {
    return TextUtils.isEmpty(this.toString().trim { it <= ' ' })
}

fun TextView.getValue():String{
   return text.toString().trim()
}