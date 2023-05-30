package com.thryve.browsrapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Organization(
    @PrimaryKey
    val id: Int,
    val name: String,
    val avatarUrl: String,
    var isFavorite: Boolean,
)