package com.samset.networksample.network

import com.samset.networksample.modal.User
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Created by Samset on 2/28/25.
Copyright (c) 2025  All rights reserved.
 */


interface ApiService {
  @GET("users")
  suspend fun getUser(): List<User>
}