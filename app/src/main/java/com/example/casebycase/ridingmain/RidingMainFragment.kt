package com.example.casebycase.ridingmain

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.SYSTEM_HEALTH_SERVICE
import android.content.pm.PackageManager
import android.icu.text.RelativeDateTimeFormatter
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.casebycase.R
import com.example.casebycase.databinding.FragmentRidingMainBinding
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat





class RidingMainFragment: Fragment(), LocationListener {

    private var _binding: FragmentRidingMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private var mLastLocation: Location? = null
    private var speed: Double = 0.0
    private var sumTime: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRidingMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return root
        }

        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)


        if (lastLocation != null) {
            var sdf: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
            var formatData: String = sdf.format(lastLocation.time)
//            binding.tvTotalTime.setText(formatData)
        }


        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)







        return root
    }

    override fun onLocationChanged(location: Location) {
        var sdf: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
        var delaTime: Double = 0.0


        var getSpeed: String = String.format("%.3f", location.speed)
        binding.tvAverageSpeed.setText(getSpeed)
        var formatDate: String = sdf.format(location.time)
//        binding.tvTotalTime.setText(formatDate)

        if (mLastLocation != null) {
            delaTime = location.time - mLastLocation!!.time / 1000.0
            sumTime = sumTime.plus(delaTime).toInt()

            if (sumTime <= 60){
                binding.tvTotalTime.setText(sumTime.toString())
            }else if (sumTime<=3600) binding.tvTotalTime.setText(sumTime.div(60).toString() + sumTime.rem(
                60).toString())
            else binding.tvTotalTime.setText(sumTime.div((60*60)).toString() + sumTime.div(60).toString() + sumTime.rem(
                60).toString())


            speed = mLastLocation!!.distanceTo(location).toDouble()
            var calSpeed: String = String.format("%.3f", speed)
            binding.tvAverageSpeed.setText(calSpeed)
            binding.speedView.speedTo(speed.toFloat())

        }
        mLastLocation = location
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
    }


    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(this)
    }

    override fun onStart() {
        super.onStart()
//        if (ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
//                    Manifest.permission.ACCESS_FINE_LOCATION) &&
//                ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
//                    Manifest.permission.ACCESS_COARSE_LOCATION)
//            ) {
//                ActivityCompat.requestPermissions(requireActivity(), String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100)
//
//            }
//
//        }

    }
}










