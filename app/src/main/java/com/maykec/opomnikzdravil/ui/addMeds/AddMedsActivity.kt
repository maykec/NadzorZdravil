package com.maykec.opomnikzdravil.ui.addMeds

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.maykec.opomnikzdravil.R
import org.w3c.dom.Text
import java.util.*

class AddMedsActivity : AppCompatActivity(),
    TimePicker.OnTimeChangedListener,
    CompoundButton.OnCheckedChangeListener,
    TextView.OnEditorActionListener, androidx.lifecycle.Observer<Boolean>,
    View.OnClickListener{
    companion object {
        const val TAG = "AddMedsActivity"
    }
    // ViewModel to retain data
    private val model: AddMedsModel by viewModels()

    // UI elements
    private lateinit var etMedsName: EditText
    private lateinit var cbEveryDay: CheckBox
    private lateinit var tpMedsTimePicker: TimePicker
    private lateinit var btSaveMeds: Button


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_meds_activity)

        etMedsName = findViewById(R.id.et_meds_name)
        etMedsName.setOnEditorActionListener(this)

        cbEveryDay = findViewById(R.id.cb_evey_day)
        cbEveryDay.setOnCheckedChangeListener(this)

        tpMedsTimePicker = findViewById(R.id.meds_time_picker)
        tpMedsTimePicker.setOnTimeChangedListener(this)

        btSaveMeds = findViewById(R.id.bt_save_meds)
        btSaveMeds.isEnabled = false //TODO in inital state meds name is empty We dont want to store empty meds
        btSaveMeds.setOnClickListener(this)

        //prevent storing meds with no name
        model.validInput.observe(this, this)

    }

    override fun onTimeChanged(view: TimePicker?, hour: Int, minute: Int) {
        model.setTimeToTake(hour, minute)
    }

    override fun onCheckedChanged(view: CompoundButton?, checked: Boolean) {
       model.setTakeEveryDay(checked)
    }

    override fun onEditorAction(view: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        model.setMedsName(view!!.text.toString())
        return false
    }

    override fun onChanged(isValidated: Boolean?) {
        btSaveMeds.isEnabled = isValidated!!
    }

    override fun onClick(button: View?) {
        // we can have one onClick listener for multiple buttons.
        when (button!!.id) {
            R.id.bt_save_meds -> model.saveMedsToFirebase()
        }
    }
}