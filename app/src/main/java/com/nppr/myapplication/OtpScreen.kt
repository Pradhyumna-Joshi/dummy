package com.nppr.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.nppr.myapplication.Components.VerifyOTP
import com.nppr.myapplication.databinding.FragmentHomeBinding
import com.nppr.myapplication.databinding.FragmentOtpScreenBinding
import java.util.concurrent.TimeUnit


class OtpScreen : Fragment() {


    private var binding_: FragmentOtpScreenBinding?=null;
    private val binding get() = binding_!!
    var auth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding_= FragmentOtpScreenBinding.inflate(layoutInflater,container,false);


        binding.apply {
            btnSendOTP.setOnClickListener {
            findNavController().navigate(R.id.action_otpScreen_to_verifyOTP)
        }


            var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification. In some cases the phone number can be instantly
                    //     verified without needing to send or enter a verification code.
                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
                    //     detect the incoming verification SMS and perform verification without
                    //     user action.
                    Log.d(ContentValues.TAG, "onVerificationCompleted:$p0")
                    //signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // This callback is invoked in an invalid request for verification is made,
                    // for instance if the the phone number format is not valid.
                    Log.d("here", "onVerificationFailed", e)

                    if (e is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Oops", Toast.LENGTH_LONG).show()
                    } else if (e is FirebaseTooManyRequestsException) {


                        Toast.makeText(context, "Oops", Toast.LENGTH_LONG)
                            .show()// The SMS quota for the project has been exceeded
                    }

                    // Show a message and update the UI
                }


            }

            btnSendOTP.setOnClickListener {

                if (isValidd()) {
                    pb.visibility = View.VISIBLE
                    btnSendOTP.text = "Please Wait"
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91 " + etMobNo.text.toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                    /*
                    val options=PhoneAuthProvider.newBuilder(auth)
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+input_mobile_number.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        otp.this,
                        PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            publi
                        }

                    )
                    */
                } else {
                    Toast.makeText(context, "Invalid Number", Toast.LENGTH_LONG).show()
                }
            }


        }


        return binding.root
    }



    fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        binding.apply {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(ContentValues.TAG, "onCodeSent:$verificationId")

            val txx = etMobNo.text.toString()
            val bundle  = Bundle()
            bundle.putString("mobNo",txx)
            bundle.putString("backendotp",verificationId)

            //val intent= Intent(verifyOTP::class.java)

            val fragment = VerifyOTP()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_controller,fragment)?.commit()


            // Save verification ID and resending token so we can use them later
            var storedVerificationId = verificationId
            var resendToken = token
        }

    }

    private fun isValidd(): Boolean {

        binding.apply {
            var k = etMobNo.text.toString()
            val sii = k.length
            if (sii == 10) {
                var userMap = hashMapOf(
                    "name" to "Guest User",
                    "email" to "",
                    "phone" to etMobNo.text.toString(),
                    "age" to ""
                )
                val db= FirebaseFirestore.getInstance()
                db.collection("Users").whereEqualTo("phone",etMobNo.text.toString()).get().addOnSuccessListener {

                    //for(doc in it){
                    if(it.size()==0){

                        Toast.makeText(context,"New user",Toast.LENGTH_LONG).show()
                        val dbc = db.collection("Users").document()
                        dbc.set(userMap).addOnSuccessListener {
                            //hideProgressDialog()

                            // val intent = Intent(this, verifyOTP::class.java)
                            //startActivity(intent)
                            //finish()
                        }.addOnFailureListener { e ->
                            //hideProgressDialog()

                            Log.w("signup", "Error adding document", e)
                        }
                    }
                    else{
                        Toast.makeText(context,"Old user",Toast.LENGTH_LONG).show()
                        //val intent = Intent(this, verifyOTP::class.java)
                        //startActivity(intent)

                    }
                    //}
                }
                return true
            }
            Toast.makeText(context, "Incorrect Mobile number", Toast.LENGTH_LONG).show()
            return false
        }

    }


}


