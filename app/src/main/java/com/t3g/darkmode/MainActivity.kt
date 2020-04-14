package com.t3g.darkmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    private var xyz: Switch? = null
    internal lateinit var sharedpref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedpref = SharedPref(this)
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme)
        } else
            setTheme(R.style.AppTheme)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xyz = findViewById<View>(R.id.enableDark) as Switch?
        if (sharedpref.loadNightModeState() == true) {
            xyz!!.isChecked = true
        }
        xyz!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedpref.setNightModeState(true)
                restartApp()
            } else {
                sharedpref.setNightModeState(false)
                restartApp()
            }
        }
    }

    fun restartApp() {
        val i = Intent (getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        finish()
    }

}