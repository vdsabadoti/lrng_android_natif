package com.example.demoeni
/*
class AuthInterceptor: Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val response = chain.proceed(originalRequest)
    // Check if the response indicates that the access token is expired
    if (response.code == 401) {
      // Call the refresh token API to obtain a new access token
        callRefreshTokenAPI()

        // Create a new request with the updated access token
      val newRequest = originalRequest.newBuilder()
        .header("Authorization", "Bearer $newAccessToken")
        .build()
      // Retry the request with the new access token
      return chain.proceed(newRequest)
    }
    return response
  }
  private suspend fun callRefreshTokenAPI() {
      val response = LoginService.LoginApi.retrofitService.login(User.getUserOnline())
      if (response.code == "200"){
          User.setToken(response.data)
      }
  }
}


// https://medium.com/@manuchekhrdev/access-token-expiration-refresh-token-retrofit-interceptor-coroutines-c3c75069de86
*/