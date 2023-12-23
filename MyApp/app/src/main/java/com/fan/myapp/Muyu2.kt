package com.fan.myapp

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class Muyu2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MuyuBoy(context = this@Muyu2)
            }
        }
    }
}


@Composable
fun MuyuBoy(context: Context) {
    rememberSystemUiController().apply {
        setStatusBarColor(Color.Black)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            Muyu(context = context)
        }
    }
}

@Composable
fun Muyu(context: Context) {
    var allNumber by remember {
        mutableStateOf(0)
    }
    val sp = context.getSharedPreferences("GD", Context.MODE_PRIVATE)
    allNumber = sp.getInt("GD_all", 0)
    var dailyNumber by remember {
        mutableStateOf(0)
    }
    var fishSizeAnim = remember {
        Animatable(250.dp, Dp.VectorConverter)
    }
    var plusOneBottom = remember(dailyNumber) {
        Animatable(150.dp, Dp.VectorConverter)
    }
    var plusOneAlpha = remember {
        Animatable(0f)
    }
    var plusOneFontSize = remember(dailyNumber) {
        Animatable(35f)
    }
    LaunchedEffect(dailyNumber) {
        fishSizeAnim.animateTo(280.dp, initialVelocity = 50.dp)
        fishSizeAnim.snapTo(250.dp)
    }
    LaunchedEffect(dailyNumber) {
        plusOneBottom.animateDecay(850.dp, exponentialDecay())
    }
    LaunchedEffect(dailyNumber) {
        plusOneAlpha.snapTo(10f)
        plusOneAlpha.animateTo(0f)
    }
    LaunchedEffect(dailyNumber) {
        plusOneFontSize.animateTo(25f)
    }
    NumberTitle(allNumber, dailyNumber)
    WoodenFish(
        fishSizeAnim,
        plusOneBottom,
        plusOneAlpha,
        plusOneFontSize,
        context
    ) {
        playSound(context)
        startVibrator()
        dailyNumber += 100000
        allNumber += 100000
        sp
            .edit()
            .putInt("GD_all", allNumber)
            .apply()
    }
}

@Composable
private fun WoodenFish(fishSizeAnim: Animatable<Dp, AnimationVector1D>, plusOneBottom: Animatable<Dp, AnimationVector1D>, plusOneAlpha: Animatable<Float, AnimationVector1D>, plusOneFontSize: Animatable<Float, AnimationVector1D>, context: Context, clickable: () -> Unit) {
    var me = MediaPlayer.create(context, R.raw.gd);
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.woodblock_playstore),
            contentDescription = "木鱼",
            modifier = Modifier
                .size(fishSizeAnim.value)
                .clickable {
                    me.start()
                    clickable()
                })
        Text(
            text = "功德 +100000",
            Modifier
                .padding(bottom = plusOneBottom.value)
                .alpha(plusOneAlpha.value),
            color = Color.White,
            fontSize = plusOneFontSize.value.sp,
        )
    }
}


@Composable
private fun NumberTitle(allNumber: Int, dailyNumber: Int) {
    Text(
        text = "累计功德 $allNumber 次",
        color = Color.White,
        fontSize = 15.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(top = 40.dp),
        textAlign = TextAlign.Center
    )
    Text(
        text = "本次功德 $dailyNumber 次",
        color = Color.White,
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(top = 5.dp),
        textAlign = TextAlign.Center
    )
}

val soundPool = SoundPool.Builder().build()
private fun playSound(context: Context) {
    soundPool.setOnLoadCompleteListener { _, _, _ ->
        soundPool.play(
            soundPool.load(context, R.raw.gd, 1),
            1f,
            1f,
            0,
            0,
            1f
        )
    }
}


private fun startVibrator() {
//        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
//            vibratorManager.getVibrator(0)
//        } else {
//            getSystemService(VIBRATOR_SERVICE) as Vibrator
//        }
//        if (vibrator.hasVibrator()) {
//            vibrator.vibrate(50)
//        }
}