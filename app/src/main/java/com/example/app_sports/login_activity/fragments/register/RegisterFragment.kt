package com.example.app_sports.login_activity.fragments.register

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.app_sports.*
import com.example.app_sports.Model.UserData
import com.example.app_sports.login_activity.check_data_register
import com.example.app_sports.login_activity.pickers.SetListener
import com.example.app_sports.login_activity.fragments.viewmodel.ConnectionViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(){

    lateinit var date_btn: Button
    lateinit var setListener: SetListener
    private lateinit var ConnViewModel: ConnectionViewModel
    private val sportsList = arrayOf<String>("Bike", "Running", "Swimming", "Tennis", "BasketBall")
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        ConnViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)

        val user_name_et = view.findViewById<EditText>(R.id.editUserName)
        val email_et = view.findViewById<EditText>(R.id.editEmail)
        val password1_et = view.findViewById<EditText>(R.id.passwordEdit)
        val password2_et = view.findViewById<EditText>(R.id.passwordConfirmEdit)
        val main_sport_tv = view.findViewById<TextView>(R.id.mainSportEdit)
        val submitBtn = view.findViewById<Button>(R.id.submitRegisterButton)

//        date_et.setOnClickListener(View.OnClickListener {
//            setListener = SetListener(date_et)
//            val datePickerDialog = DatePickerDialog(requireContext(), setListener, 2000, 1, 1)
//            datePickerDialog.show()
//        })

        main_sport_tv.setOnClickListener(View.OnClickListener {
            sportsAlertDialog(requireContext(), main_sport_tv)
        })
        
        submitBtn.setOnClickListener(View.OnClickListener {
            if (check_data_register(email_et, password1_et, password2_et, user_name_et)) {
                var new = UserData(0, email_et.text.toString(), password1_et.text.toString())
                new.userName = user_name_et.text.toString()
                new.mainSport = main_sport_tv.text.toString()
                ConnViewModel.add_user((new))

                auth.createUserWithEmailAndPassword(new.email, new.password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            // TODO: 01/11/20 go to the informations provider fragment, start main_activity then finish the current one
//                            val intent: Intent = Intent(this, HomeActivity::class.java)
//                            startActivity(intent)
//                            activity.finish()
                            Toast.makeText(this.context, "Signed up successfully !", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this.context, "Sign up failed, try again later", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        })

        return  view
    }

    fun sportsAlertDialog(context: Context, tv: TextView) {
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle("What is your favorite sport ?")
            setItems(sportsList) { dialog, which ->
                tv.setText(sportsList[which])
            }
            show()
        }
    }

    fun register(email: String, password: String) {
    }
}