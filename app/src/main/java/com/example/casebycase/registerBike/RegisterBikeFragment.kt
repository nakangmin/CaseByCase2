package com.example.casebycase.registerBike

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.casebycase.MainActivity
import com.example.casebycase.R
import com.example.casebycase.databinding.ActivityMainBinding
import com.example.casebycase.databinding.FragmentRegisterBinding
import com.example.casebycase.login.PostLogin
import com.example.casebycase.login.PostLogin2
import com.example.casebycase.login.SignService
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisterBikeFragment: Fragment(R.layout.fragment_register) {

    private var registerBinding: FragmentRegisterBinding?=null
    private val binding get() = registerBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://parkbomin.iptime.org:18000")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BicycleData::class.java)!!

        binding.registButton.setOnClickListener{
            //자전거 등록 정보 서버에 넘기기
            val name = binding.editBicycleName.text.toString()
            val model = binding.editModel.text.toString()
            val year = binding.editYear.text.toString()
            val type = binding.editCategory.text.toString()

            //화면상 자전거 이름 바꾸기
            binding.bicycleName.text = name

            //자전거 등록 날짜 구현
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ISO_DATE
            val formatted = current.format(formatter)

            //화면상 등록일 갱신
            binding.registDay.setText(formatted)

            //자전거 등록 정보 서버에 넘기기
            service.registB(myBicycle(name, model,year,type)).enqueue(object : Callback<BicycleResponse> {
                override fun onResponse(call: Call<BicycleResponse>, response: Response<BicycleResponse>) {
                    Log.d("자전거 등록", "자전거 등록 완료")
                }

                override fun onFailure(call: Call<BicycleResponse>, t: Throwable) {
                    Log.e("실패", t.localizedMessage)
                }
            })
        }

        //등록 해제
        binding.registCancleButton.setOnClickListener {
            binding.bicycleName.text = null
            binding.registDay.text = null
        }

        //이미지 등록
        binding.registImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GET_IMAGE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registerBinding = null
    }

    //이미지 추가

    val REQUEST_GET_IMAGE = 105

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_GET_IMAGE -> {
                    try{
                        var uri = data?.data
                        binding.registImage.setImageURI(uri)
                        binding.bicycleImage.setImageURI(uri)
                    }catch (e:Exception){}
                }
            }
        }
    }


}

interface BicycleData {
    @Headers("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxfQ.OqqZEfitVLZec9TumMyemvUhi-Ut_x4felx4r1w1Zb0")
    @POST("/bicycle/register/")
    fun registB (
//        @Header("Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxfQ.OqqZEfitVLZec9TumMyemvUhi-Ut_x4felx4r1w1Zb0")
        @Body rdata: myBicycle
    ):Call<BicycleResponse>
}

data class myBicycle(
    val nickname: String?,
    val modelname: String?,
    val year: String?,
    val type: String?
)

data class BicycleResponse(
    val nickname: String?,
    val modelname: String?,
    val year: String?,
    val type: String?
)
