package com.app.rickandmorty.models

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
) : java.io.Serializable