package com.coolme.me.square17.screen

import android.provider.ContactsContract
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.coolme.me.square17.enum.ScreenState
import com.coolme.me.square17.modifier.extention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.util.ErrorMine
import com.coolme.me.square17.util.isEmailValid
import com.coolme.me.square17.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun Registration()
{
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var screenWidth : Int by remember { mutableStateOf(0) }
    var screenState : ScreenState by remember { mutableStateOf(ScreenState.Email) }

    var xOffsetState : Dp by remember { mutableStateOf(0.dp) }

    val xOffset : Dp by animateDpAsState(
        targetValue = xOffsetState,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing,
        ),
    )




    Scaffold(
        modifier = Modifier
            .focusRequester(focusRequester)
            .focusTarget()
            .pointerInput(Unit) { detectTapGestures { focusRequester.requestFocus() } }
            .onGloballyPositioned {
                screenWidth = it.size.width
            },
        scaffoldState = scaffoldState,
        snackbarHost = {
                       SnackbarMine(snackberHostState = scaffoldState.snackbarHostState,)
        },
        topBar = { TopBarRegistration() },
    )
    {
        Username(
            screenWidth= screenWidth,
            onBack = {
                xOffsetState = Dp(-0.35f * screenWidth.toFloat())
            },
            xOffset = xOffset,
        )
        Password(screenWidth= screenWidth,
            onBack = {
                xOffsetState = 0.dp // Dp(0.35f * screenWidth.toFloat())
            },
            onNext = {
                xOffsetState = Dp(-0.35f * 2.0f * screenWidth.toFloat())
            },
            xOffset = xOffset,
        )
        Email(screenWidth= screenWidth,
            onNext = {
                xOffsetState = Dp(-0.35f * screenWidth.toFloat())
            },
            xOffset = xOffset,
        )


    }
}

//*************************

fun next(email: String, scope: CoroutineScope, scaffoldState: ScaffoldState)
{
    if (isEmailValid(email))
    {
        goToUserName()
    } else
    {
        val error = ErrorMine(
            title = "Email",
            msg = "Please enter a valid Email",
            errorCode = 403,
            errorState = true,
        )
        snackBarEmailError(scope, scaffoldState, error)
    }
}

fun goToUserName()
{

}

fun snackBarEmailError(scope: CoroutineScope, scaffoldState: ScaffoldState, error: ErrorMine)
{
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
            message = error.msg,
        )
    }
}