package com.example.stoicquotesgenerator

import retrofit2.Call
import retrofit2.http.GET

interface QuoteDataInterface {
    @GET("/stoic-quote")
    fun getQuoteDetails(): Call<QuoteData>
}