package com.nppr.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode
import com.nppr.myapplication.databinding.ActivityMapsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback ,  GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var mLatit: Double =0.0000000054545454
    private var mLongit: Double =0.000000005009787
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val btnErase:Button=findViewById(R.id.erase)
        val btnOrigin:Button=findViewById(R.id.origin)
        val gett:Button=findViewById(R.id.getAQI)

        gett.setOnClickListener {
 /*           val intent= Intent(this,RealTimeAQI::class.java)
            intent.putExtra("latit",mLatit)
            intent.putExtra("longit",mLongit)
            startActivity(intent)
            finish()
*/            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            val fragment = RealFragment()
            fragmentTransaction.replace(R.id.present, fragment) // Replace with the ID of your container
            fragmentTransaction.commit()

        }
        btnErase.setOnClickListener {
            //Toast.makeText(this,"Coming soon v1.0",Toast.LENGTH_LONG).show()
            mMap.clear()
        }

        btnOrigin.setOnClickListener {
            //Toast.makeText(this,"Coming soon v2.0",Toast.LENGTH_LONG).show()
            val bang = LatLng(12.9716, 77.5946)
            mLatit=12.9716
            mLongit=77.5946
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(bang).title("Marker in Bangalore"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bang))
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isTrafficEnabled=true
        val bang = LatLng(12.9716, 77.5946)
        //mMap.addMarker(MarkerOptions().position(bang).title("Marker in Bangalore"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bang))
        //val trafficSpeeds = googleMap.getTrafficSpeeds()
        /*val trafficModel=TrafficModel.getInstance()
        val trafficData = trafficModel.getHistoricalTrafficAtLocation(12.9716, 77.5946, System.currentTimeMillis())
        val speed = trafficData.speed
        val congestion = trafficData.congestion
        val message = "Traffic speed: $speed\nTraffic congestion: $congestion"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()*/
        googleMap.setOnMapClickListener { point ->
            mMap.clear()
            val neww = LatLng(point.latitude, point.longitude)
            mLatit=point.latitude
            mLongit=point.longitude
            mMap.addMarker(MarkerOptions().position(neww).title("In position"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(neww))
            Toast.makeText(this,"Latitude: ${point.latitude} \n Longitude ${point.longitude}",Toast.LENGTH_LONG).show()

        }
        val startLocation = LatLng(37.7749, -122.4194) // San Francisco, CA
        val endLocation = LatLng(34.0522, -118.2437) // Los Angeles, CA

        // Add markers for start and end locations
        googleMap.addMarker(MarkerOptions().position(startLocation).title("Start Location"))
        googleMap.addMarker(MarkerOptions().position(endLocation).title("End Location"))

        // Fetch the route between start and end locations
        fetchRoute(startLocation, endLocation)
    }
    override fun onMapClick(latLng: LatLng) {
        val latitude = latLng.latitude
        val longitude = latLng.longitude
        mLatit=latitude
        mLongit=longitude
        //Toast.makeText(this,"lol",Toast.LENGTH_LONG).show()
        val sydney = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Your location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun fetchRoute(startLocation: LatLng, endLocation: LatLng) {
        val geoApiContext = GeoApiContext.Builder()
            .apiKey("AIzaSyARYgxVz9ZlYrde179hdXO9lWrW70TAMoo")
            .build()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val result = DirectionsApi.newRequest(geoApiContext)
                    .origin(com.google.maps.model.LatLng(startLocation.latitude, startLocation.longitude))
                    .destination(com.google.maps.model.LatLng(endLocation.latitude, endLocation.longitude))
                    .mode(TravelMode.DRIVING)
                    .await()

                handleRouteResult(result)

                Log.i("sry","sdgw")
            } catch (e: Exception) {
                // Handle any errors here
                Log.i("sry","${e.message}")
            }
        }
    }


    private fun handleRouteResult(result: DirectionsResult) {
        val route = result.routes[0]
        val points = route.overviewPolyline.decodePath()

        val polylineOptions = PolylineOptions().apply {
            addAll(points.map { LatLng(it.lat, it.lng) })
            width(12f)
            color(R.color.red) // Replace with your desired color
        }

        // Add the polyline to the map
        mMap.addPolyline(polylineOptions)

        // Move the camera to show the entire route
        val bounds = route.bounds
        val boundsBuilder = LatLngBounds.builder()
        boundsBuilder.include(LatLng(bounds.northeast.lat, bounds.northeast.lng))
        boundsBuilder.include(LatLng(bounds.southwest.lat, bounds.southwest.lng))
        val bound = boundsBuilder.build()
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bound, 10))
    }

}