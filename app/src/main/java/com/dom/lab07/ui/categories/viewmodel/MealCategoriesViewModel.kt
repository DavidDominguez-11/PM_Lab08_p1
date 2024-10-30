package com.dom.lab07.ui.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom.lab07.database.categories.MealCategoryDao
import com.dom.lab07.database.categories.MealCategoryEntity
import com.dom.lab07.ui.categories.repository.MealsCategoriesRepository
import kotlinx.coroutines.launch

class MealCategoriesViewModel(private val repository: MealsCategoriesRepository) : ViewModel() {

    fun getMealCategories(successCallback: (List<MealCategoryEntity>) -> Unit) {
        viewModelScope.launch {
            val categories = repository.getMealCategories()
            successCallback(categories)
        }
    }
}
