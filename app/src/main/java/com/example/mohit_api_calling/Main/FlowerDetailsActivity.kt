package com.example.mohit_api_calling.Main

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mohit_api_calling.R
import com.squareup.picasso.Picasso

class FlowerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flower_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(R.layout.activity_flower_details)

        val flowerName = findViewById<TextView>(R.id.tv_flower_name)
        val flowerImage = findViewById<ImageView>(R.id.img_flower)
        val flowerDescription = findViewById<TextView>(R.id.tv_flower_description)
        val BackButton = findViewById<Button>(R.id.btn_back)

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")

        flowerName.text = name
        flowerDescription.text = description
        // Load image using Picasso
        Picasso.get().load(image).into(flowerImage)

        BackButton.setOnClickListener {
            finish() // Close the FlowerDetailsActivity and return to MainActivity
        }
    }
}