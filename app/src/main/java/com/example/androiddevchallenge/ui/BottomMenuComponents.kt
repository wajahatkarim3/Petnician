/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedBottomNavigation() {

    val bookmarkBgColor = Color(0xFFDBECFB)
    val bookmarkTextColor = Color(0xFF55A2EF)
    val homeBgColor = Color(0xFFE6E4E6)
    val homeTextColor = Color(0xFF878587)
    val likeBgColor = Color(0xFFFCE1EC)
    val likeTextColor = Color(0xFFF47CA7)
    val settingsBgColor = Color(0xFFEFE8E7)
    val settingsTextColor = Color(0xFFB99991)

    var bottomOptions = ArrayList<BottomMenuOption>()
    bottomOptions.add(BottomMenuOption(bgColor = homeBgColor, textColor = homeTextColor, text = "Home", icon = Icons.Default.Home))
    bottomOptions.add(BottomMenuOption(bgColor = likeBgColor, textColor = likeTextColor, text = "Like", icon = Icons.Default.FavoriteBorder))
    bottomOptions.add(BottomMenuOption(bgColor = bookmarkBgColor, textColor = bookmarkTextColor, text = "Cart", icon = Icons.Default.ShoppingCart))
    bottomOptions.add(BottomMenuOption(bgColor = settingsBgColor, textColor = settingsTextColor, text = "Settings", icon = Icons.Default.Settings))

    var selectedIndex = remember { mutableStateOf(0) }

    Card(
        backgroundColor = Color.White,
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 0 until bottomOptions.size) {
                BottomMenuButton(
                    selected = i == selectedIndex.value,
                    bgColor = bottomOptions[i].bgColor,
                    textColor = bottomOptions[i].textColor,
                    text = bottomOptions[i].text,
                    icon = bottomOptions[i].icon
                ) {
                    selectedIndex.value = i
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun BottomMenuButton(modifier: Modifier = Modifier, selected: Boolean, bgColor: Color, textColor: Color, text: String, icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
            .background(color = if (selected) bgColor else Color.Transparent, shape = RoundedCornerShape(percent = 50))
            .clickable(onClick = onClick)
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp).size(20.dp),
                tint = if (selected) textColor else Color.Black
            )
            AnimatedVisibility(
                selected,
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

data class BottomMenuOption(
    val bgColor: Color,
    val textColor: Color,
    val text: String,
    val icon: ImageVector
)
