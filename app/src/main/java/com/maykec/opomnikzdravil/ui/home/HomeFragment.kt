package com.maykec.opomnikzdravil.ui.home

import android.content.Intent
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maykec.opomnikzdravil.Constants
import com.maykec.opomnikzdravil.R
import com.maykec.opomnikzdravil.model.Reminder
import com.maykec.opomnikzdravil.ui.addMeds.AddMedsActivity
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),
    androidx.lifecycle.Observer<ArrayList<Reminder>>,
    View.OnClickListener{
    companion object {
        const val TAG = "HomeFragment"
    }

    // Model to retain data
    private val homeViewModel: HomeViewModel by activityViewModels()

    // UI elements
    private lateinit var btAddMedicals: Button
    private lateinit var rwEventList: RecyclerView

    // adapter
    private lateinit var medsListAdapter: MedsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        btAddMedicals = root.findViewById(R.id.bt_dodaj_zdravilo)
        btAddMedicals.setOnClickListener(this)

        rwEventList = root.findViewById(R.id.rw_events_list)
        rwEventList.layoutManager = LinearLayoutManager(context)
        rwEventList.setHasFixedSize(true)

        // set observer for events
        homeViewModel.eventList.observe(viewLifecycleOwner, this)

        return root
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadEvents()
    }

    private fun openAddNewMedicationActivity() {
        activity?.let{
            val intent = Intent (it, AddMedsActivity::class.java)
            it.startActivity(intent)
        }
    }

    override fun onChanged(data: ArrayList<Reminder>?) {
        if (data != null && data.size > 0) {
            data.reverse() // reverse array list to show most recently added reminders on top
            medsListAdapter = MedsListAdapter(data)
            rwEventList.adapter = medsListAdapter
        }
    }

    override fun onClick(button: View?) {
        when (button!!.id) {
            R.id.bt_dodaj_zdravilo -> openAddNewMedicationActivity()
        }
    }
}

