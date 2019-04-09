package com.mzf.laundrypedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            navigasiKeLogin()
        }
    }

    private fun navigasiKeLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
