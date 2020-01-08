package com.hughandrewarch.bulbbucket.controllers

import com.hughandrewarch.bulbbucket.views.main.MainView
import com.hughandrewarch.bulbbucket.models.domain.Bucket
import com.hughandrewarch.bulbbucket.models.domain.Lightbulb

class MainController {

    private lateinit var view: MainView

    private var bucket: Bucket? = null

    fun bind(mainView: MainView) {
        view = mainView
    }

    fun onTotalChange() {
        view.setNumberOfCount(view.getBulbCount() / view.getColourCount())

        setSelectState()
    }

    fun onOperandChange() {
        view.setBulbCount(view.getColourCount() * view.getNumberOfCount())
        view.setBulbMagnitude(view.getColourCount())
        when {
            view.getColourCount() == 0 -> view.disableBulbInc()
            else -> view.enableBulbInc()
        }
        setSelectState()
    }

    fun onSelectChange() {
        setSelectState()
    }

    fun selectPressed() {
        initBucket()
        val selected = selectLightBulbs(view.getSelectCount())
        val uniqueCount = selected.map { it.colour }.toSet().size

        val text = "$uniqueCount unique colours selected out of ${view.getBulbCount()} bulbs and ${view.getColourCount()} possible colours"

        view.setSelectTextView(text)
    }

    fun repeatPressed() {
        initBucket()
        val avg = getAvgUniqueColourCount(view.getSelectCount(), view.getRepeatCount())

        val text = "On average $avg unique colours were selected out of ${view.getBulbCount()} bulbs and ${view.getColourCount()} possible colours over ${view.getRepeatCount()} trials"

        view.setRepeatTextView(text)
    }

    private fun setSelectState() {

        if(view.getSelectCount() < view.getBulbCount()) {
            view.enableSelectInc()
        } else {
            view.disableSelectInc()
            view.setSelectCount(view.getBulbCount())
        }
    }

    private fun initBucket() {
        bucket = Bucket(view.getNumberOfCount(), view.getColourCount())
    }

    private fun selectLightBulbs(select: Int): Set<Lightbulb> {
        return bucket!!.selectLightBulbs(select)
    }

    private fun getAvgUniqueColourCount(select: Int, repeat: Int = 1): Double {

        val counts = mutableListOf<Int>()
        repeat(repeat) {
            counts.add(selectLightBulbs(select).map { it.colour }.toSet().size)
        }

        return counts.average()
    }

}