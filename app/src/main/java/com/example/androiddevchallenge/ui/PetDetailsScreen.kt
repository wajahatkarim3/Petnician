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

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.PetTypeTag
import com.example.androiddevchallenge.model.PetModel

@Composable
fun PetDetailsScreen(petModel: PetModel, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pet Details")
                },
                backgroundColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.padding(all = 5.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            onBackPressed.invoke()
                        }
                    )
                }
            )
        },
        content = {
            PetDetailsScreenContent(petModel)
        },
        bottomBar = {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Adopt Me",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    )
}

@Composable
fun PetDetailsScreenContent(petModel: PetModel) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HeaderPhoto(petModel.image)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = petModel.name,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = petModel.location,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )

            PetTypeTag(type = petModel.type)
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp, 10.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoBox(infoKey = "Age", infoValue = petModel.age)
                InfoBox(infoKey = "Color", infoValue = petModel.color)
                InfoBox(infoKey = "Weight", infoValue = petModel.weight)
            }
        }

        item {
            Text(
                text = "Pet Story",
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
            Text(
                text = petModel.story,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun HeaderPhoto(@IdRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentScale = ContentScale.Crop,
        contentDescription = "Pet Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@Composable
fun InfoBox(infoKey: String, infoValue: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentWidth().padding(start = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = infoValue,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = infoKey,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            )
        }
    }
}
