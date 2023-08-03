package com.nppr.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.nppr.myapplication.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
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
        arr.add(R.drawable.img)
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



        /*

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
        })

*/
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
}