package blur.tech.candidate.core

import android.content.SharedPreferences
import blur.tech.candidate.core.models.User
import com.google.gson.Gson
import org.json.JSONObject

class PreferencesApi {
    companion object {
        val sharedPreferencesName = "blur.tech.candidate"

        enum class PrefNames { USER, JWT }

        fun getJwt(prefs: SharedPreferences) = prefs.getString(Companion.PrefNames.JWT.name, null)

        fun setJwt(prefs: SharedPreferences, jwt: String) {
            prefs.edit().putString(Companion.PrefNames.JWT.name, jwt).apply()
        }

        fun setUser(prefs: SharedPreferences, user: User) {
            val gson = Gson()
            val json = gson.toJson(user)
            prefs.edit().putString(Companion.PrefNames.USER.name, json).apply()
        }

        fun getUser(prefs: SharedPreferences): User? {
            val gson = Gson()
            val jsonString = prefs.getString(Companion.PrefNames.USER.name, null)
            if (jsonString.isNullOrBlank()) return null

            val jsonRoot = JSONObject(jsonString)
            return gson.fromJson(jsonRoot.toString(), User::class.java)
        }

        fun delData(prefs: SharedPreferences) {
            prefs.edit()
                .clear()
                .apply()
        }
    }
}