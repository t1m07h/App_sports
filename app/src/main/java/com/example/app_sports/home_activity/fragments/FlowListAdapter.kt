package com.example.app_sports.home_activity.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.R
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.view_activity.view.*

class FlowListAdapter(): RecyclerView.Adapter<FlowListAdapter.MyViewHolder>() {
	private var activity_list = emptyList<ActivitiesData>()
	private var auth = FirebaseAuth.getInstance()
	private val user = auth.currentUser

	class MyViewHolder(item_view: View): RecyclerView.ViewHolder(item_view) {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		return MyViewHolder(
			LayoutInflater.from(parent.context).inflate(R.layout.view_activity, parent, false)
		)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val current_activity = activity_list[position]

		holder.itemView.activity_title.text = current_activity.title
		holder.itemView.activity_creator.text = current_activity.metadata.creator
		holder.itemView.activity_date.text = current_activity.date
		holder.itemView.activity_hour.text = current_activity.time
		holder.itemView.activity_place.text = current_activity.place
		holder.itemView.activity_level.text = current_activity.level
		holder.itemView.RegisteredNbr.text = current_activity.registered.toString()
		// TODO: 06/11/20 set sport image

		holder.itemView.activity_register_btn.setOnClickListener(View.OnClickListener {
			// TODO: 31/12/20 check if the current user is registered yet
			// TODO: 01/01/21 else add the current user to the activity
		})

	}

	override fun getItemCount(): Int {
		return activity_list.size
	}

	fun updateList(activities: List<ActivitiesData>) {
		this.activity_list = activities
		notifyDataSetChanged()
	}
}