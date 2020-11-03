package com.example.app_sports.login_activity.fragments.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.app_sports.R
import com.example.app_sports.login_activity.repository.ConnectionRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionViewModel(application: Application): AndroidViewModel(application) {
//	var user = MutableLiveData<UserData>()
	private val repo: ConnectionRepository

	init {
		repo = ConnectionRepository()
	}

//	fun updateUI(currentUser: FirebaseUser?) {
//		viewModelScope.launch(Dispatchers.IO) {
//			if (currentUser != null) {
//				while (true) {
//					if (currentUser.isEmailVerified) {
//						mView.findNavController().navigate(R.id.action_registerFragment_to_completeProfileFragment)
//					} else {
//						Toast.makeText(mApplication.applicationContext, "Please check your emails first", Toast.LENGTH_SHORT).show()
//					}
//				}
//			} else {
//				Toast.makeText(mApplication.applicationContext, "Registration failed, try again later", Toast.LENGTH_SHORT).show()
//			}
//		}
//	}
}