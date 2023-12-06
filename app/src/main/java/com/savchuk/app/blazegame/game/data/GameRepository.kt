package com.savchuk.app.blazegame.game.data

import com.savchuk.app.blazegame.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameRepository {

    private val itemsImages = listOf(
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.strawberry,
        R.drawable.coin,
        R.drawable.lemon,
        R.drawable.mushroom,
        R.drawable.watermelon
    )

    private val listImages = itemsImages.shuffled()
    private val lastPosition = MutableStateFlow(0)

    private val _lineState = MutableStateFlow(listImages)
    val lineState: StateFlow<List<Int>> = _lineState

    fun generateItems(count: Int) {
        lastPosition.value = count
        val temp = mutableListOf<Int>()
        for (i in 0 until count) {
            temp.addAll(listImages)
        }
        _lineState.value = lineState.value + temp
    }

    fun first(): Int = lineState.value[lastPosition.value - 2]
    fun second(): Int = lineState.value[lastPosition.value - 1]
    fun third(): Int = lineState.value[lastPosition.value]
}