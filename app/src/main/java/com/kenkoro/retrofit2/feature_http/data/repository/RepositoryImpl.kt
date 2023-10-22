package com.kenkoro.retrofit2.feature_http.data.repository

import com.kenkoro.retrofit2.feature_http.data.source.DummyApi
import com.kenkoro.retrofit2.feature_http.domain.model.AuthRequest
import com.kenkoro.retrofit2.feature_http.domain.model.Product
import com.kenkoro.retrofit2.feature_http.domain.model.User
import com.kenkoro.retrofit2.feature_http.domain.repository.Repository

class RepositoryImpl(
  private val api: DummyApi
) : Repository {
  override suspend fun getProductById(id: Int): Product {
    return api.getProductById(id)
  }

  override suspend fun auth(authRequest: AuthRequest): User {
    return api.auth(authRequest)
  }
}