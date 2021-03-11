package com.prometheo.moneylife.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefsImp @Inject constructor(
        @ApplicationContext context : Context
): Prefs {
    private val sharedPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    override var userName: String?
        get() = sharedPrefs.getString("userName", null)
        set(value) {
            sharedPrefs.edit { putString("userName", value) }
        }

    override var password: String?
        get() = sharedPrefs.getString("password", null)
        set(value) {
            sharedPrefs.edit { putString("password", value) }
        }
}