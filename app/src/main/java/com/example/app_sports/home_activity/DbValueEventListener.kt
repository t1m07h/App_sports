package com.example.app_sports.home_activity

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text
import kotlin.coroutines.coroutineContext

class DbValueEventListener(val mList: MutableLiveData<MutableList<ActivitiesData>>, val context: Context) : ValueEventListener {


	override fun onDataChange(snapshot: DataSnapshot) {
		if (snapshot!!.exists()) {
			var list = mutableListOf<ActivitiesData>()
			for (a in snapshot.children) {
				val activity = a.getValue(ActivitiesData::class.java)
				list.add(activity!!)
			}
			mList.value = list
		}
	}

	override fun onCancelled(error: DatabaseError) {
		Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
	}
}