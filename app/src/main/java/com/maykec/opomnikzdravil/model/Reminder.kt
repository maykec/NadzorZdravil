package com.maykec.opomnikzdravil.model

class Reminder (val id: String, val medsName: String,  val takeEveryDay: Boolean, val timeToTake: String ) {
    constructor(): this ("", "",true,"")
}