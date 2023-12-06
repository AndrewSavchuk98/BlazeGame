package com.savchuk.app.blazegame.game.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.game.ui.GameViewModel
import com.savchuk.app.blazegame.ui.theme.DarkGreyRollBorderColor
import com.savchuk.app.blazegame.ui.theme.GreyRollBackColor
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun GameColumn(
    items: State<List<Int>>,
    state: LazyListState?,
    viewModel: GameViewModel

) {
    LazyColumn(
        state = state ?: rememberLazyListState(),
        modifier = Modifier
            // .height(viewModel.itemHeightDp.value * 3)
            .width(130.dp)
            .height(270.dp)
    ) {
        items(
            items = items.value
        ) {

        }
    }
}

@Composable
fun GameColumnTest(
    items: List<Int>,
    state: LazyListState?,
) {
    LazyColumn(
        state = state ?: rememberLazyListState(),
        modifier = Modifier
            .width(130.dp)
            .height(260.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(GreyRollBackColor)
            .border(
                width = 2.dp,
                color = DarkGreyRollBorderColor,
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = false
    ) {
        items(
            items = items
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(60.dp),
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ColumnPreview() {
    val items = listOf(
        R.drawable.coin,
        R.drawable.lemon,
        R.drawable.mushroom,
        R.drawable.strawberry,
        R.drawable.watermelon
    )
    val state = MutableStateFlow(items)
    GameColumnTest(
        items = items,
        state = rememberLazyListState()
    )
}

