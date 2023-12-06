package com.savchuk.app.blazegame.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.components.AppButton
import com.savchuk.app.blazegame.navigation.Screens

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.width(270.dp),
            painter = painterResource(id = R.drawable.logo_2),
            contentDescription = "logo",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(64.dp))
        AppButton(
            modifier = Modifier.width(320.dp),
            text = stringResource(R.string.play),
            onClick = { navController.navigate(Screens.Game.route) })
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(
            modifier = Modifier.width(320.dp),
            text = stringResource(R.string.web),
            onClick = { navController.navigate(Screens.Web.route) })

    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}