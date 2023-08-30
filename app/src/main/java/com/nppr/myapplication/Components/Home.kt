package com.nppr.myapplication.Components

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.nppr.myapplication.R
import com.nppr.myapplication.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class Home : Fragment(R.layout.fragment_home) {


    private var binding_:FragmentHomeBinding?=null;
    private val binding get() = binding_!!
    private lateinit var navController : NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding_= FragmentHomeBinding.inflate(layoutInflater,container,false)




        val navHostFragment = fragmentManager?.findFragmentById(R.id.nav_host_controller) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

//        0   50  good
//        51  100 yellow
//        101 150 orange
//        151 200 red


        binding.circularProgressBar.apply {
            // Set Progress
            // or with animation
//            setProgressWithAnimation(65f, 1000) // =1s


            // Set Progress Max
            progressMax = 200f
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT

            onProgressChangeListener={ progress ->
                if(progress in 0.0..50.0){
                    binding.circularProgressBar.apply {
                        progressBarColor = Color.parseColor("#3BE712")
                        backgroundProgressBarColor = Color.parseColor("#8AF371")
                    }
                }else if(progress in 51.0..100.0){
                    binding.circularProgressBar.apply {
                        progressBarColor = Color.parseColor("#FFDC26")
                        backgroundProgressBarColor = Color.parseColor("#FFF6999")
                    }
                }else if(progress in 101.0..150.0){
                    binding.circularProgressBar.apply {
                        progressBarColor = Color.parseColor("#FC6F10")
                        backgroundProgressBarColor = Color.parseColor(" #FFAA71")
                    }
                }else{
                    binding.circularProgressBar.apply {
                        progressBarColor = Color.parseColor("#971919")
                        backgroundProgressBarColor = Color.parseColor("#C58080")
                    }
                }
            }

        }

//        val iconn:LinearLayout=itemView.findViewById(R.id.ll_icon)
//        val contentt:LinearLayout=itemView.findViewById(R.id.contentt)
        //iconn.
//        val duration: TextView =itemView.findViewById(R.id.tv_dura)
//        val name:TextView=itemView.findViewById(R.id.tv_name)
//        val aqi:TextView=itemView.findViewById(R.id.aqi)
//        val pb: ProgressBar =itemView.findViewById(R.id.progress_bar)
//        val tvp:TextView=itemView.findViewById(R.id.text_view_progress)

        val sdf = SimpleDateFormat("HH")
        val str: String = sdf.format(Date())
        var x=str.toString().toInt()
        val url="https://api.weatherbit.io/v2.0/current/airquality?city=Bengaluru&key=b079ef38f9d549fb9c6b9a25a59b2a13"//?lat=35.7721&lon=-78.63861&key=3d9a986599ee47c8bc87962fb8f03460"

        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val city = response.get("city_name")
                val country = response.get("country_code")
                val state = response.get("state_code")
                val data = response.getJSONArray("data").getJSONObject(0)
                val aqin = data.getString("aqi")


                binding.apply {
                    tVaqi.text = aqin.toString()
                    circularProgressBar.progress = aqin.toString().toFloat()
                    tvCity.text = city.toString()+", "
                    tvState.text = state.toString()
                }


//                pb.progress=aqin.toString().toInt()
//                tvp.text=aqin.toString()

            },
            {
                //Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
        binding.apply {
            if(x>0 && x<4){
                tvDuration.text="Good Evening!"
            }
            else if(x>=4 && x<12){

                tvDuration.text="Good Morning!"
            }
            else if(x>=12 && x<16){

                tvDuration.text="Good Afternoon!"
            }
            else{

                tvDuration.text="Good Evening!"
            }
        }

//        contentt.visibility=View.GONE


//        if(FirebaseAuth.getInstance().currentUser!!.email.toString()!=""  && FirebaseAuth.getInstance().currentUser!!.email.toString()!="null"
//        ){
//            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.email.toString()!=""}${FirebaseAuth.getInstance().currentUser!!.email.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
//
//            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email", FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        Log.e("yoyo","${doc.data["name"].toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
//                        binding.tvName.text=doc.data["name"].toString()
//
//                    }
//                }
//
//            }.addOnCompleteListener {
//                //ll.visibility=View.VISIBLE
//                //pb.visibility=View.GONE
//            }
//        }
//        else{
//            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
//
//            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone", FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
//
//                for (doc in it){
//                    //if(doc.data["name"]!=null){
//                    //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                    binding.tvName.text=doc.data["name"].toString()
//
//                }
//
//            }.addOnCompleteListener {
//                //ll.visibility=View.VISIBLE
//                //pb.visibility=View.GONE
//            }
//        }
        /*
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        //starting the animation
        iconn.startAnimation(animation)
        Handler().postDelayed({
            val animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            //starting the animation
            iconn.startAnimation(animation)
            Handler().postDelayed({
                iconn.visibility=View.GONE
            },1200)

        },2000)
        */

//        contentt.visibility=View.VISIBLE

        return binding.root



    }
}