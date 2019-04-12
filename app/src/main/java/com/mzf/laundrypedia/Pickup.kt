package com.mzf.laundrypedia

class Pickup(
    var nama : String,
    var alamat: String,
    var time_pickup: String,
    var type: String,
    var count: String,
    var total: String,
    var telp: String) {

    constructor() : this("", "", "", "", "","","") {

    }
}