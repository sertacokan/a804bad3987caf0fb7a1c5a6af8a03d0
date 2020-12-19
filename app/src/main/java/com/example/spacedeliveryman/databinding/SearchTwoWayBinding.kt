package com.example.spacedeliveryman.databinding

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.util.*

@BindingAdapter("queryText")
fun SearchView.setCurrentQuery(queryText: String?) {
    if (query != queryText) {
        setQuery(queryText, false)
    }
}

@InverseBindingAdapter(attribute = "queryText")
fun SearchView.getCurrentQuery(): String {
    return query.toString().toUpperCase(Locale.ROOT)
}

@BindingAdapter("queryTextAttrChanged")
fun SearchView.setQueryListener(inverseListener: InverseBindingListener) {

    val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            inverseListener.onChange()
            return true
        }
    }

    setOnQueryTextListener(listener)
}