package com.pramod.store.api



import com.pramod.store.model.ApiResponse
import com.pramod.store.model.LoginRequest
import com.pramod.store.model.LoginResponse
import com.pramod.store.model.RegisterRequest
import com.pramod.store.model.RegisterResponse
import com.pramod.store.model.UserProfile

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("user/update")
    suspend   fun updateUserProfile(@Body profile: UserProfile): Response<ApiResponse>

    @POST("login") // aapka endpoint
    suspend fun login(@Body  loginRequest: LoginRequest): Response<LoginResponse>


    @POST("user/Register")
    suspend fun updateUserProfile(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}
