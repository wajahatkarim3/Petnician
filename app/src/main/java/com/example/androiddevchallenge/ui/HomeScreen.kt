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
package com.example.androiddevchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.CatModel
import com.example.androiddevchallenge.model.PetCategoryModel
import com.example.androiddevchallenge.ui.AnimatedBottomNavigation

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier.padding(all = 7.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            )
        },
        content = {
            HomeScreenContent()
        },
        bottomBar = {
            AnimatedBottomNavigation()
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchBox()
        Spacer(modifier = Modifier.height(20.dp))
        PetCategories()
        Spacer(modifier = Modifier.height(10.dp))
        NewPets()
    }
}

@Composable
fun SearchBox() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        Text(
            text = "Find your",
            style = TextStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        Text(
            text = "Lovely pet anywhere",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(top = 5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier
                .offset(x = 20.dp)
                .fillMaxWidth(),
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(
                topStartPercent = 50,
                bottomStartPercent = 50
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(start = 10.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PetCategories() {

    val petCategoriesList = getPetCategories()

    Text(
        text = "Pet Category",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        cells = GridCells.Fixed(2),
    ) {
        items(
            items = petCategoriesList,
            itemContent = { item ->
                PetCategoryItem(petCategory = item)
            }
        )
    }
}

@Composable
fun PetCategoryItem(petCategory: PetCategoryModel) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable { }
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = petCategory.icon),
                contentDescription = petCategory.categoryName,
                modifier = Modifier
                    .background(
                        color = petCategory.backgroundColor,
                        shape = CircleShape
                    )
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = petCategory.categoryName,
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Total of ${petCategory.count}",
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NewPets() {
    Text(
        text = "Newest Pets",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = getCats(),
            itemContent = { item ->
                CatItem(item)
            }
        )
    }
}

@Composable
fun CatItem(catModel: CatModel) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
            }
    ) {
        Image(
            painter = painterResource(id = catModel.image),
            contentScale = ContentScale.Crop,
            contentDescription = catModel.name,
            modifier = Modifier
                .width(200.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(15.dp)),
        )

        PetTypeTag(catModel.type)

        Text(
            text = catModel.name,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = Color(0xAA000000),
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                )
                .requiredWidth(200.dp)
                .align(alignment = Alignment.BottomCenter)
                .padding(5.dp)
        )
    }
}

@Composable
fun PetTypeTag(type: String) {
    Text(
        text = type,
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, bottom = 5.dp)
            .background(
                color = if (type == "Mating") Color(0xFFD8F1FE) else Color(0xFFFBE9CF),
                shape = RoundedCornerShape(percent = 50)
            )
            .padding(start = 7.dp, end = 7.dp, top = 3.dp, bottom = 3.dp),
        style = TextStyle(
            color = if (type == "Mating") Color(0xFF43B8F5) else Color(0xFFF7B14F),
            fontWeight = FontWeight.Bold
        )
    )
}

fun getPetCategories(): List<PetCategoryModel> {
    return listOf(
        PetCategoryModel(
            categoryName = "Cats",
            count = 210,
            icon = R.drawable.ic_category_cat,
            backgroundColor = Color(0x88f5eabf)
        ),
        PetCategoryModel(
            categoryName = "Dogs",
            count = 340,
            icon = R.drawable.ic_category_dog,
            backgroundColor = Color(0x88dec1ab)
        ),
        PetCategoryModel(
            categoryName = "Bunnies",
            count = 56,
            icon = R.drawable.ic_category_bunny,
            backgroundColor = Color(0x88f5cedb)
        ),
        PetCategoryModel(
            categoryName = "Birds",
            count = 90,
            icon = R.drawable.ic_category_bird,
            backgroundColor = Color(0x88f5e2d7)
        )
    )
}

fun getCats(): List<CatModel> {
    return listOf(
        CatModel(
            name = "Bella",
            type = "Mating",
            location = "California",
            imageUrl = "https://purr.objects-us-east-1.dream.io/i/tumblr_lrj43uSQa01r2q14xo1_500.jpg",
            image = R.drawable.cat1,
            liked = true,
            age = "3 months",
            color = "Black and Gray",
            weight = "11 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Kitty",
            type = "Adoption",
            location = "New York",
            imageUrl = "https://purr.objects-us-east-1.dream.io/i/XueQd.jpg",
            image = R.drawable.cat2,
            liked = true,
            age = "5 months",
            color = "Golden",
            weight = "14 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Lily",
            type = "Mating",
            location = "California",
            imageUrl = "https://static.scientificamerican.com/sciam/cache/file/92E141F8-36E4-4331-BB2EE42AC8674DD3_source.jpg",
            image = R.drawable.cat3,
            liked = true,
            age = "4 months",
            color = "Brown",
            weight = "10 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Charlie",
            type = "Mating",
            location = "California",
            imageUrl = "https://static.toiimg.com/photo/msid-67586673/67586673.jpg?3918697",
            image = R.drawable.cat4,
            liked = true,
            age = "3 months",
            color = "Golden",
            weight = "12 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Lucy",
            type = "Adoption",
            location = "California",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/6/69/June_odd-eyed-cat_cropped.jpg",
            image = R.drawable.cat5,
            liked = true,
            age = "7 months",
            color = "White",
            weight = "19 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Leo",
            type = "Mating",
            location = "California",
            imageUrl = "https://purr.objects-us-east-1.dream.io/i/tumblr_lrj43uSQa01r2q14xo1_500.jpg",
            image = R.drawable.cat6,
            liked = true,
            age = "3 months",
            color = "Gray",
            weight = "11 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Milo",
            type = "Adoption",
            location = "California",
            imageUrl = "https://ichef.bbci.co.uk/news/1024/cpsprodpb/151AB/production/_111434468_gettyimages-1143489763.jpg",
            image = R.drawable.cat7,
            liked = true,
            age = "3 months",
            color = "Dark Brown",
            weight = "11 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Sadie",
            type = "Adoption",
            location = "California",
            imageUrl = "https://cdn.mos.cms.futurecdn.net/yzV5i2F35i9RozwSeFLPJV.jpg",
            image = R.drawable.cat1,
            liked = true,
            age = "3 months",
            color = "Golden and White",
            weight = "11 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        ),
        CatModel(
            name = "Simba",
            type = "Mating",
            location = "California",
            imageUrl = "https://purr.objects-us-east-1.dream.io/i/tumblr_lrj43uSQa01r2q14xo1_500.jpg",
            image = R.drawable.cat2,
            liked = true,
            age = "3 months",
            color = "Gray",
            weight = "11 kg",
            ownerName = "Nannie",
            ownerImageUrl = "",
            story = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sodales, odio vel malesuada suscipit, velit mi aliquet nunc, ac aliquam lacus arcu ut nibh. Quisque ac accumsan lorem, vitae congue risus. Nulla felis dui, venenatis ut vestibulum et, interdum a risus. Nulla vitae ligula cursus, dapibus mi sit amet, convallis orci. Nunc in libero a eros interdum congue. Maecenas a justo leo. Sed et bibendum sapien. Suspendisse maximus augue non magna varius, vitae fermentum quam volutpat. Quisque non pellentesque ipsum. Proin tempor mi sed tristique dignissim. Nam vitae mauris ut magna sagittis vehicula. Morbi sed dignissim diam. "
        )
    )
}
