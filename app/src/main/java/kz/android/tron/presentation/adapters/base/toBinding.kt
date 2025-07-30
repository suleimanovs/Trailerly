package kz.android.tron.presentation.adapters.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    val inflateMethod = V::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflateMethod.invoke(null, LayoutInflater.from(context), this, false) as V
}