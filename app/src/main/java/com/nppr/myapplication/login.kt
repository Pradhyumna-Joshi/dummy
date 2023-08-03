package com.nppr.myapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    var mProgressDialog: Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmailId: EditText =findViewById(R.id.etEmailId)
        val etPass: EditText =findViewById(R.id.etPass)
        val tvsign:TextView=findViewById(R.id.tv_noAccount)
        val otp:TextView=findViewById(R.id.tv_viaPhone)
        val login:TextView=findViewById(R.id.login)
        FirebaseAuth.getInstance()

        tvsign.setOnClickListener {
            val intent= Intent(this,signup::class.java)
            startActivity(intent)
        }
        otp.setOnClickListener {
            val intent= Intent(this,otpp::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {

            if(etPass.text.toString().length==0 || etEmailId.text.toString().length==0) {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
            else{
                showProgressDialog()
                FirebaseAuth.getInstance()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmailId.text.toString(),etPass.text.toString()).addOnCompleteListener {

                        task->
                    if(task.isSuccessful){
                        //Toast.makeText(this,"Sign In was successful! ${FirebaseAuth.getInstance().currentUser.toString()}",Toast.LENGTH_LONG).show()
                        hideProgressDialog()
                        val intent= Intent(this,home::class.java)
                        //intent.putExtra("naam",FirebaseAuth.getInstance().currentUser.toString())
                        //Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser}",Toast.LENGTH_LONG).show()
                        startActivity(intent)
                        finish()
                    }
                    else{
                        hideProgressDialog()
                        Toast.makeText(this,"Incorrect credentials. Try again!", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
    }
    fun showProgressDialog() {
        mProgressDialog = Dialog(this)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog!!.setContentView(R.layout.progress)

        //Start the dialog and display it on screen.
        mProgressDialog!!.show()
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }

}