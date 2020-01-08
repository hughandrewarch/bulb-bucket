package com.hughandrewarch.bulbbucket.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.hughandrewarch.bulbbucket.R
import kotlin.math.max

class CounterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var count = 0
    private var magnitude = 1
    private var minimum: Int
    private var onChange: () -> Unit = {}
    private var countTv: TextView? = null
    private var inc: Button? = null
    private var dec: Button? = null

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_counter, this, true)

        countTv = findViewById(R.id.counter_count)
        inc = findViewById(R.id.counter_button_inc)
        dec = findViewById(R.id.counter_button_dec)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CounterView)
        minimum = attributes.getInt(R.styleable.CounterView_min, Int.MIN_VALUE)
        attributes.recycle()

        count = max(0, minimum)
        setCount(count)

        inc?.setOnClickListener {
            setCount(count+magnitude)
            this.onChange()
        }
        dec?.setOnClickListener {
            setCount(count-magnitude)
            this.onChange()
        }
    }

    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
        updateCountTv()
        setDecButtonState()
    }

    fun setMagnitude(magnitude: Int) {
        this.magnitude = magnitude
        setDecButtonState()
    }

    fun setOnChangeListener(onChange: () -> Unit) {
        this.onChange = onChange
    }

    fun enableIncButton() {
        inc?.isEnabled = true
    }

    fun disableIncButton() {
        inc?.isEnabled = false
    }

    fun enableDecButton() {
        dec?.isEnabled = true
    }

    fun disableDecButton() {
        dec?.isEnabled = false
    }

    private fun updateCountTv() {
        countTv?.text = count.toString()
    }

    private fun setDecButtonState() {
        val nextStep = count - magnitude
        if(nextStep >= minimum && count != minimum) {
            enableDecButton()
        } else {
            disableDecButton()
        }
    }
}
