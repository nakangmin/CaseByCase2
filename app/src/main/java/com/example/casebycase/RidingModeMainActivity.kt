package com.example.casebycase

import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.casebycase.map.MapFragment
import com.example.casebycase.record.RecordFragment
import com.example.casebycase.ridingmain.RidingMainFragment
import com.example.casebycase.ridingrecord.RidingRecordFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class RidingModeMainActivity : AppCompatActivity(){

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riding_mode_main)



        val ridingBottomNavigationView = findViewById<BottomNavigationView>(R.id.ridingBottomNavigationView)
        val recordFragment = RecordFragment()
        val ridingRecordFragment = RidingRecordFragment()
        val ridingMainFragment = RidingMainFragment()
        val mapFragment = MapFragment()

        val intent = Intent(this@RidingModeMainActivity, MainActivity::class.java)

        ridingReplaceFragment(ridingMainFragment)

        ridingBottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.recordRiding ->ridingReplaceFragment(recordFragment)
                    R.id.ridingRecordRiding ->ridingReplaceFragment(ridingRecordFragment)
                R.id.ridingMain ->ridingReplaceFragment(ridingMainFragment)
                R.id.mapRiding ->ridingReplaceFragment(mapFragment)
                R.id.mainReturn -> startActivity(intent)
            }//when
            true
        }//ridingBottomNavigationView






    }//oncreate

    private fun ridingReplaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ridingFragmentContainer, fragment)
            commit()
        }
    }

}