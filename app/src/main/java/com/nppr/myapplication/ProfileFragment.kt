package com.nppr.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv_name:TextView=view.findViewById(R.id.tv_name)
        val tv_email:TextView=view.findViewById(R.id.tv_identity)
        val pb:ProgressBar=view.findViewById(R.id.pb)
        val tv_save:TextView=view.findViewById(R.id.tv_save)
        val et_name:TextView=view.findViewById(R.id.etName)
        val et_email:TextView=view.findViewById(R.id.etEmail)
        val et_phone:TextView=view.findViewById(R.id.etPhone)
        val et_age:TextView=view.findViewById(R.id.etAge)
        val ll:LinearLayout=view.findViewById(R.id.ll_okay)
        ll.visibility=View.GONE
        if(FirebaseAuth.getInstance().currentUser!!.email.toString()!=""  && FirebaseAuth.getInstance().currentUser!!.email.toString()!="null"
        ){
            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.email.toString()!=""}${FirebaseAuth.getInstance().currentUser!!.email.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email", FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {

                for (doc in it){
                    if(doc.data["name"]!=null){
                        Log.e("yoyo","${doc.data["name"].toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
                        tv_name.text=doc.data["name"].toString()
                        tv_email.text= FirebaseAuth.getInstance().currentUser!!.email.toString()
                        et_name.text=doc.data["name"].toString()
                        et_age.text=doc.data["age"].toString()
                        et_email.text= FirebaseAuth.getInstance().currentUser!!.email.toString()
                        et_phone.text=doc.data["phone"].toString()

                    }
                }

            }.addOnCompleteListener {
                ll.visibility=View.VISIBLE
                pb.visibility=View.GONE
            }
        }
        else{
            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone", FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {

                for (doc in it){
                    //if(doc.data["name"]!=null){
                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
                        tv_name.text=doc.data["name"].toString()
                        tv_email.text= FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)
    //                    et_age.text=doc.data["age"].toString()
                        et_age.text=doc.data["age"].toString()
                        et_name.text=doc.data["name"].toString()
                        et_email.text=doc.data["email"].toString()
                        //et_email.text= FirebaseAuth.getInstance().currentUser!!.email.toString()
                        et_phone.text=doc.data["phone"].toString()
                    //}
                }

            }.addOnCompleteListener {
                ll.visibility=View.VISIBLE
                pb.visibility=View.GONE
            }
        }
        tv_save.setOnClickListener {

            if(!et_age.text.isNullOrEmpty() && !et_name.text.isNullOrEmpty()){

                if(FirebaseAuth.getInstance().currentUser!!.email.toString()!=""  && FirebaseAuth.getInstance().currentUser!!.email.toString()!="null"
                ){
                    Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.email.toString()!=""}${FirebaseAuth.getInstance().currentUser!!.email.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

                    FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email", FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {

                        for (doc in it){

                            if(doc.data["name"]!=null){
                                Log.e("yoyo","${doc.data["name"].toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
                                //tv_name.text=doc.data["name"].toString()
                                //tv_email.text= FirebaseAuth.getInstance().currentUser!!.email.toString()
                                val hash= hashMapOf(
                                    "name" to et_name.text.toString(),
                                    "email" to et_email.text.toString(),
                                    "phone" to et_phone.text.toString(),
                                    "age" to et_age.text.toString()
                                )

                                FirebaseFirestore.getInstance().collection("Users").document(doc.id).set(hash).addOnSuccessListener {
                                    tv_name.text=et_name.text.toString()
                                    //Toast.makeText(getActivity(),"Updated!", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                    }
                }
                else{
                    Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

                    FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone", FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {

                        for (doc in it){
                            //if(doc.data["name"]!=null){
                            //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
                            val hash= hashMapOf(
                                "name" to et_name.text.toString(),
                                "email" to et_email.text.toString(),
                                "phone" to et_phone.text.toString(),
                                "age" to et_age.text.toString()
                            )

                            FirebaseFirestore.getInstance().collection("Users").document(doc.id).set(hash).addOnSuccessListener {
                                tv_name.text=et_name.text.toString()
                                Toast.makeText(getActivity(),"Updated!", Toast.LENGTH_SHORT).show();

                            }
                            //}
                        }

                    }
                }
            }
            else{
                Toast.makeText(getActivity(),"Please Enter all details", Toast.LENGTH_SHORT).show();
            }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}