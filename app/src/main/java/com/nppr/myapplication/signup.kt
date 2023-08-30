package com.nppr.myapplication

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class signup : AppCompatActivity() {
    var mProgressDialog:Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val etName:EditText=findViewById(R.id.etName)
        val etEmailId:EditText=findViewById(R.id.etEmailId)
        val etPass:EditText=findViewById(R.id.etPass)
        val etMobNo:EditText=findViewById(R.id.etMobNo)
        val etConfPass:EditText=findViewById(R.id.etConfPass)
        val signup: TextView =findViewById(R.id.signup)
        signup.setOnClickListener {
            showProgressDialog()
            if(etName.text.toString().length==0 || etEmailId.text.toString().length==0 || etPass.text.toString().length==0 || etMobNo.text.toString().length==0) {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
            else if(etConfPass.text.toString()!=etPass.text.toString()){
                Toast.makeText(this,"${etConfPass.text} and ${etPass.text}", Toast.LENGTH_LONG).show()
                Toast.makeText(this,"Confirm password doesn't match", Toast.LENGTH_LONG).show()
            }
            else if(etMobNo.text.toString().length!=10){
                Toast.makeText(this,"Invalid Mobile Number", Toast.LENGTH_LONG).show()
            }
            else{
                //Toast.makeText(this,"All set to Sign Up", Toast.LENGTH_LONG).show()

                        //Toast.makeText(this,"RCXI Welcomes You!! $registeredName",Toast.LENGTH_LONG).show()
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
                        val db=FirebaseFirestore.getInstance()
                        db.collection("Users").whereEqualTo("email",etEmailId.text.toString()).get().addOnSuccessListener {

                            //for(doc in it){
                                if(it.size()==0){
                                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmailId.text.toString(),etPass.text.toString()).addOnCompleteListener{
                                            task->
                                        if(task.isSuccessful) {
                                            val firebaseUser: FirebaseUser = task.result!!.user!!
                                            val registeredEmail = firebaseUser.email
                                            val registeredName = firebaseUser.displayName
                                            val dbc = db.collection("Users").document()
                                            dbc.set(userMap).addOnSuccessListener {
                                                hideProgressDialog()
                                                Toast.makeText(
                                                    this,
                                                    "You've signed up successfully ",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                finish()
                                            }.addOnFailureListener { e ->
                                                hideProgressDialog()

                                                Log.w("signup", "Error adding document", e)
                                            }
                                        }
                                        else{
                                            Toast.makeText(this,"Couldn't create an account..!", Toast.LENGTH_LONG).show()
                                            hideProgressDialog()
                                            //finish()
                                        }
                                    }
                                }
                                else{
                                    hideProgressDialog()
                                    Toast.makeText(this,"Email is already registered!",Toast.LENGTH_LONG).show()
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
    fun showProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.progress)
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }
}
