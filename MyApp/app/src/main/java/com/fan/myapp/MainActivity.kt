package com.fan.myapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold


import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fan.myapp.data.Pages
import com.fan.myapp.data.SecretScript
import com.fan.myapp.data.UserNavigation
import com.fan.myapp.ui.theme.MyAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(Color.Black, darkIcons = false)
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting(this@MainActivity)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Greeting(context: Context) {
    var UserNavigationList: List<UserNavigation> = UserNavigation.getUserNavigation()
    var currentNavIndex by remember {
        mutableIntStateOf(0)
    }
    //导航
    val navController = rememberNavController()
    /*NavHost(navController = navController, startDestination = "MainPage") {
        //声明名为MainPage的页面路由
        composable("MainPage") {
            //页面路由对应的页面组件
            MainPage()
        }
        composable("SettingPage") {
            SettingPage()
        }
    }*/

    Scaffold(
//        topBar = {
//            TopAppBar(
//                elevation = 100.dp,
//                title = {
//                    Text(text = title)
//                }, actions = {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = null
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = "Edit")
//                })
//        },
        bottomBar = {
            BottomNavigation() {
                UserNavigationList.forEachIndexed { index, userNavigation ->
                    BottomNavigationItem(
                        selected = currentNavIndex == index,
                        onClick = {
                            currentNavIndex = index

                            if (index == 0) {
                                navController.navigate(Pages.MainPage) {
                                    launchSingleTop = true
                                }
                            } else if (index == 1) {
                                navController.navigate("${Pages.SettingPage}/第二") {
                                    launchSingleTop = true
                                }
                            } else if (index == 2) {
                                navController.navigate("${Pages.Me}?age=18") {
                                    launchSingleTop = true
                                }
                            }

                            Log.d("=============", userNavigation.name.toString())
                        },
                        icon = {

                            userNavigation.img?.let {
                                Icon(
                                    it,
                                    contentDescription = null,
                                )
                            }

                        },
                        label = {
                            if (currentNavIndex == index) {
                                Text(text = userNavigation.name, color = Color.White)

                            } else {
                                Text(text = userNavigation.name)
                            }
                        }
                    )
                }

            }
        },
    ) {
        NavHost(navController = navController, startDestination = "MainPage") {
            //声明名为MainPage的页面路由
            composable(Pages.MainPage) {
                //页面路由对应的页面组件
                MainPage()
            }
            composable(Pages.SettingPage + "/{title}") {
                val title = it.arguments?.getString("title")
                SettingPage(context = context)
            }
            composable("${Pages.Me}?age={age}", arguments = listOf(
                navArgument("age") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) {
                val age = it.arguments?.getInt("age")
                me(age)
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MainPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 100.dp,
                title = {
                    Text(text = "首页")
                }, actions = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Edit")
                })
        }
    ) {
        println(it)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "主页")
        }
    }


}

@Composable
fun SettingPage(context: Context) {
//    MuyuBoy(context = context)
    ShowMessage()
}


@Composable
fun me(age: Int?) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 100.dp,
                title = {
                    Text(text = "我的")
                }, actions = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Edit")
                })
        }
    ) {
        println(it)
        c1()
//        muyu()
    }
}


@Composable
fun c1() {
    val data = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val gteSecretScript: List<SecretScript> = SecretScript.gteSecretScript()
    LazyVerticalGrid(
        modifier = Modifier.absolutePadding(top = 50.dp),
        columns = GridCells.Fixed(
            2,
        )
    ) {
        items(gteSecretScript) {
            Card(
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(250.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageBitmap = ImageBitmap.imageResource(R.drawable.wzqxj)
                    Image(
                        modifier = Modifier.width(150.dp),
                        painter = painterResource(id = R.drawable.wzqxj),
                        contentDescription = null,
                        alignment = Alignment.Center,
                    )
                    Text(text = it.title)
                    Text(text = it.blurb)
                    Text(text = it.image)
                }
            }
        }
    }
}


@Composable
fun muyu() {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTheme {
//        Greeting()
//        h1()
//        c1()
//        muyu()
        ShowMessage()
    }
}