package com.example.casebycase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.casebycase.home.HomeFragment
import com.example.casebycase.parking.ParkingFragment
import com.example.casebycase.registerBike.RegisterBikeFragment
import com.example.casebycase.repair.RepairFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val registerFragment = RegisterBikeFragment()
        val repairFragment = RepairFragment()
        val homeFragment = HomeFragment()
        val parkingFragment = ParkingFragment()



        replaceFragment(homeFragment)

        val intent = Intent(this@MainActivity,RidingModeMainActivity::class.java )

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.MainBike -> replaceFragment(homeFragment)
                    R.id.repairBike -> replaceFragment(repairFragment)
                R.id.registerBike -> replaceFragment(registerFragment)
                R.id.parkginBike -> replaceFragment(parkingFragment)
                R.id.ridingModeBike -> startActivity(intent)
            }//when

            true
        }//bottomNavigationView


    }//onCreate
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }

    }
    private fun onCreateOptionMenu(menu: Menu){
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu);
    }//메뉴를 눌렀을때 호출되는 메소드드


}