package com.example.spacedeliveryman.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <DB : ViewDataBinding> ViewGroup.dataBinding(@LayoutRes layout: Int): Lazy<DB> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val layoutInflater = LayoutInflater.from(context)
        DataBindingUtil.inflate(layoutInflater, layout, this, true)
    }
}

fun <DB : ViewDataBinding> Fragment.dataBinding(@LayoutRes layout: Int): Lazy<DB> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val layoutInflater = LayoutInflater.from(context)
        DataBindingUtil.inflate(layoutInflater, layout, null, true)
    }
}
