package com.example.mvvmpractice.model
//Model to store popular Tv show details
data class Result (
    val backdrop_path: String,
    val name: String,
    val overview: String,
    val id: String,
    val languages: List<String>
        )