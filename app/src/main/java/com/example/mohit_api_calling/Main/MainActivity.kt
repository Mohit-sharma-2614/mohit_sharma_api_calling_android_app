package com.example.mohit_api_calling.Main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohit_api_calling.Adapter.FlowerCustomAdapter
import com.example.mohit_api_calling.Data.Flower_DataItem
import com.example.mohit_api_calling.Interface.FlowerApi
import com.example.mohit_api_calling.R
import com.example.mohit_api_calling.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), FlowerCustomAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val BASE_URL = "https://mocki.io/v1/"

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlowerApi::class.java)
        api.getFlower().enqueue(object : Callback<List<Flower_DataItem>> {
            override fun onResponse(
                call: Call<List<Flower_DataItem>>,
                response: Response<List<Flower_DataItem>>
            ) {
                if (response.isSuccessful) {
                    val dataSet = response.body() ?: emptyList()
                    val customAdapter = FlowerCustomAdapter(dataSet, this@MainActivity)
                    recyclerView.adapter = customAdapter
                }
            }

            override fun onFailure(call: Call<List<Flower_DataItem>>, t: Throwable) {
                Log.i("onFailure", "onFailure ${t.message}")
            }
        })
    }

    override fun onItemClick(flower: Flower_DataItem) {
        val intent = Intent(this, FlowerDetailsActivity::class.java).apply {
            putExtra("name", flower.name)
            putExtra("image", flower.imageUrl)
            putExtra("description", flower.description)
        }
        startActivity(intent)
    }
}
