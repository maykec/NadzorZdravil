package com.maykec.opomnikzdravil.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maykec.opomnikzdravil.Constants
import com.maykec.opomnikzdravil.model.Notification
import com.maykec.opomnikzdravil.model.Reminder
import com.maykec.opomnikzdravil.ui.home.HomeFragment

class NotificationsViewModel : ViewModel() {
    private var database = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_DATA_BASE_NOTIFICATIONS_NAME)
    var notificationList = MutableLiveData<ArrayList<Notification>>()

    fun loadNotifications() {
        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(err: DatabaseError) {
                //TODO should show error to user
                Log.d(NotificationsFragment.TAG, "onCancelled")
            }

            override fun onDataChange(snaphost: DataSnapshot) {
                if (snaphost.exists()) {
                    val eventsArr = arrayListOf<Notification>()
                    for (data in snaphost.children) {
                        eventsArr.add(data.getValue(Notification::class.java)!!)
                    }
                    notificationList.value = eventsArr
                }
            }

        })
    }
}