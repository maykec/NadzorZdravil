package com.maykec.opomnikzdravil.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maykec.opomnikzdravil.Constants
import com.maykec.opomnikzdravil.model.Reminder

class HomeViewModel : ViewModel() {
    var database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_DATA_BASE_NAME)
    var eventList = MutableLiveData<ArrayList<Reminder>>()

    fun loadEvents() {
        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                //TODO should show error notification
                Log.d(HomeFragment.TAG, "onCancelled")
            }

            override fun onDataChange(snaphost: DataSnapshot) {
                if (snaphost.exists()) {
                    val eventsArr = arrayListOf<Reminder>()
                    for (data in snaphost.children) {
                        eventsArr.add(data.getValue(Reminder::class.java)!!)
                    }
                    eventList.value = eventsArr
                }
            }
        })
    }
}