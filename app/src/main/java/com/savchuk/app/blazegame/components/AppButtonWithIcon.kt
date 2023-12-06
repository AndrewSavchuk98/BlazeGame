package com.savchuk.app.blazegame.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.ArrowBack,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    tint: Color = Color.Black
) {
    IconButton(
        modifier = modifier.clip(RoundedCornerShape(4.dp)),
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor
        )
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
    }
}

@Preview
@Composable
fun AppButtonWithIconPreview() {
    AppButtonWithIcon(onClick = {})
}