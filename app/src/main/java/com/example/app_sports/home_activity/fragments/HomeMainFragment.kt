package com.example.app_sports.home_activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_sports.R
import com.example.app_sports.home_activity.FlowListAdapter
import kotlinx.android.synthetic.main.fragment_main_home.view.*

class HomeMainFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_main_home, container, false)

		val recyclerView = view.flow_recycler_view
		val adapter = FlowListAdapter()
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}
}