package com.p4r4d0x.recomfy.main

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Result : Screen("result")
    object Detail : Screen("detail")

}
