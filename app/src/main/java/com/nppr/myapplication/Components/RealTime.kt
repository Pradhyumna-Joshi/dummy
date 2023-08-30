package com.nppr.myapplication.Components

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nppr.myapplication.Info
import com.nppr.myapplication.MapsActivity
import com.nppr.myapplication.R
import kotlin.math.roundToInt

class RealTime : Fragment(R.layout.fragment_real) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    var harvestingmonth: Spinner? = null
    var t_harvestingmonth: String? = null
    private var time:String?=""

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val imgg:ImageView=itemView.findViewById(R.id.picc)
        val btnCop: Button =itemView.findViewById(R.id.btn_cop)
        val btnll: Button =itemView.findViewById(R.id.btn_ll)
//        val btnmp: Button =itemView.findViewById(R.id.btn_mpp)
        val search: Button =itemView.findViewById(R.id.ss)
        val btnMap: Button =itemView.findViewById(R.id.btn_pom)
        val btnCity: Button =itemView.findViewById(R.id.btn_ct)
        val btnn: Button =itemView.findViewById(R.id.bt)
        val cntry: LinearLayout =itemView.findViewById(R.id.ll_country)
        val ct: LinearLayout =itemView.findViewById(R.id.ll_city)
        val etCt: EditText =itemView.findViewById(R.id.et_ct)
        val etCnt: EditText =itemView.findViewById(R.id.et_cnt)
        val etPin: EditText =itemView.findViewById(R.id.et_pin)
        val ss: Button =itemView.findViewById(R.id.ss)

        val REQUEST_LOCATION_PERMISSION=1
        val ress:TextView=itemView.findViewById(R.id.ress)
        val ox:TextView=itemView.findViewById(R.id.o3)
        val pmx10:TextView=itemView.findViewById(R.id.pm10)
        val pmx25:TextView=itemView.findViewById(R.id.pm25)
        val sox2:TextView=itemView.findViewById(R.id.SO2)
        val cox:TextView=itemView.findViewById(R.id.co)
        val nox:TextView=itemView.findViewById(R.id.no2)
        val city:TextView=itemView.findViewById(R.id.city)
        val state:TextView=itemView.findViewById(R.id.st)
        val ll_o3:LinearLayout=itemView.findViewById(R.id.ll_o3)
        val ll_so2:LinearLayout=itemView.findViewById(R.id.ll_so2)
        val ll_pm10:LinearLayout=itemView.findViewById(R.id.ll_pm10)
        val ll_pm25:LinearLayout=itemView.findViewById(R.id.ll_pm25)
        val ll_no2:LinearLayout=itemView.findViewById(R.id.ll_no2)
        val ll_co:LinearLayout=itemView.findViewById(R.id.ll_co)


        ll_o3.setOnClickListener {
            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "O3"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
        }
        ll_pm10.setOnClickListener {

            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "PM10"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
        }
        ll_co.setOnClickListener {

            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "CO"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
        }
        ll_no2.setOnClickListener {

            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "NO2"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
        }
        ll_pm25.setOnClickListener {

            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "PM25"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
        }
        ll_so2.setOnClickListener {

            val destinationFragment = Info()
            val bundle = Bundle()
            val str = "SO2"
            bundle.putString("key", str)
            destinationFragment.arguments = bundle

            // Perform the navigation
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment)
                .addToBackStack(null) // If you want to add the fragment to the back stack
                .commit()
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
            val intent= Intent(context, MapsActivity::class.java)
            startActivity(intent)
        }
        btnll.setOnClickListener {
            cntry.visibility= View.GONE
            ct.visibility=View.GONE
            search.visibility=View.GONE

        }
        val slott = resources.getStringArray(R.array.slots)
        val spinner2 = itemView.findViewById<Spinner>(R.id.mw_spinner)
        if (spinner2 != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, slott)
            spinner2.adapter = adapter
            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    time=slott[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        /*
        btnll.setOnClickListener {
            cntry.visibility= View.GONE
            ct.visibility=View.GONE
            search.visibility=View.GONE
            imgg.visibility=View.GONE
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


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
        }*/
        ss.setOnClickListener {
            if(!etPin.text.isNullOrEmpty() && etPin.text.length==6){
                val url="https://api.weatherbit.io/v2.0/current/airquality?postal_code=${etPin.text.toString()}&country=in&key=b079ef38f9d549fb9c6b9a25a59b2a13"
                fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox,city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
                cntry.visibility=View.GONE
                ss.visibility=View.GONE
            }
            else if(!etCt.text.isNullOrEmpty()){
                val url="https://api.weatherbit.io/v2.0/current/airquality?city=${etCt.text.toString()}&key=b079ef38f9d549fb9c6b9a25a59b2a13"//?lat=35.7721&lon=-78.63861&key=3d9a986599ee47c8bc87962fb8f03460"
                fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox, city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
                ct.visibility=View.GONE
                ss.visibility=View.GONE
            }
            else{
                Toast.makeText(context,"Can't be empty :(",Toast.LENGTH_LONG).show()
            }

        }
        btnn.visibility=View.GONE
        btnn.setOnClickListener {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val itemView=inflater.inflate(R.layout.fragment_real, container, false)
        //val args = arguments
        var mLongit=-77777.777770077
        var mLatit =-77777.777770077
        /*if(args!=null){
            mLongit= args!!.getDouble("longit")
            mLatit = args!!.getDouble("latit")
        }*/
        //var mLongit=intent.getDoubleExtra("longit",-77777.777770077)
        //var mLatit=intent.getDoubleExtra("latit",-77777.777770077)

        val ox:TextView=itemView.findViewById(R.id.o3)
        val pmx10:TextView=itemView.findViewById(R.id.pm10)
        val pmx25:TextView=itemView.findViewById(R.id.pm25)
        val sox2:TextView=itemView.findViewById(R.id.SO2)
        val cox:TextView=itemView.findViewById(R.id.co)
        val nox:TextView=itemView.findViewById(R.id.no2)
        val city:TextView=itemView.findViewById(R.id.city)
        val state:TextView=itemView.findViewById(R.id.st)
        val ll_o3:LinearLayout=itemView.findViewById(R.id.ll_o3)
        val ll_so2:LinearLayout=itemView.findViewById(R.id.ll_so2)
        val ll_pm10:LinearLayout=itemView.findViewById(R.id.ll_pm10)
        val ll_pm25:LinearLayout=itemView.findViewById(R.id.ll_pm25)
        val ll_no2:LinearLayout=itemView.findViewById(R.id.ll_no2)
        val ll_co:LinearLayout=itemView.findViewById(R.id.ll_co)

        if(mLongit!=-77777.777770077 && mLatit!=-77777.777770077){
            Toast.makeText(context,"lat=${mLatit}&lon=${mLongit}",Toast.LENGTH_LONG).show()
            val url="https://api.weatherbit.io/v2.0/current/airquality?lat=${mLatit}&lon=${mLongit}&key=b079ef38f9d549fb9c6b9a25a59b2a13"
            fetchRes(url,ox,pmx10,pmx25,sox2,cox,nox,city,state,ll_o3,ll_pm10,ll_pm25,ll_co,ll_so2,ll_no2)
        }
        return itemView//iew
    }


    private fun fetchRes(url:String, ox: TextView, pmx10: TextView, pmx25: TextView, sox2: TextView, cox: TextView, nox: TextView, cit: TextView, sta: TextView, llo3: LinearLayout, ll_pm10: LinearLayout, ll_pm25: LinearLayout, ll_co: LinearLayout, ll_so2: LinearLayout, ll_no2: LinearLayout){
        val queue = Volley.newRequestQueue(context)
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
    /*
    private fun fetchLoc(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
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
                        //Toast.makeText(this,"${list[0].latitude} ${list[0].longitude}", Toast.LENGTH_LONG).show()
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
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
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
*/
    private fun rounder(str: String):String{
        var cod=str.toDouble()
        val roundoff = (cod * 1000.0).roundToInt() / 1000.0
        var str1=roundoff.toString()
        return str1
    }
}

