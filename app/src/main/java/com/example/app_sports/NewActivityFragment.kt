package com.example.app_sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

class NewActivityFragment : Fragment() {

//	val sports = resources.getStringArray(R.array.Sports)

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_new_activity, container, false)
		return view
	}

//	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//		super.onViewCreated(view, savedInstanceState)
//		val mySpinner = view.findViewById<Spinner>(R.id.new_activity_sport)
//
//		if (mySpinner != null) {
//			val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sports)
//			mySpinner.adapter = adapter
//		}
//	}
}