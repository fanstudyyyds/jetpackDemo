package com.fan.myapp

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Chat {


}


class Message {

    var id: String = "";
    var author: String = "";
    var content: String = "";
    var type: Int = 0;

    constructor(id: String, author: String, content: String, type: Int) {
        this.id = id
        this.author = author
        this.content = content
        this.type = type
    }
}

@Composable
fun MessageRight(msg: Message) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        //给左边文字一个权重，避免文字过多，让右边图像无法显示
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Right
            )

            Spacer(modifier = Modifier.height(10.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                targetValue = if (isExpanded)
                    Color.Green
                else
                    MaterialTheme.colorScheme.surface, label = ""
            )

            Surface(
                shape = RectangleShape,
                shadowElevation = 1.dp,
                tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.content,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis

                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.wzqxj),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clickable { CircleShape }
                .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape)

        )
    }
}


@Composable
fun MessageLeft(msg: Message) {
    Row(
        modifier = Modifier.padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        //图像
        Image(
            painter = painterResource(id = R.drawable.wzqxj),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)//图像大小
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape)

        )

        //左右间隔
        Spacer(modifier = Modifier.width(10.dp))

        //重组：可组合函数可以使用 remember 将本地状态存储在内存中，并跟踪传递给 mutableStateOf 的值的变化。
        // 该值更新时，系统会自动重新绘制使用此状态的可组合项（及其子项）
        //通过使用 Compose 的状态 API（如 remember 和 mutableStateOf），系统会在状态发生任何变化时自动更新界面。
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded)
                Color.Cyan
            else
                MaterialTheme.colorScheme.surface, label = ""
        )

        Column(Modifier.weight(1f)) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )

            //上下间隔
            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RectangleShape,
                shadowElevation = 1.dp,
                tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.content,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}


val msgList: ArrayList<Message> = arrayListOf(
    Message("1", "hhh", "你好", 0),
    Message("2", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("3", "hhh", "你好", 0),
    Message("4", "hhh", "你好", 1),
)


@Preview(showBackground = true)
@Composable
fun ShowMessage() {
    var list: ArrayList<Message> by remember { mutableStateOf(msgList) }

    //竖向列表
    Column() {
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(items = list) { index, item ->
                if (item.type == 0)
                    MessageLeft(msg = item)
                else
                    MessageRight(msg = item)
            }

        }

        chat {
            list.add(
                Message(
                    "hhh", "hhh", "怒骂uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" +
                            "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu", 1
                )
            )
//            value.removeAt(0);
            Log.d("===============", list.size.toString())

        }
    }

}

@Composable
fun chat(value: () -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            //行中元素的对齐方式，需要设置fillMaxWidth，不然只有内容大小，无法调节元素对齐
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedTextField(
                singleLine = false,
                maxLines = 10,
                value = text,
                modifier = Modifier.width(250.dp),
                onValueChange = {
                    text = it
                },
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    text = ""
                    value()
                }) {
                Text(text = "发送")
            }
        }
    }


}

