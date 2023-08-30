package com.nppr.myapplication.Components

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.nppr.myapplication.R
import com.nppr.myapplication.databinding.FragmentVerifyOTPBinding


class VerifyOTP : Fragment() {


    private var binding_: FragmentVerifyOTPBinding?=null
    private val binding get() = binding_!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding_= FragmentVerifyOTPBinding.inflate(layoutInflater,container,false)
        val bundle = this.requireArguments()
        var txx = bundle.getString("mobno").toString()
        var dbaseOtp=bundle.getString("backendotp").toString()
//      tv_mobno.text = "OTP Has been sent to " + txx.toString()
        binding.apply {

            tvMobNo.text = "OTP Has been sent to " + txx.toString()

            btnVerifyOTP.setOnClickListener(){
                //val str=(pin_view.text).toString()
                //et_OTP.setText(str)
                Toast.makeText(context, "Signing in", Toast.LENGTH_LONG).show()

                if(!pinView.text.toString().isNullOrEmpty()){

                    var credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(dbaseOtp.toString(),pinView.text.toString())
                    signInWithPhoneAuthCredential(credential)
                }
            }
            /*
                */
        }

        return binding.root
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        var auth= FirebaseAuth.getInstance()
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    findNavController().navigate(R.id.action_verifyOTP_to_home)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }



}