package com.savchuk.app.blazegame.game.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.components.AppButton
import com.savchuk.app.blazegame.ui.theme.DarkGreyRollBorderColor
import com.savchuk.app.blazegame.ui.theme.SofiaSans

@Composable
fun WinDialog(value: Int = 0, onClose: () -> Unit) {
    Dialog(onDismissRequest = { onClose() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(DarkGreyRollBorderColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.coin),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "+$value", style = TextStyle(
                    color = Color.White,
                    fontFamily = SofiaSans,
                    fontSize = 36.sp
                )
            )
            AppButton(modifier = Modifier.width(180.dp), text = "Ok", onClick = { onClose() })
        }

    }
}

@Preview
@Composable
fun WinDialogPreview() {
    WinDialog(0) {}
}