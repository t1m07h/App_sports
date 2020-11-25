package com.example.app_sports.home_activity.fragments.user_activities

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.home_activity.DbValueEventListener
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.database.DatabaseReference

class UserActivitiesViewModel : ViewModel() {
	var list = mutableListOf<ActivitiesData>()
	private var activitiesList = LiveData<MutableList<ActivitiesData>>()

	fun loadActivities(ref: DatabaseReference, rv: RecyclerView, tv: TextView, context: Context) {
//		val adapter = adapter
		val rv = rv
		val tv = tv
		val context = context

		val listener = DbValueEventListener(list, rv, tv, context)
		ref.addValueEventListener(listener)
		activitiesList = list
//		adapter.updateList(activitiesList)
	}
}