package ui.reddit.sk.materialreddit.Core.Services

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by sk_macbook_pro_new on 2/8/18.
 */

class SharedPrefRecorder(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun savePreferencesNum(key: String, value: Int?) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value!!)
        editor.apply()
    }

    fun savePreferencesNumL(key: String, value: Long?) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value!!)
        editor.apply()
    }

    fun savePreferencesB(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun savePreferencesS(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
        editor.clear()
    }

}
