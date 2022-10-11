package com.example.casebycase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.casebycase.login.RegisterService
import com.example.casebycase.login.SignUpInfo
import com.example.casebycase.login.SignUpMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regitster)

        val nameRegister = findViewById<EditText>(R.id.registerNameTextView)
        val emailRegister = findViewById<EditText>(R.id.registerEmailTextView)
        val passwordRegister = findViewById<EditText>(R.id.registerPasswordTextView)
        val nicknameRegister = findViewById<EditText>(R.id.registerNicknameTextView)
        val phoneRegister = findViewById<EditText>(R.id.registerPhoneTextView)

        val btnRegister = findViewById<Button>(R.id.buttonRegisterSubmit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://parkbomin.iptime.org:18000/") // 주소는 본인의 서버 주소로 설정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RegisterService::class.java)

        btnRegister.setOnClickListener {
            val nameStr = nameRegister?.text.toString()
            val emailStr = emailRegister?.text.toString()
            val passwordStr = passwordRegister?.text.toString()
            val nicknameStr = nicknameRegister?.text.toString()
            val phoneStr = phoneRegister?.text.toString()




                service.register(SignUpMessage(nameStr,emailStr,passwordStr,nicknameStr,phoneStr)).enqueue(object :
                    Callback<SignUpInfo> {
                    override fun onResponse(call: Call<SignUpInfo>, response: Response<SignUpInfo>) {
                        if(response.isSuccessful){
                            Toast.makeText(this@RegisterActivity, "회원가입 완료! 로그인창으로 돌아가세요", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }else if(!response.isSuccessful){
                            Toast.makeText(this@RegisterActivity, "올바르게 작성해주세요.", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<SignUpInfo>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity, "서버와 응답이 안됩니다.", Toast.LENGTH_SHORT).show()
                    }
                })
            }



    }
}