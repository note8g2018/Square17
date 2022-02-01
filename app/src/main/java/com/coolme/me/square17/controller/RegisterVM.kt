package com.coolme.me.square17.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.coolme.me.square17.util.isEmailValid
import com.coolme.me.square17.util.isPasswordValid
import com.coolme.me.square17.util.isUsernameValid

class RegisterVM : ViewModel()
{
    // state
    var username : String by mutableStateOf<String>(value = "")
        private set
    var usernameHasError : Boolean by mutableStateOf<Boolean>(value = false)
        private set
    var password1 : String by mutableStateOf<String>(value = "")
        private set
    var password2 : String by mutableStateOf<String>(value = "")
        private set
    var passwordHasError : Boolean by mutableStateOf<Boolean>(value = false)
        private set
    var email : String by mutableStateOf<String>(value = "")
        private set
    var emailHasError : Boolean by mutableStateOf<Boolean>(value = false)
        private set

    fun onUsernameChange(newUsername: String)
    {
        username = newUsername
    }
    fun validateUsername()
    {
        usernameHasError = !isUsernameValid(username= username)
    }
    fun onPassword1Change(newPassword: String)
    {
        password1 = newPassword
    }
    fun onPassword2Change(newPassword: String)
    {
        password2 = newPassword
    }
    fun validatePassword()
    {
        passwordHasError = !isPasswordValid(password1 = password1, password2 = password2)
    }
    fun onEmailChange(newEmail: String)
    {
        email = newEmail
    }
    fun validateEmail()
    {
        emailHasError = !isEmailValid(email)
    }
}