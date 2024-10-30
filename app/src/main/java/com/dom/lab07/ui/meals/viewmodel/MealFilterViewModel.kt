package com.dom.lab07.ui.meals.viewmodel

import androidx.lifecycle.ViewModel
import com.dom.lab07.networking.responce.filter_D
import com.dom.lab07.ui.meals.repository.MealFilterRepository

class MealFilterViewModel(private val repository: MealFilterRepository= MealFilterRepository()) : ViewModel() {
    fun getMealsByCategory(
        category: String,
        successCallback: (response: filter_D?) -> Unit
    ) {
        repository.getMealsByCategory(category) { response ->
            successCallback(response)
        }
    }
}