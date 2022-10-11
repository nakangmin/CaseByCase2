package com.example.casebycase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.casebycase.databinding.ActivityLoginBinding
import com.example.casebycase.login.PostLogin
import com.example.casebycase.login.PostLogin2
import com.example.casebycase.login.SignService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val button = findViewById<Button>(R.id.loginBtn)
        val register = findViewById<TextView>(R.id.registerTextView)
        val passwordSearchTextView = findViewById<TextView>(R.id.passwordSearchTextView)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://parkbomin.iptime.org:18000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SignService::class.java)

        button.setOnClickListener {
            val idStr = email.text.toString()
            val pwStr = password.text.toString()

            service.login(PostLogin2(idStr,pwStr)).enqueue(object : Callback<PostLogin> {
                override fun onResponse(call: Call<PostLogin>, response: Response<PostLogin>) {
                    if(response.isSuccessful){
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }else if(!response.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "다시입력하세요.", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<PostLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "서버오류!! + ${t.toString()}", Toast.LENGTH_LONG).show()
                    Log.e("로그인", t.localizedMessage)
                }
            })
        }

        register.setOnClickListener {
            Intent(this, RegisterActivity::class.java).run {
                startActivity(this)
            }
        }

        passwordSearchTextView.setOnClickListener {
            Intent(this, PasswordSearchActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}