package com.nppr.myapplication

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nppr.myapplication.Components.Home
import java.util.*
import kotlin.math.roundToInt

class RealTimeAQI : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time_aqi)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val imgg:ImageView=findViewById(R.id.picc)
        val btnCop: Button=findViewById(R.id.btn_cop)
        val btnll: Button =findViewById(R.id.btn_ll)
//        val btnmp: Button =findViewById(R.id.btn_mpp)
        val search: Button =findViewById(R.id.ss)
        val btnMap: Button =findViewById(R.id.btn_pom)
        val btnCity: Button =findViewById(R.id.btn_ct)
        val btnn: Button =findViewById(R.id.bt)
        val cntry: LinearLayout =findViewById(R.id.ll_country)
        val ct: LinearLayout =findViewById(R.id.ll_city)
        val etCt:EditText=findViewById(R.id.et_ct)
        val etCnt:EditText=findViewById(R.id.et_cnt)
        val etPin:EditText=findViewById(R.id.et_pin)
        val ss:Button=findViewById(R.id.ss)
        val REQUEST_LOCATION_PERMISSION=1
        val ress:TextView=findViewById(R.id.ress)
        val ox:TextView=findViewById(R.id.o3)
        val pmx10:TextView=findViewById(R.id.pm10)
        val pmx25:TextView=findViewById(R.id.pm25)
        val sox2:TextView=findViewById(R.id.SO2)
        val cox:TextView=findViewById(R.id.co)
        val nox:TextView=findViewById(R.id.no2)
        val city:TextView=findViewById(R.id.city)
        val state:TextView=findViewById(R.id.st)
        val ll_o3:LinearLayout=findViewById(R.id.ll_o3)
        val ll_so2:LinearLayout=findViewById(R.id.ll_so2)
        val ll_pm10:LinearLayout=findViewById(R.id.ll_pm10)
        val ll_pm25:LinearLayout=findViewById(R.id.ll_pm25)
        val ll_no2:LinearLayout=findViewById(R.id.ll_no2)
        val ll_co:LinearLayout=findViewById(R.id.ll_co)

        var mLongit=intent.getDoubleExtra("longit",-77777.777770077)
        var mLatit=intent.getDoubleExtra("latit",-77777.777770077)

        if(mLongit!=-77777.777770077 && mLatit!=-77777.777770077){
            Toast.makeText(this,"lat=${mLatit}&lon=${mLongit}",Toast.LENGTH_LONG).show()
            val url="https://api.weatherbit.io/v2.0/current/airquality?lat=${mLatit}&lon=${mLongit}&key=dbe8a69ceb3247c69eccae80651e0c8a"
            fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox,city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
        }
        btnCop.setOnClickListener {
            cntry.visibility= View.VISIBLE
            ct.visibility=View.GONE
            search.visibility=View.VISIBLE
            imgg.visibility=View.GONE
            etCt.text.clear()
        }
        btnCity.setOnClickListener {
            cntry.visibility= View.GONE
            ct.visibility=View.VISIBLE
            search.visibility=View.VISIBLE
            imgg.visibility=View.GONE
            etCnt.text.clear()
            etPin.text.clear()
        }
        btnMap.setOnClickListener {
            cntry.visibility= View.GONE
            ct.visibility=View.GONE
            search.visibility=View.GONE
            val intent= Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
        btnll.setOnClickListener {
            cntry.visibility= View.GONE
            ct.visibility=View.GONE
            search.visibility=View.GONE
            imgg.visibility=View.GONE
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            //Toast.makeText(this,"is ${isLocationEnabled()} ",Toast.LENGTH_LONG).show()
            if(isLocationEnabled()){

                //checkPermit(ox,pmx10,pmx25,sox2,cox,nox,city,state)
                val url="https://api.weatherbit.io/v2.0/current/airquality?postal_code=560100&country=in&key=dbe8a69ceb3247c69eccae80651e0c8a"

                //fetchLoc()
                //val url="https://api.weatherbit.io/v2.0/current/airquality?lat=${mLatit}&lon=${mLongit}&key=3d9a986599ee47c8bc87962fb8f03460\""

                fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox,city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)

            }
            else{
                Toast.makeText(this,"Location is turned off!",Toast.LENGTH_LONG).show()
            }
        }
        ss.setOnClickListener {
            if(!etPin.text.isNullOrEmpty() && etPin.text.length==6){
                val url="https://api.weatherbit.io/v2.0/current/airquality?postal_code=${etPin.text.toString()}&country=in&key=dbe8a69ceb3247c69eccae80651e0c8a"
                fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox,city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
                cntry.visibility=View.GONE
                ss.visibility=View.GONE
            }
            else if(!etCt.text.isNullOrEmpty()){
                val url="https://api.weatherbit.io/v2.0/current/airquality?city=${etCt.text.toString()}&key=dbe8a69ceb3247c69eccae80651e0c8a"//?lat=35.7721&lon=-78.63861&key=3d9a986599ee47c8bc87962fb8f03460"
                fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox, city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
                ct.visibility=View.GONE
                ss.visibility=View.GONE
            }
            else{
                Toast.makeText(this,"Can't be empty :(",Toast.LENGTH_LONG).show()
            }

        }
        btnn.visibility=View.GONE
        btnn.setOnClickListener {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        }

    }
    private fun fetchRes(url:String, ox:TextView,pmx10:TextView,pmx25:TextView,sox2:TextView,cox:TextView,nox:TextView,cit:TextView,sta:TextView,llo3:LinearLayout,ll_pm10: LinearLayout,ll_pm25:LinearLayout,ll_co:LinearLayout,ll_so2:LinearLayout,ll_no2:LinearLayout){
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val city=response.get("city_name")
                val country=response.get("country_code")
                val state=response.get("state_code")
                val data=response.getJSONArray("data").getJSONObject(0)
                val aqi=data.getString("aqi")
                var co=data.getString("co")
                var no2=data.getString("no2")
                var o3=data.getString("o3")
                var pm25=data.getString("pm25")
                var pm10=data.getString("pm10")
                var so2=data.getString("so2")
/*
                //ozone range
                if(o3.toDouble() in 0.0..55.0){
                    llo3.setBackgroundResource(R.color.teal_200)
                }
                else if(o3.toDouble() in 55.1..105.0){
                    llo3.setBackgroundResource(R.color.yellow)
                }
                else if(o3.toDouble() in 105.1..2000.0){
                    llo3.setBackgroundResource(R.color.red)
                }

                //co range
                if(co.toDouble() in 0.0..50.0){
                    ll_co.setBackgroundResource(R.color.teal_200)
                }
                else if(co.toDouble() in 50.1..200.0){
                    ll_co.setBackgroundResource(R.color.yellow)
                }
                else if(co.toDouble() in 200.1..400.0){
                    ll_co.setBackgroundResource(R.color.orange)
                }
                else if(co.toDouble() in 400.1..2000.0){
                    ll_co.setBackgroundResource(R.color.red)
                }
*/
                //
                co=rounder(co)
                no2=rounder(no2)
                o3=rounder(o3)
                pm25=rounder(pm25)
                pm10=rounder(pm10)
                so2=rounder(so2)
                //pm25=rounder(pm25)
                cit.text=city.toString()
                sta.text=country.toString()
                //txx.text="AQI: ${aqi}"
                //txx.text=" CO: ${co}\n NO2: ${no2}\n O3: ${o3}\n PM25: ${pm25}\n PM10: ${pm10}\n SO2: ${so2}"
                ox.text="${o3}"
                pmx10.text="${pm10}"
                pmx25.text="${pm25}"
                sox2.text="${so2}"
                cox.text="${co}"
                nox.text="${no2}"
                val latt=response.get("lat")
                val lonn=response.get("lon")
                //Toast.makeText(this,"City Name: ${city}",Toast.LENGTH_LONG).show()
                //Toast.makeText(this,"Country Name: ${country}",Toast.LENGTH_LONG).show()
/*                Toast.makeText(this,"Latt Name: ${latt}",Toast.LENGTH_LONG).show()
                Toast.makeText(this,"Lonn: ${lonn}",Toast.LENGTH_LONG).show()
                Toast.makeText(this,"Data: ${data.getString("aqi")}",Toast.LENGTH_LONG).show()
*/
            },
            {
                //Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)

    }
    private fun fetchLoc(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                            Toast.makeText(this,"${list[0].latitude} ${list[0].longitude}",Toast.LENGTH_LONG).show()
/*                            tvLatitude.text = "Latitude\n${list[0].latitude}"
                            tvLongitude.text = "Longitude\n${list[0].longitude}"
                            tvCountryName.text = "Country Name\n${list[0].countryName}"
                            tvLocality.text = "Locality\n${list[0].locality}"
                            tvAddress.text = "Address\n${list[0].getAddressLine(0)}"
  */
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                //val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                //fetchLoc()
            }
        }
    }

    private fun rounder(str: String):String{
        var cod=str.toDouble()
        val roundoff = (cod * 1000.0).roundToInt() / 1000.0
        var str1=roundoff.toString()
        return str1
    }

    override fun onBackPressed() {
        super.onBackPressed()
        replaceFragmentMenu(Home())

    }
    /*
    private fun asli(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100
            )
            return

        }
        val loc=fusedLocationClient.lastLocation

        loc.addOnSuccessListener{
            if(it!=null){
                val long=it.longitude
                val lati=it.latitude
                Toast.makeText(this,"${long.toString()} ${lati.toString()}",Toast.LENGTH_LONG).show()
            }
            else{
            }
        }
    }*/

    private fun replaceFragmentMenu(fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null)
        fragmentTransaction.replace(R.id.homee,fragment)
        fragmentTransaction.commit()
    }
}











