package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes

data class Adoption (
    val id: String,
    val name: String,
    val age: Int,
    val from: String,
    @DrawableRes val image: Int,
)