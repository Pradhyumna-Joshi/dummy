package com.nppr.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val iconn:LinearLayout=itemView.findViewById(R.id.ll_icon)
        val contentt:LinearLayout=itemView.findViewById(R.id.contentt)
        //iconn.
        val duration:TextView=itemView.findViewById(R.id.tv_dura)
        val name:TextView=itemView.findViewById(R.id.tv_name)
        val aqi:TextView=itemView.findViewById(R.id.aqi)
        val pb:ProgressBar=itemView.findViewById(R.id.progress_bar)
        val tvp:TextView=itemView.findViewById(R.id.text_view_progress)

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
                aqi.text = aqin.toString()

                pb.progress=aqin.toString().toInt()
                tvp.text=aqin.toString()

            },
            {
                //Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)

        if(x>0 && x<4){
            duration.text="Good Evening!"
        }
        else if(x>=4 && x<12){

            duration.text="Good Morning!"
        }
        else if(x>=12 && x<16){

            duration.text="Good Afternoon!"
        }
        else{

            duration.text="Good Evening!"
        }
        contentt.visibility=View.GONE


        if(FirebaseAuth.getInstance().currentUser!!.email.toString()!=""  && FirebaseAuth.getInstance().currentUser!!.email.toString()!="null"
        ){
            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.email.toString()!=""}${FirebaseAuth.getInstance().currentUser!!.email.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email", FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {

                for (doc in it){
                    if(doc.data["name"]!=null){
                        Log.e("yoyo","${doc.data["name"].toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()
                        name.text=doc.data["name"].toString()

                    }
                }

            }.addOnCompleteListener {
                //ll.visibility=View.VISIBLE
                //pb.visibility=View.GONE
            }
        }
        else{
            Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}")//Toast.makeText(this,"My name is ${}",Toast.LENGTH_LONG).show()

            FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone", FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {

                for (doc in it){
                    //if(doc.data["name"]!=null){
                    //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
                    name.text=doc.data["name"].toString()

                }

            }.addOnCompleteListener {
                //ll.visibility=View.VISIBLE
                //pb.visibility=View.GONE
            }
        }
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

        contentt.visibility=View.VISIBLE
    /*
        var cnt=0

        val one: TextView =itemView.findViewById(R.id.tv_d1)
        val two: TextView =itemView.findViewById(R.id.tv_d2)
        val three: TextView =itemView.findViewById(R.id.tv_d3)
        val pic:ImageView= itemView.findViewById(R.id.iv_iv)
        val info: TextView =itemView.findViewById(R.id.tv_info)

/*
        val constr: LinearLayout =itemView.findViewById(R.id.homee)

        val animationDrawable: AnimationDrawable = constr.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(0)
        animationDrawable.setExitFadeDuration(4500)
        animationDrawable.start()
*/
        val lss=listOf<String>("Navigates to Health Optimal Route","Updates with Real time Pollutant levels","Predicts future Pollutant levels\n and much more")



        two.setTextColor(Color.parseColor("#21E1F3"))

        one.setTextColor(Color.parseColor("#0000FF"))
        three.setTextColor(Color.parseColor("#21E1F3"))
        var runnable: Runnable? = null
        var i=0
        var arr=ArrayList<Int>()
        arr.add(R.drawable.navipic)
        arr.add(R.drawable.real)
        arr.add(R.drawable.future)
        arr.add(R.drawable.future)
        pic.setImageResource(arr[i])
        Handler().postDelayed(Runnable {
            Handler().postDelayed(runnable!!,3000)
            pic.setImageResource(arr[i])
            info.text=lss[i]

            var cnt=i
            if(cnt==0){

                two.setTextColor(Color.parseColor("#21E1F3"))

                one.setTextColor(Color.parseColor("#0000FF"))
                three.setTextColor(Color.parseColor("#21E1F3"))
            }

            if(cnt==1){

                three.setTextColor(Color.parseColor("#21E1F3"))
                two.setTextColor(Color.parseColor("#0000FF"))

                one.setTextColor(Color.parseColor("#21E1F3"))
            }

            if(cnt==2){

                three.setTextColor(Color.parseColor("#0000FF"))

                two.setTextColor(Color.parseColor("#21E1F3"))

                one.setTextColor(Color.parseColor("#21E1F3"))

            }
            i++
            i=i%3
        }.also { runnable =it }, 20)



        pic.setImageResource(arr[0])

        pic.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->
                    {
                        cnt++
                        cnt=cnt%lss.size
                        if(cnt==lss.size-1){
                            pic.setImageResource(arr[cnt])
                            info.text=lss[cnt]
                        }
                        else{
                            pic.setImageResource(arr[cnt])
                            info.text=lss[cnt]
                            //cnt++
                        }

                        if(cnt==0){

                            two.setTextColor(Color.parseColor("#21E1F3"))

                            one.setTextColor(Color.parseColor("#0000FF"))
                            three.setTextColor(Color.parseColor("#21E1F3"))
                        }

                        if(cnt==1){

                            three.setTextColor(Color.parseColor("#21E1F3"))
                            two.setTextColor(Color.parseColor("#0000FF"))

                            one.setTextColor(Color.parseColor("#21E1F3"))
                        }

                        if(cnt==2){

                            three.setTextColor(Color.parseColor("#0000FF"))

                            two.setTextColor(Color.parseColor("#21E1F3"))

                            one.setTextColor(Color.parseColor("#21E1F3"))

                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })*/


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
}