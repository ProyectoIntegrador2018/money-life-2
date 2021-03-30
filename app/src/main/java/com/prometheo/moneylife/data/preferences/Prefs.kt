package com.prometheo.moneylife.data.preferences

interface Prefs {
    var userId: Int
    var userName: String?
    var password: String?
    val userSignedIn: Boolean
}