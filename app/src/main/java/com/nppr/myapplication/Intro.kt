package com.nppr.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.OnSwipe
import com.google.firebase.auth.FirebaseAuth

class Intro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val pic:ImageView=findViewById(R.id.iv_picc)

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(Intent(this, home::class.java))
            finish()
        }

        val lsg: TextView =findViewById(R.id.lgs)
        val skip: TextView =findViewById(R.id.tv_skip)
        val one: TextView =findViewById(R.id.tv_d1)
        val two: TextView =findViewById(R.id.tv_d2)
        val three: TextView =findViewById(R.id.tv_d3)

        val nexx:TextView=findViewById(R.id.tv_next)

        val info:TextView=findViewById(R.id.tv_info)
        val lss=listOf<String>("Navigates to Health Optimal Route","Updates with Real time Pollutant levels","Predicts future Pollutant levels\n and much more")

        var arr=ArrayList<Int>()
        arr.add(R.drawable.navipic)
        arr.add(R.drawable.img)
        arr.add(R.drawable.future)
        arr.add(R.drawable.future)
        var cnt=0
        pic.setImageResource(arr[0])
        info.text=lss[0]
        var runnable: Runnable? = null
        one.setTextColor(Color.parseColor("#0000FF"))

        pic.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->
                    {
                        cnt++
                        cnt=cnt%lss.size
                        if(cnt==lss.size-1){
                            skip.visibility=View.INVISIBLE
                            lsg.visibility=View.VISIBLE

//
                            info.text=lss[cnt]

                            pic.setImageResource(arr[cnt])
                        }
                        else{

                            info.text=lss[cnt]

                            pic.setImageResource(arr[cnt])
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
        })
        nexx.setOnClickListener {

            cnt++
            if(cnt==lss.size-1){
                skip.visibility=View.INVISIBLE
                lsg.visibility=View.VISIBLE

//
                info.text=lss[cnt]

                pic.setImageResource(arr[cnt])
            }
            else if(cnt==lss.size){
                val intent= Intent(this, login::class.java)
                startActivity(intent)
                finish()
            }
            else{

                info.text=lss[cnt]

                pic.setImageResource(arr[cnt])
                //cnt++
            }

            if(cnt==1){

                two.setTextColor(Color.parseColor("#0000FF"))

                one.setTextColor(Color.parseColor("#21E1F3"))
                three.setTextColor(Color.parseColor("#21E1F3"))
            }

            if(cnt==2){

                three.setTextColor(Color.parseColor("#0000FF"))

                two.setTextColor(Color.parseColor("#21E1F3"))

                one.setTextColor(Color.parseColor("#21E1F3"))
            }

            if(cnt==3){

                three.setTextColor(Color.parseColor("#21E1F3"))
                two.setTextColor(Color.parseColor("#21E1F3"))

                one.setTextColor(Color.parseColor("#21E1F3"))

            }
        }
        skip.setOnClickListener {

            val intent= Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}