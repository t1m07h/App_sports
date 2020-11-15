package com.example.app_sports.home_activity.fragments.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.R
import com.example.app_sports.Model.ActivitiesModel.ActivitiesData
import kotlinx.android.synthetic.main.activity_view.view.*

class FlowListAdapter(): RecyclerView.Adapter<FlowListAdapter.MyViewHolder>() {
	private var activity_list = emptyList<ActivitiesData>()

	class MyViewHolder(item_view: View): RecyclerView.ViewHolder(item_view) {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		return MyViewHolder(
			LayoutInflater.from(parent.context).inflate(R.layout.activity_view, parent, false)
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
		// TODO: 06/11/20 set sport image
	}

	override fun getItemCount(): Int {
		return activity_list.size
	}

	fun updateList(activities: List<ActivitiesData>) {
		this.activity_list = activities
		notifyDataSetChanged()
	}
}