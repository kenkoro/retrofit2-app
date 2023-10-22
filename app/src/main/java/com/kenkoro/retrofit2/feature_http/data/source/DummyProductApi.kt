package com.kenkoro.retrofit2.feature_http.data.source

import com.kenkoro.retrofit2.feature_http.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyProductApi {
  @GET("products/{id}")
  suspend fun getProductById(@Path("id") id: Int): Product
}