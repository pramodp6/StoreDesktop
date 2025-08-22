package com.pramod.store

import java.util.prefs.Preferences

object PrefManager {

    // Application root node
    private val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)

    // Put String value
    fun putString(key: String, value: String) {
        prefs.put(key, value)
    }

    // Get String value
    fun getString(key: String, default: String = ""): String {
        return prefs.get(key, default)
    }

    // Put Int value
    fun putInt(key: String, value: Int) {
        prefs.putInt(key, value)
    }

    // Get Int value
    fun getInt(key: String, default: Int = 0): Int {
        return prefs.getInt(key, default)
    }

    // Put Boolean value
    fun putBoolean(key: String, value: Boolean) {
        prefs.putBoolean(key, value)
    }

    // Get Boolean value
    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return prefs.getBoolean(key, default)
    }

    // Remove a key
    fun remove(key: String) {
        prefs.remove(key)
    }

    // Clear all preferences
    fun clear() {
        prefs.clear()
    }
}
