package com.p4r4d0x.domain

enum class RecommendationType {
    movie,music,unknown;

    companion object{
        fun find(value:String) = values().find { it.name == value } ?: unknown
    }
}