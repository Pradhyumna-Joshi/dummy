package com.nppr.myapplication.Components

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.nppr.myapplication.R
import com.nppr.myapplication.databinding.FragmentSignUpBinding


class SignUp : Fragment() {


    private var binding_: FragmentSignUpBinding? = null
    private val binding get() = binding_!!
    var mProgressDialog: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding_ = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        binding.apply {
            signup.setOnClickListener {
                showProgressDialog()
                if (etName.text.toString().length == 0 || etEmailId.text.toString().length == 0 || etPass.text.toString().length == 0 || etMobNo.text.toString().length == 0) {
                    Toast.makeText(context, "Invalid credentials!", Toast.LENGTH_LONG).show()
                } else if (etConfPass.text.toString() != etPass.text.toString()) {
                    Toast.makeText(
                        context,
                        "${etConfPass.text} and ${etPass.text}",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    Toast.makeText(context, "Confirm password doesn't match", Toast.LENGTH_LONG)
                        .show()
                } else if (etMobNo.text.toString().length != 10) {
                    Toast.makeText(context, "Invalid Mobile Number", Toast.LENGTH_LONG).show()
                } else {
                    //Toast.makeText(context,"All set to Sign Up", Toast.LENGTH_LONG).show()

                    //Toast.makeText(context,"RCXI Welcomes You!! $registeredName",Toast.LENGTH_LONG).show()
/*                        var str= FirebaseAuth.getInstance().currentUser!!.email.toString().substringBefore("@")

                        FirebaseDatabase.getInstance().getReference("Users").child(str).setValue(etName.text.toString()).addOnCompleteListener {
                            FirebaseAuth.getInstance().signOut()

                            finish()
                        }*/
                    var userMap = hashMapOf(
                        "name" to etName.text.toString(),
                        "email" to etEmailId.text.toString(),
                        "phone" to etMobNo.text.toString(),
                        "age" to ""
                    )
                    val db = FirebaseFirestore.getInstance()
                    db.collection("Users").whereEqualTo("email", etEmailId.text.toString()).get()
                        .addOnSuccessListener {

                            //for(doc in it){
                            if (it.size() == 0) {
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                                    etEmailId.text.toString(), etPass.text.toString()
                                ).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val firebaseUser: FirebaseUser = task.result!!.user!!
                                        val registeredEmail = firebaseUser.email
                                        val registeredName = firebaseUser.displayName
                                        val dbc = db.collection("Users").document()
                                        dbc.set(userMap).addOnSuccessListener {
                                            hideProgressDialog()
                                            Toast.makeText(
                                                context,
                                                "You've signed up successfully ",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }.addOnFailureListener { e ->
                                            hideProgressDialog()

                                            Log.w("signup", "Error adding document", e)
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Couldn't create an account..!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        hideProgressDialog()
                                        //finish()
                                    }
                                }
                            } else {
                                hideProgressDialog()
                                Toast.makeText(
                                    context,
                                    "Email is already registered!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            //}
                        }
                    /*whereEqualTo("mobile_number",
                                FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
                                for (document in it) {
                                    //if (document.data["cartFinal"] != null) {
    
                                        db.collection("Users").document(document.id)
                                            .set(userMap, SetOptions.merge()).addOnSuccessListener {
                                            }
                                    //}
                                }*/

                }
            }
        }



        return binding.root
    }

    private fun showProgressDialog() {
        mProgressDialog = context?.let { Dialog(it) }
        mProgressDialog!!.setContentView(R.layout.progress)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }
}


