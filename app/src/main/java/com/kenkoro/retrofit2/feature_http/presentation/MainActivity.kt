package com.kenkoro.retrofit2.feature_http.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.kenkoro.retrofit2.feature_http.data.repository.RepositoryImpl
import com.kenkoro.retrofit2.feature_http.data.source.DummyProductApi
import com.kenkoro.retrofit2.feature_http.domain.repository.Repository
import com.kenkoro.retrofit2.ui.theme.Retrofit2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val retrofit = Retrofit.Builder()
      .baseUrl("https://dummyjson.com")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
    val api = retrofit.create<DummyProductApi>()
    val repository: Repository = RepositoryImpl(api)

    val awaitResult = lifecycleScope.async(Dispatchers.IO) {
      repository.getProductById(1)
    }

    lifecycleScope.launch {
      println("Result: ${awaitResult.await()}")
    }

    setContent {
      Retrofit2Theme {
      }
    }
  }
}