package com.dom.lab07.networking

import com.dom.lab07.networking.responce.categories_D
import com.dom.lab07.networking.responce.filter_D
import com.dom.lab07.networking.responce.lookup_D

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface MealsApi {

    @GET("categories.php")
    fun getMealCategories(): Call <categories_D>

    // Método GET para obtener comidas por categoría
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category:String): Call <filter_D>

    // Método GET para obtener detalles de una comida por su ID
    @GET("lookup.php")
    fun getMealById(@Query("i") mealId:String): Call <lookup_D>

}