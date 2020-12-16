
package com.example.app_sports.home_activity.fragments.new_activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.Model.ActivitiesModel.ActivitiesMetadata
import com.example.app_sports.R
import com.example.app_sports.home_activity.DatePickerFragment
import com.example.app_sports.login_activity.pickers.TimeSetListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.DoubleStream.builder

class NewActivityFragment : Fragment() {

	private lateinit var auth: FirebaseAuth
	private var db_ref = Firebase.database.getReference("")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_new_activity, container, false)
		auth = FirebaseAuth.getInstance()
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val sports = resources.getStringArray(R.array.Sports)
		val levels = resources.getStringArray(R.array.level)
		val sportSpinner = view.findViewById<Spinner>(R.id.new_activity_sport)
		val levelSpinner = view.findViewById<Spinner>(R.id.new_activity_level)
		val title_et = view.findViewById<EditText>(R.id.new_activity_title)
		val place_et = view.findViewById<EditText>(R.id.new_activity_place)
		val date_et = view.findViewById<EditText>(R.id.new_activity_date)
		val time_et = view.findViewById<EditText>(R.id.new_activity_time)
		val level_et = view.findViewById<Spinner>(R.id.new_activity_level)
		val add_btn = view.findViewById<Button>(R.id.new_activity_btn)
		val user = auth.currentUser
		val id = db_ref.push().key

		if (sportSpinner != null) {
			val sport_adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sports)
			sportSpinner.adapter = sport_adapter
		}

		if (levelSpinner != null) {
			val level_adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, levels)
			levelSpinner.adapter = level_adapter
		}

		date_et.setOnClickListener(View.OnClickListener {
			val datePickerDialog = DatePickerFragment(date_et)
//			val datePickerDialog = DatePickerDialog(requireContext(), setListener, 2020, 12, 1)
			datePickerDialog.show(parentFragmentManager, "Date Picker")
		})

		time_et.setOnClickListener(View.OnClickListener {
			val setListener = TimeSetListener(time_et)
			val timePickerDialog = TimePickerDialog(requireContext(), setListener, 12, 0, true)
		})

		add_btn.setOnClickListener(View.OnClickListener {
			if (user != null && id != null) {
				val meta = createActivityMetadata(user.toString()!!, id)
				val new_activity = ActivitiesData(
					meta.id,
					title_et.text.toString(),
					place_et.text.toString(),
					sportSpinner.selectedItem.toString(),
					date_et.text.toString(),
					time_et.text.toString(),
					level_et.selectedItem.toString(),
					meta
				)

				// TODO: 12/11/20 generate a random id for each activity
				db_ref.child("activities").child(id).setValue(new_activity)
			}
		})
	}

	private fun createActivityMetadata(user: String, id: String): ActivitiesMetadata {
		val currentDateTime = LocalDateTime.now()
		val currentDate = currentDateTime.format(DateTimeFormatter.ISO_DATE)
		val currentTime = currentDateTime.format(DateTimeFormatter.ISO_TIME)

		val meta = ActivitiesMetadata(
			id,
			user,
			currentDate,
			currentTime
		)
		return meta
	}
}