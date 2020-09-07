package com.maykec.opomnikzdravil.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maykec.opomnikzdravil.R
import com.maykec.opomnikzdravil.model.Notification
import com.maykec.opomnikzdravil.model.Reminder

class NotificationsFragment : Fragment(),
    androidx.lifecycle.Observer<ArrayList<Notification>>{
    companion object {
        const val TAG = "NotificationsFragment"
    }

    // modelview
    private val notificationsViewModel: NotificationsViewModel by activityViewModels()

    // ui elements
    private lateinit var rcNotificationsList: RecyclerView

    // adapter
    private lateinit var notificationsAdapter: NotificationsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        rcNotificationsList = root.findViewById(R.id.rc_notifications_list)
        rcNotificationsList.layoutManager = LinearLayoutManager(context)
        rcNotificationsList.setHasFixedSize(true)

        notificationsViewModel.notificationList.observe(viewLifecycleOwner, this)
        return root
    }

    override fun onResume() {
        super.onResume()
        notificationsViewModel.loadNotifications()
    }

    override fun onChanged(data: ArrayList<Notification>?) {
        if (data != null && data.size > 0) {
            data.reverse() // reverse array list to show most recently added notifications on top
            notificationsAdapter = NotificationsListAdapter(data)
            rcNotificationsList.adapter = notificationsAdapter
        }
    }
}