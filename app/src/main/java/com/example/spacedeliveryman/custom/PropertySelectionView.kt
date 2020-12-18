package com.example.spacedeliveryman.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.example.spacedeliveryman.R

class PropertySelectionView @JvmOverloads constructor(ctx: Context, attr: AttributeSet? = null) : LinearLayout(ctx, attr), SeekBar.OnSeekBarChangeListener {

    private val viewRoot by lazy(LazyThreadSafetyMode.NONE) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.component_propery_layout, this, true)
    }

    private val propertyTitle by lazy(LazyThreadSafetyMode.NONE) {
        viewRoot.findViewById<TextView>(R.id.property_title)
    }

    private val propertySeekbar by lazy(LazyThreadSafetyMode.NONE) {
        viewRoot.findViewById<SeekBar>(R.id.property_seekbar)
    }

    private var _propertyChangeListener: PropertyChangeListener? = null


    init {
        orientation = VERTICAL

        val ta = context.obtainStyledAttributes(attr, R.styleable.PropertySelectionView)

        try {
            val title = ta.getString(R.styleable.PropertySelectionView_propertyTitle)
            propertyTitle.text = title
        } finally {
            ta.recycle()
        }
    }

    fun getPropertyValue(): Int {
        return propertySeekbar.progress
    }

    fun setPropertyValue(propertyValue: Int) {
        propertySeekbar.progress = propertyValue
    }

    interface PropertyChangeListener {
        fun onPropertyChanged()
    }

    //region Seekbar Listener
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        _propertyChangeListener?.onPropertyChanged()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
    //endregion
}