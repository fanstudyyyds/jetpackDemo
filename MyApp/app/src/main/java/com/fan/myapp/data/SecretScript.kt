package com.fan.myapp.data

import com.fan.myapp.R

class SecretScript {


    var id: String = "";
    var title: String = "";
    var blurb: String = "";
    var image: String = "";

    constructor(id: String, title: String, blurb: String, image: String) {
        this.id = id
        this.title = title
        this.blurb = blurb
        this.image = image
    }


    companion object instance {
        fun gteSecretScript(): List<SecretScript> {
            var list: ArrayList<SecretScript> = arrayListOf(
                SecretScript("1", "六臂天尸王", "力道", ""),
                SecretScript("2", "毛民", "", ""),
                SecretScript("3", "引魂入梦", "仙道杀招--引魂入梦", ""),
                SecretScript("4", "逆流护身印", "", ""),
                SecretScript("5", "五指拳心剑", "", ""),
                SecretScript("6", "春秋蝉", "", ""),
                SecretScript("7", "六", "", ""),
                SecretScript("8", "六", "", ""),
                SecretScript("9", "六", "", ""),
            )
            return list
        }
    }
}