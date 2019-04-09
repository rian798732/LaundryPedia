package com.mzf.laundrypedia

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerButtonInLoginAct.setOnClickListener{
            navigasiKeRegister()
        }

        loginButtonInLoginAct.setOnClickListener {

            val progressDialog = ProgressDialog(
                this,
                R.style.Theme_MaterialComponents_Light_Dialog
            )
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Tunggu Sebentar...")
            progressDialog.show()

            val email = inputEmailInLoginAct.text.toString()
            val password = inputPasswordInLoginAct.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val progressDialog = ProgressDialog(
                            this,
                            R.style.Theme_MaterialComponents_Light_Dialog
                        )

                        progressDialog.hide()
                        navigasiKeHome()
                    }
                }
                .addOnFailureListener {
                    val progressDialog = ProgressDialog(
                        this,
                        R.style.Theme_MaterialComponents_Light_Dialog
                    )
                    Log.d("Main", "Failed Login: ${it.message}")
                    Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                    progressDialog.hide()

                }
        }
    }

    private fun navigasiKeRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun navigasiKeHome() {
        val intent = Intent(this, PrimaryActivity::class.java)
        startActivity(intent)
    }
}
