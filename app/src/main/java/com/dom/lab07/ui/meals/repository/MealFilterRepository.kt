package com.dom.lab07.ui.meals.repository

import com.dom.lab07.networking.MealsWebService
import com.dom.lab07.networking.responce.filter_D
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealFilterRepository(private val webService: MealsWebService= MealsWebService()) {
    fun getMealsByCategory(category: String, successCallback: (response: filter_D?) -> Unit){
        return webService.getMealsByCategory(category).enqueue(object: Callback<filter_D>{
            override fun onResponse(
                call: Call<filter_D>,
                response: Response<filter_D>
            ){
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<filter_D>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}