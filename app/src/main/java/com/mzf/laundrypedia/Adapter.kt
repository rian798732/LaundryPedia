package com.mzf.laundrypedia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Pickup> )
    : ArrayAdapter<Pickup>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textAlamat = view.findViewById<TextView>(R.id.textAlamat)
        val textTime = view.findViewById<TextView>(R.id.textTime)
        val textType = view.findViewById<TextView>(R.id.textType)
        val textBerat = view.findViewById<TextView>(R.id.textBerat)
        val textNoTelp = view.findViewById<TextView>(R.id.textNomorTelp)

        val pickup = list[position]

        textNama.text = pickup.nama
        textAlamat.text = pickup.alamat
        textTime.text = pickup.time_pickup
        textType.text = pickup.type
        textBerat.text = pickup.count
        textNoTelp.text = pickup.telp

        return view
    }
}