package com.example.app_sports.home_activity.fragments.user_activities

import android.app.Application
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.home_activity.DbValueEventListener
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserActivitiesViewModel(application: Application) : AndroidViewModel(application) {
	var activitiesList = MutableLiveData<MutableList<ActivitiesData>>()
	val ref = Firebase.database.getReference("activities")

	init {
		activitiesList = fetchAll(ref, application)
	}

	fun fetchAll(ref: DatabaseReference, application: Application): MutableLiveData<MutableList<ActivitiesData>> {
		val listener = DbValueEventListener(activitiesList, application)
		ref.addValueEventListener(listener)
		return activitiesList
	}

//	fun selectNearActivities(list: MutableList<ActivitiesData>): MutableList<ActivitiesData>? {
//		return sortActivitiesList(list)
//	}
//
//	fun sortActivitiesList(list: MutableList<ActivitiesData>): MutableList<ActivitiesData>? {
//		return (list)
//	}
}