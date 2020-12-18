package com.example.spacedeliveryman.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.SeekBar
import com.example.spacedeliveryman.R
import com.example.spacedeliveryman.databinding.ComponentProperyLayoutBinding
import com.example.spacedeliveryman.extensions.dataBinding
import com.google.android.material.slider.Slider

class PropertySelectionView @JvmOverloads constructor(ctx: Context, attr: AttributeSet? = null) : LinearLayout(ctx, attr), SeekBar.OnSeekBarChangeListener {

    private val viewRootBinding: ComponentProperyLayoutBinding by dataBinding(R.layout.component_propery_layout)

    private var _propertyChangeListener: PropertyChangeListener? = null

    init {
        orientation = VERTICAL

        val ta = context.obtainStyledAttributes(attr, R.styleable.PropertySelectionView)

        try {
            val title = ta.getString(R.styleable.PropertySelectionView_propertyTitle)
            viewRootBinding.propertyTitle.text = title
        } finally {
            ta.recycle()
        }
        viewRootBinding.propertySlider.setOnSeekBarChangeListener(this@PropertySelectionView)
    }

    fun getPropertyValue(): Int {
        return viewRootBinding.propertySlider.progress
    }

    fun setPropertyValue(propertyValue: Int) {
        viewRootBinding.propertySlider.progress = propertyValue
    }

    fun setMaxValue(maxValue: Int) {
        viewRootBinding.propertySlider.max = maxValue
    }

    fun setPropertyListener(propertyChangeListener: PropertyChangeListener) {
        _propertyChangeListener = propertyChangeListener
    }

    fun setPropertyTitle(title: String) {
        viewRootBinding.propertyTitle.text = title
    }

    interface PropertyChangeListener {
        fun onPropertyChanged()
    }

    //region Slider Listener
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (progress >= 1) {
            _propertyChangeListener?.onPropertyChanged()
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
    //endregion
}