package com.samset.networksample.utils

/**
 * Created by Samset on 2/28/25.
Copyright (c) 2025  All rights reserved.
 */

sealed class ApiState<out T> {
  object Loading : ApiState<Nothing>()
  data class Success<out T>(val data: T) : ApiState<T>()
  data class Error(val message: String) : ApiState<Nothing>()
}