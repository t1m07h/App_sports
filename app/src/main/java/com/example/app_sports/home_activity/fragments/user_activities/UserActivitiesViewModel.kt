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

	fun selectNearActivities(list: MutableList<ActivitiesData>): MutableList<ActivitiesData>? {
		 var i = 0
		while(list[i] != null) {
			if (list[i] != null) {
				val date1 = replaceChar(list[i - 1].date, '/').toInt()
				val date2 = replaceChar(list[i].date, '/').toInt()
				if (i > 0 && date1 < date2) {
					val tmp = list[i]
					list[i] = list[i - 1]
					list[i - 1] = tmp
				}
			}
		}
		return sortActivitiesList(list)
	}

	fun sortActivitiesList(list: MutableList<ActivitiesData>): MutableList<ActivitiesData>? {
		return (list)
	}

	fun replaceChar(str: String, char: Char): String{
		var new: String = ""
		for(c in str) {
			if (c != char) new += c
		}

		return (new)
	}
}