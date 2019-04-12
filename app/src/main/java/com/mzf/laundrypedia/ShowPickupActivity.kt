package com.mzf.laundrypedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class ShowPickupActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Pickup>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_pickup)

        ref = FirebaseDatabase.getInstance().getReference("PICKUP")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val pickup = h.getValue(Pickup::class.java)
                        list.add(pickup!!)
                    }
                    val adapter = Adapter(applicationContext,R.layout.pickup_list,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
