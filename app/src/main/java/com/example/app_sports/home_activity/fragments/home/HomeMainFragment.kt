package com.example.app_sports.home_activity.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main_home.view.*
import org.w3c.dom.Text

class HomeMainFragment : Fragment() {

	private var db_ref = Firebase.database.getReference("user/activities")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_main_home, container, false)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val recyclerView = view.findViewById<RecyclerView>(R.id.flow_recycler_view)
		val emptyRvText = view.findViewById<TextView>(R.id.empty_rv)
		val adapter = FlowListAdapter()
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())
		emptyRvText.visibility = TextView.INVISIBLE

		val activitiesListener = object: ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				val activities = snapshot.getValue<List<ActivitiesData>>()
				if(activities != null) {
					adapter.updateList(activities!!)
				} else {
					emptyRvText.visibility = TextView.VISIBLE
					recyclerView.visibility = RecyclerView.INVISIBLE
					Toast.makeText(requireContext(), "okok", Toast.LENGTH_SHORT).show()
				}
			}

			override fun onCancelled(error: DatabaseError) {
				Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
			}
		}

		db_ref.addValueEventListener(activitiesListener)
	}
}