package com.example.androiddevchallenge

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.PetCategoryModel

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
                 modifier = Modifier.padding(all = 10.dp),
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
            Text(text = "Bottom")
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchBox()
        Spacer(modifier = Modifier.height(30.dp))
        PetCategories()
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
                fontSize = 24.sp
            )
        )
        Text(
            text = "Lovely pet anywhere",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
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
    Spacer(modifier = Modifier.height(10.dp))

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
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            ).padding(10.dp)
            .clickable {  },
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
                    .padding(10.dp)
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