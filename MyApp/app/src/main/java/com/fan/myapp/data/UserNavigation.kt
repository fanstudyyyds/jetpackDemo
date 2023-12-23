package com.fan.myapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

class UserNavigation {
    constructor(name: String, img: ImageVector?) {
        this.name = name
        this.img = img
    }


    var name: String = "";
    var img: ImageVector? = null;

    companion object instance {
        fun getUserNavigation(): List<UserNavigation> {
            var list: ArrayList<UserNavigation> = arrayListOf<UserNavigation>(
                UserNavigation("首页", Icons.Default.Home),
                UserNavigation("消息", Icons.Default.Email),
                UserNavigation("我的", Icons.Default.AccountCircle)
            )
            return list
        }
    }


}

