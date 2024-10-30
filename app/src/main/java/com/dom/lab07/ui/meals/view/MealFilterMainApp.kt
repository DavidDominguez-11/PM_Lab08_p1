package com.dom.lab07.ui.meals.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

import com.dom.lab07.networking.responce.Meal
import com.dom.lab07.ui.mealdetail.view.MealDetail
import com.dom.lab07.ui.meals.viewmodel.MealFilterViewModel

class MealFilter:ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = intent.getStringExtra("category")
        setContent {
            MealFilterMainApp(category)
        }
    }
}


@Composable
fun MealFilterMainApp(category: String?) {
    val viewModel: MealFilterViewModel = viewModel() //ViewModel
    val filteredMeals: MutableState<List<Meal>> = remember { mutableStateOf(emptyList()) } //Lista de estado mutable de las comidas que pertenencen a la categoría seleccionada
    val context = LocalContext.current // context local

    if (category != null) { //Se llama a la función para obtener las comidas de cada categoría desde la API
        viewModel.getMealsByCategory(category) { response ->
            val mealsFromTheApi = response?.meals.orEmpty()
            filteredMeals.value = mealsFromTheApi
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Available Meals",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {//Despliega las comidas de la categoría en un LazyColumn
                items(filteredMeals.value) { meal ->
                    MealItem(meal= meal, context=context)
                }
            }
        }
    }

}

@Composable
fun MealItem(meal: Meal, context: Context) {
    val backgroundcolor= Color(android.graphics.Color.parseColor("#DEB866"))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color= backgroundcolor)
            .clickable {
                val intent = Intent(context, MealDetail::class.java)
                intent.putExtra("mealId", meal.idMeal) // Agrega el valor de la categoría como un extra
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {//Imagen de cada comida
            val painter = rememberAsyncImagePainter(model = meal.strMealThumb)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text( //Nombre de la comida
                text = meal.strMeal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMealFilter() {
    val sampleMeals = listOf(
        Meal(idMeal = "1", strMeal = "Spaghetti Bolognese", strMealThumb = "https://example.com/spaghetti.jpg"),
        Meal(idMeal = "2", strMeal = "Chicken Curry", strMealThumb = "https://example.com/chickencurry.jpg"),
        Meal(idMeal = "3", strMeal = "Vegetable Stir Fry", strMealThumb = "https://example.com/vegetablestirfry.jpg")
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Available Meals",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(sampleMeals) { meal ->
                MealItem(meal = meal, context = LocalContext.current)
            }
        }
    }
}