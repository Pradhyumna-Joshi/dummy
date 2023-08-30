package com.nppr.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityHomeBinding
//    private lateinit var txxEmail: TextView
//    private lateinit var txxUser: TextView


    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.appBarHome.toolbar)
///*
//        binding.appBarHome.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }*/
//
//        val drawerLayout: DrawerLayout = binding.drawerLayout
//        val navView: NavigationView = binding.navView
//        val iv:ImageView=binding.appBarHome.ivSignOut
//        val ll_h:LinearLayout=binding.slideHome
//        val ll_nav:LinearLayout=binding.slideNav
//        val ll_real:LinearLayout=binding.slideReal
//        val ll_fut:LinearLayout=binding.slideFut
//        val ll_pro:LinearLayout=binding.slideProf
//        val ll_out:LinearLayout=binding.slideOut
//        val navController = findNavController(R.id.nav_host_fragment_content_home)
//
//        val ix: androidx.appcompat.widget.Toolbar =binding.appBarHome.toolbar
//
//        ll_h.setOnClickListener {
//            replaceFragment(Home())
//            drawerLayout.closeDrawers()
//            ix.setTitle("Home")
//        }
//        ll_nav.setOnClickListener {
//            replaceFragment(Navigate())
//            drawerLayout.closeDrawers()
//            ix.setTitle("Navigation")
//        }
//        ll_real.setOnClickListener {
//            replaceFragment(RealTime())
//            drawerLayout.closeDrawers()
//            ix.setTitle("Real Time Levels")
//        }
//        ll_fut.setOnClickListener {
//            replaceFragment(Future())
//            drawerLayout.closeDrawers()
//            ix.setTitle("Future Pollutant Levels")
//        }
//        ll_pro.setOnClickListener {
//            replaceFragment(ProfileFragment())
//            drawerLayout.closeDrawers()
//            ix.setTitle("Profile")
//
//        }
//        ll_out.setOnClickListener {
//            replaceFragment(SignOut())
//            drawerLayout.closeDrawers()
//            ix.setTitle("AirO'Drive")
//
//        }
//        txxUser=binding.tvoffUser//navView.findViewById(R.id.tvoff_user)
//        txxEmail=binding.tvoffEmail
//
//        //navView.nav_signOut
//        val loc:ImageView=binding.appBarHome.ivLoca
//        val curr:ImageView=binding.appBarHome.ivReal
//        val fut:ImageView=binding.appBarHome.ivFut
//        val Home:ImageView=binding.appBarHome.ivHome
//        //Log.e("yoyo","${FirebaseAuth.getInstance().currentUser!!.email.toString()}")
//        //Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}",Toast.LENGTH_LONG).show()
//        //Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser!!.email.toString()}",Toast.LENGTH_LONG).show()
//
//        if(FirebaseAuth.getInstance().currentUser!!.email.toString()!="" && FirebaseAuth.getInstance().currentUser!!.email.toString()!="null" ){
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email",FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//                    }
//                }
//            }
//        }
//        else{
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
//
//                    }
//                }
//
//            }
//        }
//
//        iv.setOnClickListener {
//            replaceFragment(SignOut())
//            ix.setTitle("AirO'Drive")
//        }
//        Home.setOnClickListener {
//            replaceFragment(Home())
//            ix.setTitle("Home")
//        }
//        loc.setOnClickListener {
//
//            replaceFragment(Navigate())
//            ix.setTitle("Navigation")
//        }
//        fut.setOnClickListener {
//
//            replaceFragment(Future())
//            ix.setTitle("Future Pollutant Levels")
//        }
//        curr.setOnClickListener {
//            replaceFragment(RealTime())
//            ix.setTitle("Realtime Pollutant Levels")
//        }
//
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home, R.id.nav_navigate, R.id.nav_realLevels, R.id.nav_futureLevels,R.id.nav_proff,R.id.nav_signOut,
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_home)
//        //Toast.makeText(this,"Fair", Toast.LENGTH_SHORT).show();
//
////        Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()}",Toast.LENGTH_LONG).show()
//        /*if(FirebaseAuth.getInstance().currentUser!!.email.toString()!=""){
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email",FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//                    }
//                }
//
//            }
//        }
//        else{
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
//
//                    }
//                }
//
//            }
//        }*/
//        if(FirebaseAuth.getInstance().currentUser!!.email.toString()!="" &&  FirebaseAuth.getInstance().currentUser!!.email.toString()!="null"){
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("email",FirebaseAuth.getInstance().currentUser!!.email.toString()).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.email.toString()
//                    }
//                }
//            }
//        }
//        else{
//            txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)
//            val db=FirebaseFirestore.getInstance().collection("Users").whereEqualTo("phone",FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString().substring(3)).get().addOnSuccessListener {
//
//                for (doc in it){
//                    if(doc.data["name"]!=null){
//                        //Toast.makeText(this,"My name is ${doc.data["name"].toString()}",Toast.LENGTH_LONG).show()
//                        txxUser.text=doc.data["name"].toString()
//                        //txxEmail.text=FirebaseAuth.getInstance().currentUser!!.phoneNumber.toString()
//
//                    }
//                }
//
//            }
//        }
//
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

//    private fun replaceFragment(fragment : Fragment){
//        val fragmentManager=supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.nav_host_fragment_content_home,fragment)
//        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        fragmentTransaction.commit()
//
//    }

//    private fun replaceFragmentMenu(fragment : Fragment){
//        val fragmentManager=supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
//        fragmentTransaction.replace(R.id.nav_host_fragment_content_home,fragment)
//        fragmentTransaction.commit()
//    }
}