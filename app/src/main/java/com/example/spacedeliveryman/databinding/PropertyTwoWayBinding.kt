package com.example.spacedeliveryman.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.spacedeliveryman.custom.PropertySelectionView

@BindingAdapter("currentValue")
fun PropertySelectionView.setPropertyValue(currentValue: Int) {
    if (getPropertyValue() != currentValue) {
        setPropertyValue(currentValue)
    }
}

@InverseBindingAdapter(attribute = "currentValue")
fun PropertySelectionView.getPropertyValue(): Int {
    return getPropertyValue()
}

@BindingAdapter("currentValueAttrChanged")
fun PropertySelectionView.setListener(attrChange: InverseBindingListener) {

    val listener = object : PropertySelectionView.PropertyChangeListener {
        override fun onPropertyChanged() {
            attrChange.onChange()
        }
    }

    setPropertyListener(listener)
}