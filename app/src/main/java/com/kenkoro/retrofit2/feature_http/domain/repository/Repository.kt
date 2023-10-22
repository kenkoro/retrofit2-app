package com.kenkoro.retrofit2.feature_http.domain.repository

import com.kenkoro.retrofit2.feature_http.domain.model.AuthRequest
import com.kenkoro.retrofit2.feature_http.domain.model.Product
import com.kenkoro.retrofit2.feature_http.domain.model.User

interface Repository {
  suspend fun getProductById(id: Int): Product

  suspend fun auth(authRequest: AuthRequest): User
}