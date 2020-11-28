package com.example.app_sports.home_activity.fragments.user_activities

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
import com.google.firebase.auth.FirebaseAuth

class UserActivitiesFragment(auth: FirebaseAuth) : Fragment() {

//	private val user = auth.currentUser
	lateinit var mUserActivitiesViewModel: UserActivitiesViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_user_activities, container, false)
		mUserActivitiesViewModel = ViewModelProvider(this).get(UserActivitiesViewModel::class.java)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val recyclerView = view.findViewById<RecyclerView>(R.id.user_recycler_view)
		val emptyRvText = view.findViewById<TextView>(R.id.empty_user_rv)
		val adapter = FlowListAdapter()

		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())
		emptyRvText.visibility = TextView.INVISIBLE

		mUserActivitiesViewModel.activitiesList.observe(viewLifecycleOwner, Observer { list ->
			if (list.size > 0) {
				// TODO: 27/11/20 null list
				adapter.updateList(list)
			} else {
				emptyRvText.visibility = TextView.VISIBLE
				recyclerView.visibility = RecyclerView.INVISIBLE
			}
		})
	}
}