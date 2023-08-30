package com.nppr.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nppr.myapplication.databinding.FragmentIntroBinding


class Intro : Fragment() {

    private var binding_:FragmentIntroBinding?=null
    private val binding get() = binding_!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding.apply {
//
//
//            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//                findNavController().navigate(R.id.action_intro_to_login3)
//            }
//
//
//            val lss=listOf<String>("Navigates to Health Optimal Route","Updates with Real time Pollutant levels","Predicts future Pollutant levels\n and much more")
//
//            var arr=ArrayList<Int>()
//            arr.add(R.drawable.navipic)
//            arr.add(R.drawable.real)
//            arr.add(R.drawable.future)
//            arr.add(R.drawable.future)
//            var cnt=0
//            pic.setImageResource(arr[0])
//            info.text=lss[0]
//            var runnable: Runnable? = null
//            one.setTextColor(Color.parseColor("#0000FF"))
//
//            pic.setOnTouchListener(object : View.OnTouchListener {
//                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                    when (event?.action) {
//                        MotionEvent.ACTION_DOWN ->
//                        {
//                            cnt++
//                            cnt=cnt%lss.size
//                            if(cnt==lss.size-1){
//                                skip.visibility=View.INVISIBLE
//                                lsg.visibility=View.VISIBLE
//
////
//                                info.text=lss[cnt]
//
//                                pic.setImageResource(arr[cnt])
//                            }
//                            else{
//
//                                info.text=lss[cnt]
//
//                                pic.setImageResource(arr[cnt])
//                                //cnt++
//                            }
//
//                            if(cnt==0){
//
//                                two.setTextColor(Color.parseColor("#21E1F3"))
//
//                                one.setTextColor(Color.parseColor("#0000FF"))
//                                three.setTextColor(Color.parseColor("#21E1F3"))
//                            }
//
//                            if(cnt==1){
//
//                                three.setTextColor(Color.parseColor("#21E1F3"))
//                                two.setTextColor(Color.parseColor("#0000FF"))
//
//                                one.setTextColor(Color.parseColor("#21E1F3"))
//                            }
//
//                            if(cnt==2){
//
//                                three.setTextColor(Color.parseColor("#0000FF"))
//
//                                two.setTextColor(Color.parseColor("#21E1F3"))
//
//                                one.setTextColor(Color.parseColor("#21E1F3"))
//
//                            }
//                        }
//                    }
//
//                    return v?.onTouchEvent(event) ?: true
//                }
//            })
//            nexx.setOnClickListener {
//
//                cnt++
//                if(cnt==lss.size-1){
//                    skip.visibility=View.INVISIBLE
//                    lsg.visibility=View.VISIBLE
//
////
//                    info.text=lss[cnt]
//
//                    pic.setImageResource(arr[cnt])
//                }
//                else if(cnt==lss.size){
//                    val intent= Intent(this, login::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                else{
//
//                    info.text=lss[cnt]
//
//                    pic.setImageResource(arr[cnt])
//                    //cnt++
//                }
//
//                if(cnt==1){
//
//                    two.setTextColor(Color.parseColor("#0000FF"))
//
//                    one.setTextColor(Color.parseColor("#21E1F3"))
//                    three.setTextColor(Color.parseColor("#21E1F3"))
//                }
//
//                if(cnt==2){
//
//                    three.setTextColor(Color.parseColor("#0000FF"))
//
//                    two.setTextColor(Color.parseColor("#21E1F3"))
//
//                    one.setTextColor(Color.parseColor("#21E1F3"))
//                }
//
//                if(cnt==3){
//
//                    three.setTextColor(Color.parseColor("#21E1F3"))
//                    two.setTextColor(Color.parseColor("#21E1F3"))
//
//                    one.setTextColor(Color.parseColor("#21E1F3"))
//
//                }
//            }
//            skip.setOnClickListener {
//
//                val intent= Intent(this, login::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }

        return binding.root

    }


}