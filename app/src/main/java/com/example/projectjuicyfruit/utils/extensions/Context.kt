package com.example.projectjuicyfruit.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import com.example.projectjuicyfruit.utils.SharedPreferencesHelper.Companion.DEFAULT_PREF_FILE_NAME

fun Context.getDefaultSharedPreferences(mode: Int): SharedPreferences =
  this.getSharedPreferences(
    DEFAULT_PREF_FILE_NAME,
    mode
  )
