package com.example.app_sports.home_activity.fragments.user_activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.R
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class UserActivitiesFragment(auth: FirebaseAuth) : Fragment() {

	private val user = auth.currentUser
	private lateinit var db_ref : DatabaseReference

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_user_activities, container, false)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		if (user != null) {
//			val ref_path = "user/" + user.uid + "/activities"
			db_ref = Firebase.database.getReference("activities")
			val recyclerView = view.findViewById<RecyclerView>(R.id.user_recycler_view)
			val emptyRvText = view.findViewById<TextView>(R.id.empty_user_rv)
			val adapter = FlowListAdapter()
			val activitiesList = mutableListOf<ActivitiesData>()
			recyclerView.adapter = adapter
			recyclerView.layoutManager = LinearLayoutManager(requireContext())
			emptyRvText.visibility = TextView.INVISIBLE

			val activitiesListener = object : ValueEventListener {
				override fun onDataChange(snapshot: DataSnapshot) {
					if (snapshot != null) {
						for(activityL in snapshot.children) {
							val activity = activityL.getValue(ActivitiesData::class.java)
							activitiesList.add(activity!!)
						}
						adapter.updateList(activitiesList)
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
}