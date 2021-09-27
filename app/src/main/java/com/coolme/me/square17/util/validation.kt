package com.coolme.me.square17.util

import org.intellij.lang.annotations.RegExp

fun isEmailValid(email: String): Boolean
{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password1: String, password2: String): Boolean
{
    if (password1 == password2)
    {
        val pattern: String = "^([a-zA-Z0-9!@#$%^&*()_+=-]{8,31}$)"
        val regex: Regex = pattern.toRegex()
        return password1.matches(regex = regex)
    }
    return false
}

fun isUsernameValid(username: String): Boolean
{
    val pattern: String = "^([a-z]{5})([a-z0-9]{3,31}$)"
    val regex: Regex = pattern.toRegex()
    return username.matches(regex = regex)
}