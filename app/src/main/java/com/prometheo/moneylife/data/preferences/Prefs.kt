package com.prometheo.moneylife.data.preferences

interface Prefs {
    var userName: String?
    var password: String?
    val userSignedIn: Boolean
}