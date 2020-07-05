package com.androidshowtime.sharedprefs

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        /*<string name="preference_file_key">com.androidshowtime.sharedprefs.
        PREFERENCE_FILE_KEY</string>
        */


        //list
        val listOfFriends = listOf("Stevoo", "Alex", "Patoo")

        //getting reference to the SharedPrefs file
        val sharedPrefs = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)


        /*GSON provide simple toJson() and fromJson() methods to
        convert Java objects to JSON and vice-versa*/
        val jsonList = Gson().toJson(listOfFriends)

        sharedPrefs
                .edit()
                .putString("list", jsonList)
                .apply()

        Timber.i("MyFriends are ${fetchYourList()}")
    }


    private fun fetchYourList(): List<String> {

        //declare a local list variable
        val listOfFriends: List<String>

        //get SharedPref reference
        val prefs = this.getSharedPreferences(
            getString(R.string.preference_file_key), (Context.MODE_PRIVATE))


        //retrieve the inserted Json Object using the key
        val json = prefs.getString("list", null)

        //setting listOfFriends using when statement
        listOfFriends = when {
            json.isNullOrEmpty() -> emptyList()
            else -> Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)

        }

        return listOfFriends

    }


}



