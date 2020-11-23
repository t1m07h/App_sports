package com.example.app_sports.home_activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app_sports.home_activity.fragments.new_activity.NewActivityFragment
import com.example.app_sports.R
import com.example.app_sports.home_activity.fragments.home.HomeMainFragment
import com.example.app_sports.home_activity.fragments.user_activities.UserActivitiesFragment
import com.example.app_sports.home_activity.fragments.user_profile.UserProfileFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		setSupportActionBar(findViewById(R.id.toolbar))
		auth = FirebaseAuth.getInstance()

		val name = findViewById<TextView>(R.id.app_bar_text)
		val newActivityFragment = NewActivityFragment()
		val listFlowFragment = HomeMainFragment()
		val userActivitiesFragment = UserActivitiesFragment(auth)
		val userProfileFragment = UserProfileFragment()

		replaceFragment(listFlowFragment)
		name.text = "Yess"

		bottom_navigation.setOnNavigationItemSelectedListener { item ->
			when(item.itemId) {
				R.id.ic_activities_flow -> replaceFragment(listFlowFragment)
				R.id.ic_user_activities -> replaceFragment(userActivitiesFragment)
				R.id.ic_user_profile -> replaceFragment(userProfileFragment)
				R.id.ic_add_activity -> replaceFragment(newActivityFragment)
			}
			false
		}
	}

	private fun replaceFragment(fragment: Fragment): Boolean {
		val fragmentManager = supportFragmentManager
		val fragmentTransaction = fragmentManager.beginTransaction()
		if (fragment != null) {
			fragmentTransaction.replace(R.id.main_home, fragment).commit()
			return true
		}
		return false
	}
}