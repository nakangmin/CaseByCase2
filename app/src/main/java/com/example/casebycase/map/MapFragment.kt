package com.example.casebycase.map

import android.Manifest
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

import com.example.casebycase.databinding.FragmentMapBinding
import com.skt.Tmap.TMapGpsManager
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView





class MapFragment: Fragment(), TMapGpsManager.onLocationChangedCallback {

    private var _binding: FragmentMapBinding ?= null
    private val binding get() = _binding!!
    var API_Key = "l7xxf8a0d7c16d01466a8bae0f6451de98be"

    // T Map View
    var tMapView: TMapView? = null

    // T Map GPS
    var tMapGPS: TMapGpsManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        val root: View = binding.root
//        tMapView = TMapView(context)
//        tMapView!!.setCenterPoint(37.50067,126.86780)
//
//        tMapView!!.setSKTMapApiKey(API_Key)
//
//
//        tMapView!!.zoomLevel = 17
//        tMapView!!.setIconVisibility(true)
//        tMapView!!.setCenterPoint(37.50067,126.86780, true)
//        tMapView!!.mapType = TMapView.MAPTYPE_STANDARD
//        tMapView!!.setLanguage(TMapView.LANGUAGE_KOREAN)
//
//
//        markerSetting()
//
//
//        val linearLayoutTmap = binding.linearLayoutTmap as LinearLayout
//        linearLayoutTmap.addView(tMapView)
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//        }
//
//        tMapGPS = TMapGpsManager(context)
//        tMapGPS!!.minTime = 1000
//        tMapGPS!!.minDistance = 10f
//        tMapGPS!!.provider = TMapGpsManager.NETWORK_PROVIDER
//
//        tMapGPS!!.OpenGps()


        // T Map View
        // T Map View
        tMapView = TMapView(context)

        // API Key

        // API Key
        tMapView!!.setSKTMapApiKey(API_Key)

        // Initial Setting

        // Initial Setting
        tMapView!!.zoomLevel = 17
        tMapView!!.setIconVisibility(true)
        tMapView!!.mapType = TMapView.MAPTYPE_STANDARD
        tMapView!!.setLanguage(TMapView.LANGUAGE_KOREAN)

        // T Map View Using Linear Layout

        // T Map View Using Linear Layout
        val linearLayoutTmap = binding.linearLayoutTmap
        linearLayoutTmap.addView(tMapView)

        // Request For GPS permission

        // Request For GPS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        // GPS using T Map

        // GPS using T Map
        tMapGPS = TMapGpsManager(context)

        // Initial Setting

        // Initial Setting
        tMapGPS!!.minTime = 1000
        tMapGPS!!.minDistance = 10f
        tMapGPS!!.provider = TMapGpsManager.NETWORK_PROVIDER
        //tMapGPS.setProvider(tMapGPS.GPS_PROVIDER);

        //tMapGPS.setProvider(tMapGPS.GPS_PROVIDER);
        tMapGPS!!.OpenGps()



        return root
    }

    private fun markerSetting(){
        val markerItem1 = TMapMarkerItem()
        val markerItem2 = TMapMarkerItem()
        val markerItem3 = TMapMarkerItem()
        val markerItem4 = TMapMarkerItem()

        val tMapPoint1 = TMapPoint(37.50067, 126.86780)
        val tMapPoint2 = TMapPoint(37.54697,127.00693)
        val tMapPoint3 = TMapPoint(37.56980,127.02178)
        val tMapPoint4 = TMapPoint(37.56034,37.56034)

        val bitmap = BitmapFactory.decodeResource(context!!.resources, com.example.casebycase.R.drawable.ic_baseline_home_24)
        markerItem1.icon = bitmap // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f) // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.tMapPoint = tMapPoint1 // 마커의 좌표 지정
        markerItem1.name = "동양미래대학교" // 마커의 타이틀 지정

        markerItem2.icon = bitmap // 마커 아이콘 지정
        markerItem2.setPosition(0.5f, 1.0f) // 마커의 중심점을 중앙, 하단으로 설정
        markerItem2.tMapPoint = tMapPoint2 // 마커의 좌표 지정
        markerItem2.name = "명동서점" // 마커의 타이틀 지정

        markerItem3.icon = bitmap // 마커 아이콘 지정
        markerItem3.setPosition(0.5f, 1.0f) // 마커의 중심점을 중앙, 하단으로 설정
        markerItem3.tMapPoint = tMapPoint3 // 마커의 좌표 지정
        markerItem3.name = "명동서점" // 마커의 타이틀 지정

        markerItem4.icon = bitmap // 마커 아이콘 지정
        markerItem4.setPosition(0.5f, 1.0f) // 마커의 중심점을 중앙, 하단으로 설정
        markerItem4.tMapPoint = tMapPoint4 // 마커의 좌표 지정
        markerItem4.name = "명동서점" // 마커의 타이틀 지정


        tMapView!!.addMarkerItem("markerItem1", markerItem1)
        tMapView!!.addMarkerItem("markerItem2", markerItem2)
        tMapView!!.addMarkerItem("markerItem3", markerItem3)
        tMapView!!.addMarkerItem("markerItem4", markerItem4)
        // 지도에 마커 추가
//        tMapView!!.setCenterPoint(37.50067, 126.86780)
    }


    override fun onLocationChange(p0: Location?) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}