package kz.android.tron.presentation.util

import android.text.Editable
import android.text.TextUtils
import android.widget.TextView

fun Editable?.isEmpty(): Boolean {
    return TextUtils.isEmpty(this.toString().trim { it <= ' ' })
}

fun TextView?.isEmpty(): Boolean {
    return TextUtils.isEmpty(this?.text.toString().trim { it <= ' ' })
}

fun String.isEmpty(): Boolean {
    return TextUtils.isEmpty(this.trim { it <= ' ' })
}

val TextView.value
    get() = text.toString().trim()
