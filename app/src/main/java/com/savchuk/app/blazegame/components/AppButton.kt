package com.savchuk.app.blazegame.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.savchuk.app.blazegame.ui.theme.SofiaSans

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    textSize: TextUnit = 24.sp
) {
    Button(
        modifier = modifier,
        onClick = { onClick() }, colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text, style = TextStyle(
                fontSize = textSize,
                color = textColor,
                fontFamily = SofiaSans
            )
        )

    }

}

@Preview
@Composable
fun AppButtonPreview() {
    AppButton(text = "Ok", onClick = {})
}