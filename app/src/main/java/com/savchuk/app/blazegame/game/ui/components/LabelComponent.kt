package com.savchuk.app.blazegame.game.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.savchuk.app.blazegame.ui.theme.DarkGreyRollBorderColor
import com.savchuk.app.blazegame.ui.theme.GreyRollBackColor
import com.savchuk.app.blazegame.ui.theme.SofiaSans

@Composable
fun LabelComponent(title: String, label: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .width(230.dp)
            .background(DarkGreyRollBorderColor, RoundedCornerShape(4.dp))
            .border(width = 2.dp, color = GreyRollBackColor, shape = RoundedCornerShape(4.dp))
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, style = TextStyle(
                fontFamily = SofiaSans,
                color = Color.Gray
            )
        )
        Text(
            text = label, style = TextStyle(
                fontFamily = SofiaSans,
                color = Color.White,
                fontSize = 24.sp
            )
        )

    }
}

@Preview
@Composable
fun LabelComponentPreview() {
    LabelComponent(title = "Your bet", label = "2000")
}