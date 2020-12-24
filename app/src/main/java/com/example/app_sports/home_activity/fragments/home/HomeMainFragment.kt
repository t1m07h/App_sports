package com.example.app_sports.home_activity.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.R
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.example.app_sports.home_activity.fragments.user_activities.UserActivitiesViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeMainFragment : Fragment() {

	private var db_ref = Firebase.database.getReference("user/activities")
	lateinit var mUserActivitiesViewModel: UserActivitiesViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_main_home, container, false)
		mUserActivitiesViewModel = ViewModelProvider(this).get(UserActivitiesViewModel::class.java)
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

		mUserActivitiesViewModel.activitiesList.observe(viewLifecycleOwner, Observer { list ->
			if (list.size > 0) {
				adapter.updateList(list)
			} else {
				emptyRvText.visibility = TextView.VISIBLE
				recyclerView.visibility = RecyclerView.INVISIBLE
			}
		})
	}
}