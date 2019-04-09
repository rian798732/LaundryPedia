package com.mzf.laundrypedia

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            saveData()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun saveData() {
        val nama = inputName.text.toString()
        val email = inputEmail.text.toString()
        val alamat = inputAddress.text.toString()
        val password = inputPassword.text.toString()

        when {
            nama.isEmpty() -> inputName.error = "Nama tidak boleh kosong"
            email.isEmpty() -> inputEmail.error = "Email tidak boleh kosong"
            password.isEmpty() -> inputPassword.error = "Password tidak boleh kosong"
            alamat.isEmpty() -> inputAddress.error = "Alamat tidak boleh kosong"
            else -> {
                val progressDialog = ProgressDialog(
                    this,
                    R.style.Theme_MaterialComponents_Light_Dialog
                )
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Tunggu Sebentar...")
                progressDialog.show()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }

                val intent = Intent(this, PrimaryActivity::class.java)

                progressDialog.hide()
                startActivity(intent)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}

