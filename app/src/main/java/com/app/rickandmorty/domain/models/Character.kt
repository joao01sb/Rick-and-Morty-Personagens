package com.app.rickandmorty.domain.models

import com.app.rickandmorty.data.local.entitys.CharacterEntity
import com.app.rickandmorty.data.local.entitys.FavoriteCharacterEntity
import com.app.rickandmorty.domain.models.Character

data class Character(
    val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: CharacterLocation?,
    val location: CharacterLocation,
    val image: String?,
    val episode: List<String>?
)

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode!!,
    url = "",
    created = ""
)

fun Character.toFavoriteEntity() = FavoriteCharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    origin = origin?.name,
    location = location.name,
    image = image,
    episode = episode
)