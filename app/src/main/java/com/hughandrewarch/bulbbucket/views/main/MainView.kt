package com.hughandrewarch.bulbbucket.views.main

interface MainView {

    fun getBulbCount(): Int
    fun setBulbCount(count: Int)
    fun setBulbMagnitude(magnitude: Int)
    fun enableBulbInc()
    fun disableBulbInc()

    fun getColourCount(): Int
    fun setColourCount(count: Int)

    fun getNumberOfCount(): Int
    fun setNumberOfCount(count: Int)

    fun getSelectCount(): Int
    fun setSelectCount(count: Int)
    fun enableSelectInc()
    fun disableSelectInc()
    fun setSelectTextView(text: String)

    fun setRepeatCount(count: Int)
    fun getRepeatCount(): Int
    fun setRepeatTextView(text: String)
}
