package com.derandecker.servelemonade

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _lemonadeList = mutableStateListOf<Int>()

    val lemonadeList
        get() = _lemonadeList

    fun addToLemonadeList() {
        if (_lemonadeList.isNotEmpty()) {
            _lemonadeList.add(_lemonadeList.last() + 1)
        } else {
            _lemonadeList.add(1)
        }
    }

    fun clearLemonadeList() {
        _lemonadeList.clear()
    }
}
