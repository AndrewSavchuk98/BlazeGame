package com.savchuk.app.blazegame.game.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.game.data.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val line1 = MutableStateFlow(GameRepository())
    val line2 = MutableStateFlow(GameRepository())
    val line3 = MutableStateFlow(GameRepository())

    val score = MutableStateFlow(1000)
    val userValue = MutableStateFlow(10)
    val winValue = MutableStateFlow(0)

    private var inputBlock: Boolean = false

    fun addValue() {
        if (userValue.value > score.value - 10) return
        userValue.value = userValue.value + 10
    }

    fun removeValue() {
        if (userValue.value <= 0) return
        userValue.value = userValue.value - 10
    }

    fun maxValue() {
        userValue.value = score.value
    }

    suspend fun startGame(
        fState: LazyListState, sState: LazyListState, tState: LazyListState, itemSizePx: Float
    ) {
        if (score.value == 0) return
        if (inputBlock) return
        inputBlock = true
        score.value = score.value - userValue.value
        launchRotate(fState, line1.value, itemSizePx)
        launchRotate(sState, line2.value, itemSizePx)
        launchRotate(tState, line3.value, itemSizePx)
        calculateWinning()
        inputBlock = false
    }

    private fun calculateWinning() {
        val rows = listOf(line1.value, line2.value, line3.value)
        for (row in rows) {
            val scoreMultiplier = when {
                row.first() == row.second() && row.first() == row.third() && row.first() == R.drawable.coin -> 5
                row.first() == row.second() && row.second() == row.third() -> 4
                row.first() == row.second() && row.first() == row.third() -> 4
                row.second() == row.third() && row.second() == R.drawable.coin -> 3
                row.second() == row.third() -> 1
                row.third() == R.drawable.coin -> 2
                else -> 0
            }
            winValue.value = userValue.value * scoreMultiplier
            score.value += winValue.value
        }
        userValue.value = 10
    }

    private suspend fun launchRotate(
        state: LazyListState, lineUtil: GameRepository, itemSizePx: Float
    ) {
        val countNewElements = Random.nextInt(15, 30)
        lineUtil.generateItems(countNewElements)
        delay(200)
        val value = countNewElements * itemSizePx
        state.animateScrollBy(
            value, animationSpec = tween(
                2000
            )
        )
    }

}
