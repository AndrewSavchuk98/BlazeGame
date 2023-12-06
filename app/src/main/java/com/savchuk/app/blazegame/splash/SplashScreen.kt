package com.savchuk.app.blazegame.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.savchuk.app.blazegame.R
import com.savchuk.app.blazegame.navigation.Screens
import com.savchuk.app.blazegame.ui.theme.BackProgressColor
import com.savchuk.app.blazegame.ui.theme.RedLoadingColor
import com.savchuk.app.blazegame.ui.theme.SofiaSans


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {

    val progress = remember { Animatable(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.width(270.dp),
                painter = painterResource(id = R.drawable.logo_2),
                contentDescription = stringResource(R.string.logo),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(BackProgressColor),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.height(24.dp),
                    color = RedLoadingColor,
                    trackColor = BackProgressColor,
                    progress = progress.value
                )

                Text(
                    text = stringResource(R.string.loading),
                    color = Color.White,
                    fontFamily = SofiaSans
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
        navController.navigate(Screens.Menu.route) {
            popUpTo(Screens.Splash.route) { inclusive = true }
        }
    }
}


@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}