package com.nppr.myapplication.Components

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.nppr.myapplication.databinding.FragmentLoginBinding
import com.nppr.myapplication.databinding.FragmentSplashBinding


class Splash : Fragment() {

    private var binding_: FragmentSplashBinding?=null
    private val binding get() = binding_!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding_=FragmentSplashBinding.inflate(layoutInflater,container,false)

//        binding.apply {
//            Handler().postDelayed({
//                img.startAnimation(
//                    AnimationUtils.loadAnimation(
//                    requireContext(),
//                    R.anim.zoom_in
//                ))
//            },2950)
//            Handler().postDelayed({
//               findNavController().navigate(R.id.action_splash2_to_login3)
//            },3000)
//
//
//            val animationDrawable: AnimationDrawable = splash.background as AnimationDrawable
//            animationDrawable.setEnterFadeDuration(0)
//            animationDrawable.setExitFadeDuration(3000)
//            animationDrawable.start()
//
//        }

//        findNavController().navigate(R.id.action_splash2_to_login3)
        return binding.root

    }


}