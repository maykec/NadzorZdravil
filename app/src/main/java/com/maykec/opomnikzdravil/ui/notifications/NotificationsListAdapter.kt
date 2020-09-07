package com.maykec.opomnikzdravil.ui.notifications

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.maykec.opomnikzdravil.Constants
import com.maykec.opomnikzdravil.R
import com.maykec.opomnikzdravil.extensions.inflate
import com.maykec.opomnikzdravil.model.Notification
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationsListAdapter(private val data: ArrayList<Notification>) :
    RecyclerView.Adapter<NotificationsListAdapter.NotificationItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationItemHolder {
        val rowView = parent.inflate(R.layout.rc_notification_list_item, false)
        return NotificationItemHolder(rowView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotificationItemHolder, position: Int) {
        holder.bindData(data[position])
    }

    class NotificationItemHolder(view:  View): RecyclerView.ViewHolder(view){

        private val tvNotificationName: TextView = view.findViewById(R.id.tv_notification_name)
        private val tvStatus: TextView = view.findViewById(R.id.tv_status)


        @SuppressLint("StringFormatMatches")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bindData(data: Notification) {
            tvNotificationName.text = data.medsName

            if (data.timestampConfirmed > 0) {
                val minDiff = getTimeDifferenceInMin(data.timestampCreated, data.timestampConfirmed)
                if (minDiff > 0) {
                    val text = tvStatus.resources.getString(R.string.lbl_meds_received_late, minDiff)
                    tvStatus.text = text
                    tvStatus.setTextColor(tvStatus.context.getColor(R.color.warning))
                }
            } else {
                tvStatus.setText(R.string.lbl_meds_not_received)
                tvStatus.setTextColor(tvStatus.context.getColor(R.color.alert))
            }
        }

        @SuppressLint("SimpleDateFormat")
        private fun getDateFormatted(milliseconds: Long): String {
            val dateFormatter = SimpleDateFormat(Constants.LIST_ITEM_DATE_TIME_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliseconds
            return dateFormatter.format(calendar.time)

        }

        private fun getTimeDifferenceInMin(starTime: Long, endTime: Long): Long {
            return TimeUnit.MILLISECONDS.toMinutes(endTime - starTime)
        }
    }
}