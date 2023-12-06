package com.savchuk.app.blazegame.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.components.AppButton
import com.savchuk.app.blazegame.components.AppButtonWithIcon
import com.savchuk.app.blazegame.components.BalanceComponent
import com.savchuk.app.blazegame.game.ui.components.GameColumnTest
import com.savchuk.app.blazegame.game.ui.components.LabelComponent
import com.savchuk.app.blazegame.game.ui.components.WinDialog
import com.savchuk.app.blazegame.ui.theme.GreyRollBackColor
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    val viewModel: GameViewModel = viewModel()

    val firstLineState = rememberLazyListState()
    val secondLineState = rememberLazyListState()
    val thirdLineState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val score = viewModel.score.collectAsState()
    val userValue = viewModel.userValue.collectAsState()
    val winValue = viewModel.winValue.collectAsState()

    val line1 = viewModel.line1.collectAsState()
    val line2 = viewModel.line2.collectAsState()
    val line3 = viewModel.line3.collectAsState()

    val itemSize = 80.dp
    val density = LocalDensity.current
    val itemSizePx = with(density) { itemSize.toPx() }

    var showLinksDialog by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .paint(
                    painterResource(id = R.drawable.second_background),
                    contentScale = ContentScale.Crop
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Row(
                modifier = modifier
                    .wrapContentSize()
                    .background(
                        GreyRollBackColor,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp)
            ) {

                GameColumnTest(
                    items = line1.value.lineState.collectAsState().value,
                    state = firstLineState
                )
                GameColumnTest(
                    items = line2.value.lineState.collectAsState().value,
                    state = secondLineState
                )
                GameColumnTest(
                    items = line3.value.lineState.collectAsState().value,
                    state = thirdLineState
                )
            }

            Column(
                modifier = Modifier
                    .width(230.dp)
                    .height(270.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                BalanceComponent(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    balance = score.value
                )
                LabelComponent(
                    title = stringResource(R.string.your_bet),
                    label = userValue.value.toString()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AppButtonWithIcon(
                        icon = ImageVector.vectorResource(R.drawable.round_remove_24),
                        onClick = { viewModel.removeValue() },
                        backgroundColor = Color.Red,
                        tint = Color.White
                    )
                    AppButtonWithIcon(
                        icon = Icons.Rounded.Add,
                        onClick = { viewModel.addValue() },
                        tint = Color.White,
                        backgroundColor = Color.Green
                    )
                    AppButton(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "MAX BET",
                        onClick = { viewModel.maxValue() }, textSize = 16.sp
                    )
                }
                LabelComponent(
                    title = stringResource(R.string.total_win),
                    label = winValue.value.toString()
                )
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.spin),
                    onClick = {

                        scope.launch {
                            viewModel.startGame(
                                firstLineState,
                                secondLineState,
                                thirdLineState,
                                itemSizePx
                            )
                        }
                    },
                    backgroundColor = Color.Green,
                    textSize = 36.sp,
                    textColor = Color.White
                )

            }
            if (winValue.value > 0 && !showLinksDialog) {
                showLinksDialog = true
            }

            if (showLinksDialog) {
                WinDialog(winValue.value) {
                    showLinksDialog = false
                }
            }
        }
    }

}


@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}