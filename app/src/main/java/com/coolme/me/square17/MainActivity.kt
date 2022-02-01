package com.coolme.me.square17

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coolme.me.square17.screen.Login
import com.coolme.me.square17.screen.Registration
import com.coolme.me.square17.screen.Wall
import com.coolme.me.square17.ui.theme.Square16Theme
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : ComponentActivity()
{
    // Realm
    lateinit var backgroundThreadRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        // Realm
        Realm.init(this) // context, usually an Activity or Application
        val localDBRealm: String = "localDBRealm"
        val config = RealmConfiguration.Builder().name(localDBRealm).build()
        //backgroundThreadRealm = Realm.getInstance(config)
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm)
            {
                backgroundThreadRealm = realm
                println("Realm is open")
                Log.v("EXAMPLE", "Successfully fetched realm instance.")
            }

            fun onError(e: java.lang.Exception)
            {
                println("Realm is Error ${e.message}")
                Log.e("EXAMPLE", "Failed to get realm instance: $e")
            }
        })


        setContent {
            Square16Theme(darkTheme = true) {
                Navigation()
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
        // the ui thread realm uses asynchronous transactions, so we can only safely close the realm
        // when the activity ends and we can safely assume that those transactions have completed
        backgroundThreadRealm.close() // Realm
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    Square16Theme {
        Navigation()
    }
}

@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login"
           )
    {
        composable(route= "Login") { Login(navController = navController) }
        composable(route= "Registration") { Registration(navController = navController) }
        composable(route= "Wall") { Wall(navController = navController) }
    }
}




