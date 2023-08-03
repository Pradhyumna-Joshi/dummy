package com.nppr.myapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class verifyOTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        val tv_mobno:TextView=findViewById(R.id.tv_mobno)
        val tv_verifyOTP:TextView=findViewById(R.id.tv_verifyOTP)
        val pin_view:TextView=findViewById(R.id.pin_view)
        var txx = intent.getStringExtra("mobNo")
        var dbaseOtp=intent.getStringExtra("backendotp")
        tv_mobno.text = "OTP Has been sent to " + txx.toString()

        tv_verifyOTP.setOnClickListener(){
            //val str=(pin_view.text).toString()
            //et_OTP.setText(str)

            Toast.makeText(this, "Signing in", Toast.LENGTH_LONG).show()

            if(!pin_view.text.toString().isNullOrEmpty()){

                var credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(dbaseOtp.toString(),pin_view.text.toString())
                signInWithPhoneAuthCredential(credential)
            }
        }
        /*
            */
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        var auth= FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    val intent= Intent(this,home::class.java)
                    startActivity(intent)
                    finish()
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