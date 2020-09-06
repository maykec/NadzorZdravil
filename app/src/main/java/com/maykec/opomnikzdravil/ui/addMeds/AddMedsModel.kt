package com.maykec.opomnikzdravil.ui.addMeds

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.maykec.opomnikzdravil.Constants
import com.maykec.opomnikzdravil.model.Reminder
import java.sql.Time

class AddMedsModel : ViewModel() {
    companion object {
        const val TAG = "AddMedsModel"
    }

    val medsName = MutableLiveData<String>()
    val takeEveryDay = MutableLiveData<Boolean>()
    val timeToTake = MutableLiveData<String>()
    val validInput = MutableLiveData<Boolean>()

    fun saveMedsToFirebase() {
        var database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_DATA_BASE_NAME)
        val reminderId = database.push().key as String
        val reminder = Reminder(id= reminderId, medsName = this.medsName.value!!, takeEveryDay = this.takeEveryDay.value!!, timeToTake = this.timeToTake.value!!)
        database.child(reminderId).setValue(reminder).addOnCompleteListener {
            Log.d(TAG, "addOnCompleteListener(): Stored to firebase" + reminderId)
        }
    }

    fun setTimeToTake(hour: Int, minute: Int) {
        timeToTake.value = hour.toString() + ":" + minute.toString() //TODO not best solution
    }

    fun setTakeEveryDay(takeEveryDay: Boolean) {
        this.takeEveryDay.value = takeEveryDay
    }

    fun setMedsName(name: String) {
        medsName.value = name
        validateInput()
    }

    fun validateInput() {
        validInput.value = medsName.value?.length!! >= 2
    }
}

