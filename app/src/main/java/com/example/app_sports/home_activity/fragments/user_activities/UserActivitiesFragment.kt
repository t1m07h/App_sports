package com.example.app_sports.home_activity.fragments.user_activities

import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.R
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserActivitiesFragment(auth: FirebaseAuth) : Fragment() {

	private val user = auth.currentUser
	private lateinit var db_ref : DatabaseReference
	lateinit var mUserActivitiesViewModel: UserActivitiesViewModel
	private var activitiesList = mutableListOf<ActivitiesData>()

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

		if (user != null) {
//			val ref_path = "user/" + user.uid + "/activities"
			db_ref = Firebase.database.getReference("activities")
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
					Toast.makeText(requireContext(), list.size.toString(), Toast.LENGTH_SHORT).show()
					emptyRvText.visibility = TextView.VISIBLE
					recyclerView.visibility = RecyclerView.INVISIBLE
				}
			})

//			val activitiesListener = DbValueEventListener(activitiesList, adapter, recyclerView, emptyRvText, requireContext())
//			db_ref.addValueEventListener(activitiesListener)

		}
	}
}