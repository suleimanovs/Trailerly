package kz.android.tron.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    val inflateMethod = V::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflateMethod.invoke(null, LayoutInflater.from(context), this, false) as V
}