package com.example.androiddevchallenge.model

import androidx.annotation.IdRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class PetCategoryModel(
    val categoryName: String,
    val count: Int,
    val backgroundColor: Color,
    @IdRes val icon: Int
)