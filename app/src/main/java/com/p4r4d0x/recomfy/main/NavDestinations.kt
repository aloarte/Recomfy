package com.p4r4d0x.recomfy.main

sealed class NavDestinations(val route: String) {
    object Search : NavDestinations("search")
    object Result : NavDestinations("result")
    object Detail : NavDestinations("detail")

    companion object{
        const val ITEM_NAME_KEY = "itemName"
    }

}
