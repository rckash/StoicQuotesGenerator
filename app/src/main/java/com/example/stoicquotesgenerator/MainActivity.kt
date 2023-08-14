package com.example.stoicquotesgenerator

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stoicquotesgenerator.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val BASE_URL = "https://api.themotivate365.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { getQuote() }
    }

    private fun getQuote() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteDataInterface::class.java)

        api.getQuoteDetails().enqueue(object: Callback<QuoteData>{
            override fun onResponse(call: Call<QuoteData>, response: Response<QuoteData>) {
                if (response.isSuccessful) {
                    binding.tvQuote.setText(response.body()?.quote.toString())
                    binding.tvAuthor.setText(response.body()?.author.toString())
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteData>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}