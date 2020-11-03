package com.example.app_sports.login_activity.fragments.register

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.app_sports.Model.UserData
import com.example.app_sports.R
import com.example.app_sports.login_activity.pickers.SetListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CompleteProfileFragment : Fragment() {

	private val sportsList = arrayOf<String>("Bike", "Running", "Swimming", "Tennis", "BasketBall")
	lateinit var setListener: SetListener
	private lateinit var auth: FirebaseAuth
	private var database = Firebase.database

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_complete_profile, container, false)
		auth = FirebaseAuth.getInstance()

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val user_name_et = view.findViewById<EditText>(R.id.editUserName)
		val first_name_et = view.findViewById<EditText>(R.id.editFirstName)
		val last_name_et = view.findViewById<EditText>(R.id.editLastName)
		val date_et = view.findViewById<EditText>(R.id.date_et)
		val main_sport_tv = view.findViewById<TextView>(R.id.mainSportEdit)
		val confirm_btn = view.findViewById<Button>(R.id.complete_profile_button)

		val user = auth.currentUser

		date_et.setOnClickListener(View.OnClickListener {
			setListener = SetListener(date_et)
			val datePickerDialog = DatePickerDialog(requireContext(), setListener, 2000, 1, 1)
			datePickerDialog.show()
		})

		main_sport_tv.setOnClickListener(View.OnClickListener {
			sportsAlertDialog(requireContext(), main_sport_tv)
		})

		confirm_btn.setOnClickListener(View.OnClickListener {
			if (user != null) {
				var new_user: UserData = UserData(user!!.email.toString())
				new_user.userName = user_name_et.text.toString()
				new_user.firstName = first_name_et.text.toString()
				new_user.lastName = last_name_et.text.toString()
				new_user.birthDate = date_et.text.toString()
				new_user.mainSport = main_sport_tv.text.toString()

				if (user.isEmailVerified) {
					addToDatabase(new_user)
				} else {
					Toast.makeText(this.context, "Please check your emails", Toast.LENGTH_SHORT).show()
				}
			}
		})
	}

	fun addToDatabase(user: UserData) {
		val db_user_ref = database.getReference("user")

		db_user_ref.child("userName").setValue(user.userName)
		db_user_ref.child("firstName").setValue(user.firstName)
		db_user_ref.child("lasttName").setValue(user.lastName)
		db_user_ref.child("birthDate").setValue(user.birthDate)
		db_user_ref.child("mainSport").setValue(user.mainSport)
	}

	fun sportsAlertDialog(context: Context, tv: TextView) {
		val builder = AlertDialog.Builder(context)
		with(builder) {
			setTitle("What is your favorite sport ?")
			setItems(sportsList) { dialog, which ->
				tv.setText(sportsList[which])
			}
			show()
		}
	}
}