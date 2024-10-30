package com.dom.lab07.networking

import com.dom.lab07.networking.responce.categories_D
import com.dom.lab07.networking.responce.filter_D
import com.dom.lab07.networking.responce.lookup_D

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService{

    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMealCategories(): Call<categories_D> {
        return api.getMealCategories()
    }

    fun getMealsByCategory(category: String): Call<filter_D> {
        return api.getMealsByCategory(category)
    }

    fun getMealById(mealId: String): Call<lookup_D> {
        return api.getMealById(mealId)
    }

}