package com.example.app_sports.home_activity.fragments.user_activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class UserActivitiesFragment : Fragment() {

	private lateinit var auth: FirebaseAuth
	private var db_ref = Firebase.database.getReference("user")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_user_activities, container, false)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val recyclerView = view.findViewById<RecyclerView>(R.id.user_recycler_view)
		val emptyRvText = view.findViewById<TextView>(R.id.empty_user_rv)
		// TODO: 21/11/20 set the recyclerView adapter
		emptyRvText.visibility = TextView.INVISIBLE

		val activitiesListener = object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				val activities = snapshot.getValue<List<ActivitiesData>>()
				if (activities != null) {
					// TODO: 21/11/20 update recyclerView adapter
				} else {
					emptyRvText.visibility = TextView.VISIBLE
					recyclerView.visibility = RecyclerView.INVISIBLE
				}
			}

			override fun onCancelled(error: DatabaseError) {
				Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
			}
		}

		db_ref.addValueEventListener(activitiesListener)
	}
}