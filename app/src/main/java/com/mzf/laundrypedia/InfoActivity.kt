package com.mzf.laundrypedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        ref = FirebaseDatabase.getInstance().getReference("PICKUP")

        buttonPickUp.setOnClickListener {
            savedata()
        }
    }
    private fun savedata() {
        val nama = inputName.text.toString()
        val alamat = inputPlaceAddress.text.toString()
        val timePickup = inputTimePickUp.text.toString()
        val type = inputType.text.toString()
        val count = inputCount.text.toString()
        val number = inputTelp.text.toString()
        val total = totalHarga.text.toString()

        val pickup = InsertPickup(nama,alamat,timePickup,type,count,number)
        val pickupId = ref.push().key.toString()

        ref.child(pickupId).setValue(pickup).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputName.setText("")
            inputPlaceAddress.setText("")
            inputTimePickUp.setText("")
            inputType.setText("")
            inputCount.setText("")
            inputTelp.setText("")

        }
    }
}
