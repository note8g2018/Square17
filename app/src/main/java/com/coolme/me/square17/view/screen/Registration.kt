package com.coolme.me.square17.view.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coolme.me.square17.util.ErrorMine
import com.coolme.me.square17.util.isEmailValid
import com.coolme.me.square17.controller.RegisterVM
import com.coolme.me.square17.view.widget.*
import com.coolme.me.square17.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun Registration(navController: NavController,
    registerVM: RegisterVM = viewModel()
                )
{
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    var screenWidth : Int by remember { mutableStateOf(0) }
    var xOffsetState : Dp by remember { mutableStateOf(0.dp) }

    val xOffset : Dp by animateDpAsState(
        targetValue = xOffsetState,
        animationSpec = tween(
            durationMillis = 900,
            easing = LinearEasing,
        ),
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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
        topBar = { TopBarRegistration(navController = navController) },
    )
    {
        Box( modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
              )
        {
            Email(
                registerVM = registerVM,
                onNext = {
                    xOffsetState = Dp(-0.35f * screenWidth.toFloat())
                },
                xOffset = xOffset,
                 )
            Password(
                registerVM = registerVM,
                screenWidth = screenWidth,
                onBack = {
                    xOffsetState = 0.dp // Dp(0.35f * screenWidth.toFloat())
                },
                onNext = {
                    xOffsetState = Dp(-0.35f * 2.0f * screenWidth.toFloat())
                },
                xOffset = xOffset,
                    )
            Username(
                registerVM = registerVM,
                screenWidth = screenWidth,
                onBack = {
                    xOffsetState = Dp(-0.35f * screenWidth.toFloat())
                },
                onNext = {

                },
                xOffset = xOffset,
                    )


        }
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