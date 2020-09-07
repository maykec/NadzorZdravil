package com.maykec.opomnikzdravil.model

class Notification(val id: String, val medsName: String, val timestampCreated: Long, val timestampConfirmed: Long) {
    constructor(): this("", "", 0, 0)
}