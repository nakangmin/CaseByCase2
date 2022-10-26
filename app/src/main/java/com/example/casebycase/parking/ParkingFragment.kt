package com.example.casebycase.parking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.casebycase.R
import com.example.casebycase.databinding.FragmentParkingBinding
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class ParkingFragment: Fragment() {
    private var parkingBinding: FragmentParkingBinding?=null
    private val binding get() = parkingBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parkingBinding = FragmentParkingBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.webview.getSettings().setUseWideViewPort(true);
//        binding.webview.getSettings().setLoadWithOverviewMode(true);
//
//        binding.webview.apply{
//            webViewClient = WebViewClient()
//            settings.javaScriptEnabled= true
//        }
//        binding.webview.loadUrl("http://parkbomin.iptime.org:8081/html/mapview.html")
//
//        binding.reloadbutton.setOnClickListener {
//            binding.webview.reload()
//        }

        //온습도 받아오기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://parkbomin.iptime.org:18000")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TemHum::class.java)!!

        val tem = 0
        val hum = 0

        val callGetTH = service.TH()

        callGetTH.enqueue(object : Callback<exampleResponse> {
            override fun onResponse(call: Call<exampleResponse>, response: Response<exampleResponse>){
                Log.d("Parking", response.toString())
                response.body().let {
                    Log.d("Parking", it.toString())
//                    binding.hum.text = it?.MESSAGE?.get(hum)?.humi.toString()
//                    binding.temp.text = it?.MESSAGE?.get(tem)?.tem.toString()
                }

            }
            override fun onFailure(call: Call<exampleResponse>, t: Throwable) {
                Log.e("Parking", t.toString())
            }
        })




    }

}
interface TemHum {
    @GET("/sensor/tem-gps/")
    fun TH (
    ): Call<exampleResponse>
}

data class exampleResponse(
    val MESSAGE: List<Message>
)

data class Message(
    @SerializedName("tem") val tem:String?,
    @SerializedName("humi") val humi:String?
)