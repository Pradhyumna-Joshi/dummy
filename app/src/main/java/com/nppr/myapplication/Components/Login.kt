package com.nppr.myapplication.Components

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nppr.myapplication.R
import com.nppr.myapplication.databinding.FragmentLoginBinding

class Login : Fragment() {



    private var binding_:FragmentLoginBinding?=null
    private val binding get() = binding_!!
    var mProgressDialog: Dialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding_=FragmentLoginBinding.inflate(layoutInflater,container,false)
        FirebaseAuth.getInstance()

        binding.apply{
            tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_login3_to_signUp)
        }
            tvOtp.setOnClickListener {
            findNavController().navigate(R.id.action_login3_to_otpScreen)
        }
            login.setOnClickListener {

            if(etPass.text.toString().length==0 || etEmailId.text.toString().length==0) {
                Toast.makeText(context, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
            else{
                showProgressDialog()
                FirebaseAuth.getInstance()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmailId.text.toString(),etPass.text.toString()).addOnCompleteListener { task->
                    if(task.isSuccessful){
                        //Toast.makeText(this,"Sign In was successful! ${FirebaseAuth.getInstance().currentUser.toString()}",Toast.LENGTH_LONG).show()
                        hideProgressDialog()
//                        findNavController().navigate(R.id.action_login3_to_home)
                    }
                    else{
                        hideProgressDialog()
                        Toast.makeText(context,"Incorrect credentials. Try again!", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
        }




    return binding.root
    }

    private fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }

    private fun showProgressDialog() {
        mProgressDialog = context?.let { Dialog(it) }

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog!!.setContentView(R.layout.progress)

        //Start the dialog and display it on screen.
        mProgressDialog!!.show()
    }


}