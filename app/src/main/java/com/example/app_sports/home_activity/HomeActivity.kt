package com.example.app_sports.home_activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_sports.NewActivityFragment
import com.example.app_sports.R
import com.example.app_sports.home_activity.fragments.HomeMainFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

	val mainFragmentManager = supportFragmentManager
	val newActivityFragmentManager = supportFragmentManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		setSupportActionBar(findViewById(R.id.toolbar))

		val name = findViewById<TextView>(R.id.app_bar_text)
		val add_activity_btn = findViewById<FloatingActionButton>(R.id.new_activity_btn)
		val newActivityFragmentTransaction = newActivityFragmentManager.beginTransaction()
		val newActivityFragment = NewActivityFragment()
		val mainFragmentTransaction = mainFragmentManager.beginTransaction()
		val listFlowFragment = HomeMainFragment()

		name.text = "Yess"

		mainFragmentTransaction.add(R.id.main_home, listFlowFragment).commit()

		add_activity_btn.setOnClickListener(View.OnClickListener {
			newActivityFragmentTransaction.add(R.id.main_home, newActivityFragment).commit()
		})
	}
}