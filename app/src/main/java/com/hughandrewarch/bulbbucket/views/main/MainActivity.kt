package com.hughandrewarch.bulbbucket.views.main

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hughandrewarch.bulbbucket.R
import com.hughandrewarch.bulbbucket.controllers.ControllerModule.mainController
import com.hughandrewarch.bulbbucket.components.CounterView

class MainActivity : AppCompatActivity(), MainView {

    private val controller = mainController()

    private lateinit var bulbCounter: CounterView
    private lateinit var colourCounter: CounterView
    private lateinit var numberOfCounter: CounterView
    private lateinit var selectCounter: CounterView
    private lateinit var repeatCounter: CounterView
    private lateinit var selectButton: Button
    private lateinit var selectTv: TextView
    private lateinit var repeatButton: Button
    private lateinit var repeatTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller.bind(this)

        bulbCounter = findViewById(R.id.bulb_counter)
        colourCounter = findViewById(R.id.colour_counter)
        numberOfCounter = findViewById(R.id.number_of_counter)
        selectCounter = findViewById(R.id.select_counter)
        repeatCounter = findViewById(R.id.repeat_counter)
        selectButton = findViewById(R.id.selected_colours_button)
        selectTv = findViewById(R.id.select_tv)
        repeatButton = findViewById(R.id.repeat_button)
        repeatTv = findViewById(R.id.repeat_tv)

        bulbCounter.setOnChangeListener { controller.onTotalChange() }
        colourCounter.setOnChangeListener { controller.onOperandChange() }
        numberOfCounter.setOnChangeListener { controller.onOperandChange() }
        selectCounter.setOnChangeListener { controller.onSelectChange() }

        selectButton.setOnClickListener { controller.selectPressed() }
        repeatButton.setOnClickListener { controller.repeatPressed() }

        setRepeatCount(1000)
        disableBulbInc()
    }

    override fun getBulbCount(): Int {
        return bulbCounter.getCount()
    }

    override fun setBulbCount(count: Int) {
        bulbCounter.setCount(count)
    }

    override fun setBulbMagnitude(magnitude: Int) {
        bulbCounter.setMagnitude(magnitude)
    }

    override fun enableBulbInc() {
        bulbCounter.enableIncButton()
    }

    override fun disableBulbInc() {
        bulbCounter.disableIncButton()
    }

    override fun getColourCount(): Int {
        return colourCounter.getCount()
    }

    override fun setColourCount(count: Int) {
        colourCounter.setCount(count)
    }

    override fun getNumberOfCount(): Int {
        return numberOfCounter.getCount()
    }

    override fun setNumberOfCount(count: Int) {
        numberOfCounter.setCount(count)
    }

    override fun getSelectCount(): Int {
        return selectCounter.getCount()
    }

    override fun setSelectCount(count: Int) {
        selectCounter.setCount(count)
    }

    override fun enableSelectInc() {
        selectCounter.enableIncButton()
    }

    override fun disableSelectInc() {
        selectCounter.disableIncButton()
    }

    override fun setSelectTextView(text: String) {
        selectTv.text = text
    }

    override fun getRepeatCount(): Int {
        return repeatCounter.getCount()
    }

    override fun setRepeatCount(count: Int) {
        repeatCounter.setCount(count)
    }

    override fun enableRepeat() {
        repeatButton.isEnabled = true
    }

    override fun disableRepeat() {
        repeatButton.isEnabled = false
    }

    override fun setRepeatTextView(text: String) {
        repeatTv.text = text
    }
}
