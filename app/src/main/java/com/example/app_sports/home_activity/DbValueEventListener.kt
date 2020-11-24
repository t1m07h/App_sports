package com.example.app_sports.home_activity

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.example.app_sports.home_activity.fragments.FlowListAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text
import kotlin.coroutines.coroutineContext

class DbValueEventListener(list: MutableList<ActivitiesData>, adapter: FlowListAdapter, rv: RecyclerView, tv: TextView, context: Context) : ValueEventListener {

	val list = list
	val adapter = adapter
	val rv = rv
	val tv = tv
	val context = context

	override fun onDataChange(snapshot: DataSnapshot) {
		if (snapshot!!.exists()) {
			for (a in snapshot.children) {
				val activity = a.getValue(ActivitiesData::class.java)
				list.add(activity!!)
			}
			adapter.updateList(list)
		} else {
			tv.visibility = TextView.VISIBLE
			rv.visibility = RecyclerView.INVISIBLE
		}
	}

	override fun onCancelled(error: DatabaseError) {
		Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
	}
}