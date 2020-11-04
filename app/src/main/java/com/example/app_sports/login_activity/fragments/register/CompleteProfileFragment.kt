package com.example.app_sports.login_activity.fragments.register

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
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
import com.example.app_sports.home_activity.HomeActivity
import com.example.app_sports.login_activity.pickers.SetListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CompleteProfileFragment : Fragment() {

	private val sportsList = arrayOf<String>("Bike", "Running", "Swimming", "Tennis", "BasketBall")
	lateinit var setListener: SetListener
	private lateinit var auth: FirebaseAuth
	private var db_user_ref = Firebase.database.getReference("user")

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
				val id = db_user_ref.push().key
				val new_user: UserData = UserData(
					id.toString(),
					user!!.email.toString(),
					user_name_et.text.toString(),
					first_name_et.text.toString(),
					last_name_et.text.toString(),
					date_et.text.toString(),
					main_sport_tv.text.toString()
				)

				user.reload()
				if (user != null) {
					if (user.isEmailVerified) {
						addToDatabase(new_user)
						login(user)
					} else {
						Toast.makeText(this.context, "Email not verified", Toast.LENGTH_SHORT)
							.show()
					}
				}
			}
		})
	}

	fun login(user: FirebaseUser?) {
			val intent = Intent(this.context, HomeActivity::class.java)
			startActivity(intent)
			requireActivity().finish()
	}

	fun addToDatabase(user: UserData) {
		db_user_ref.child(user.id).setValue(user)
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