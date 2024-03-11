package com.example.demoeni
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.demoeni.services.LoginService
import com.example.demoeni.viewmodel.User
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import java.security.AccessController.getContext
/*
class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 403) {
            redirectToLogin()
        }

        return response
    }

    private fun redirectToLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}


// https://medium.com/@manuchekhrdev/access-token-expiration-refresh-token-retrofit-interceptor-coroutines-c3c75069de86
 */