package com.example.app_sports.home_activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_sports.NewActivityFragment
import com.example.app_sports.R

class HomeActivity : AppCompatActivity() {

	val fragmentManager = supportFragmentManager
	val newActivityFragment: NewActivityFragment = NewActivityFragment()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		setSupportActionBar(findViewById(R.id.toolbar))

		val name = findViewById<TextView>(R.id.app_bar_text)
		val add_activity_btn = findViewById<Button>(R.id.new_activity_btn)

		name.text = "Yess"

		val fragmentTransaction = fragmentManager.beginTransaction()
		add_activity_btn.setOnClickListener(View.OnClickListener {
			fragmentTransaction.add(R.id.main_home, newActivityFragment).commit()
		})
	}
}