package com.nppr.myapplication.Components

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nppr.myapplication.R


class SignOut : Fragment(R.layout.fragment_sign_out) {

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Signing Out...")
        builder.setMessage("Are you sure you want to sign out?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setCancelable(false)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Handler().postDelayed({
                FirebaseAuth.getInstance().signOut()
//                val intent = Intent(activity, Intro::class.java)
//                startActivity(intent)
                requireActivity().finish()
            },1500)

        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            val destinationFragment = Home()
            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .disallowAddToBackStack()
                .commit()
            dialogInterface.dismiss()


        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
        }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_sign_out, container, false)
        return view
   }
}