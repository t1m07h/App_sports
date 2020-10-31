package com.example.app_sports.home_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sports.R

class FlowListAdapter(): RecyclerView.Adapter<FlowListAdapter.MyViewHolder>() {
	private var activity_list = emptyList<Activities>()

	class MyViewHolder(item_view: View): RecyclerView.ViewHolder(item_view) {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_view, parent, false))
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val current_activity = activity_list[position]
	}

	override fun getItemCount(): Int {
		return activity_list.size
	}
}