package com.example.app_sports.home_activity.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main_home.view.*

class HomeMainFragment : Fragment() {

	private var db_ref = Firebase.database.getReference("activities")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_main_home, container, false)

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val activities: List<ActivitiesData>
		val recyclerView = view.flow_recycler_view
		val adapter = FlowListAdapter()
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())`

		val activitiesListener = object: ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				val activity = snapshot.getValue<ActivitiesData>()
				adapter.updateList(activities)
			}

			override fun onCancelled(error: DatabaseError) {
				Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
			}
		}

		db_ref.addValueEventListener(activitiesListener)
	}
}