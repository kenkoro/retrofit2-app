package com.kenkoro.retrofit2.feature_http.data.source

import com.kenkoro.retrofit2.feature_http.domain.model.AuthRequest
import com.kenkoro.retrofit2.feature_http.domain.model.Product
import com.kenkoro.retrofit2.feature_http.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DummyApi {
  @GET("products/{id}")
  suspend fun getProductById(@Path("id") id: Int): Product

  @POST("auth/login")
  suspend fun auth(@Body authResult: AuthRequest): User
}