package com.nppr.myapplication

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val imgg: ImageView=findViewById(R.id.img)

        Handler().postDelayed({
            imgg.startAnimation(AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.zoom_in
            ))
        },2950)
        Handler().postDelayed({
            val intent= Intent(this, Intro::class.java)
            startActivity(intent)
            finish()

        },3000)
        val constr: LinearLayout =findViewById(R.id.splash)

        val animationDrawable: AnimationDrawable = constr.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(0)
        animationDrawable.setExitFadeDuration(3000)
        animationDrawable.start()

    }
}