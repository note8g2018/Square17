package com.coolme.me.square17.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import com.coolme.me.square17.view.modifierExtention.shadowWithColor
import com.coolme.me.square17.ui.theme.*
import com.coolme.me.square17.view.theme.*
import com.coolme.me.square17.view.widget.TopBarWall

@Composable
fun Wall(navController: NavController)
{
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    Scaffold(
        modifier = Modifier
            .focusRequester(focusRequester)
            .focusTarget()
            .pointerInput(Unit) { detectTapGestures { focusRequester.requestFocus() } },
        topBar = { TopBarWall() },
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Box(
                modifier = Modifier
                    .padding(PaddingAll)
                    .fillMaxWidth()
                    .shadowWithColor(
                        color = TopBarContent,
                        shadowRadius = ShadowRadius,
                    )
                    .background(color = BoxBackground)
            )
            {
                Column(
                    modifier = Modifier
                        .padding(PaddingColumn)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(space = SpaceColumnHeight),
                )
                {
                    Text(
                        text = "Wall",
                    )
                }
            }
        }
    }
}