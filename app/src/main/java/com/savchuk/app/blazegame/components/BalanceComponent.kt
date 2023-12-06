package com.savchuk.app.blazegame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.ui.theme.SofiaSans

@Composable
fun BalanceComponent(balance: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(id = R.drawable.coin),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "Balance", style = TextStyle(
                    fontFamily = SofiaSans,
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
            Text(
                text = balance.toString(), style = TextStyle(
                    fontFamily = SofiaSans,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )
        }
    }
}
