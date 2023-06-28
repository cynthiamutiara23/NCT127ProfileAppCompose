package com.dicoding.mycomposeapp.model

data class Member(
    val id: Long,
    val name: String,
    val birthdate: String,
    val description: String,
    val position: String,
    val image: Int
)