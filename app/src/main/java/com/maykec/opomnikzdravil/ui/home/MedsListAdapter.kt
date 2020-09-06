package com.maykec.opomnikzdravil.ui.home

import android.util.Log
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maykec.opomnikzdravil.R
import com.maykec.opomnikzdravil.extensions.inflate
import com.maykec.opomnikzdravil.model.Reminder
import org.w3c.dom.Text

class MedsListAdapter(val data: ArrayList<Reminder>) : RecyclerView.Adapter<MedsListAdapter.MedsNameHolder>() {
    companion object {
        const val TAG = "MedsListAdapter"
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): MedsListAdapter.MedsNameHolder {
        Log.d(TAG, "onCreateViewHolder")
        val rowView = parent.inflate(R.layout.rc_event_list_item, false)
        return MedsNameHolder(rowView)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount(): " + data.size)
        return data.size
    }

    override fun onBindViewHolder(holder: MedsListAdapter.MedsNameHolder, position: Int) {
        holder.bindData(data.get(position))
    }

    class MedsNameHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvTitleName: TextView
        private val cbEveryDay: CheckBox
        private val tvTime: TextView

        init {
            tvTitleName = view.findViewById(R.id.tv_meds_name)
            cbEveryDay = view.findViewById(R.id.cb_evey_day_item)
            tvTime = view.findViewById(R.id.tv_time)
        }

        fun bindData(data: Reminder) {
            tvTitleName.text = data.medsName
            cbEveryDay.isChecked = data.takeEveryDay
            tvTime.text = data.timeToTake
        }

    }
}