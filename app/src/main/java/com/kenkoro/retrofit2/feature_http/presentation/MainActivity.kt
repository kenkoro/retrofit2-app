package com.kenkoro.retrofit2.feature_http.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.kenkoro.retrofit2.feature_http.data.repository.RepositoryImpl
import com.kenkoro.retrofit2.feature_http.data.source.DummyProductApi
import com.kenkoro.retrofit2.feature_http.domain.repository.Repository
import com.kenkoro.retrofit2.ui.theme.Retrofit2Theme
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Urls {
  const val BASE_URL = "https://dummyjson.com"
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .build()

    val retrofit = Retrofit.Builder()
      .baseUrl(Urls.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
    val api = retrofit.create<DummyProductApi>()
    val repository: Repository = RepositoryImpl(api)

    lifecycleScope.launch {
      println(repository.getProductById(1))
    }

    setContent {
      Retrofit2Theme {
      }
    }
  }
}