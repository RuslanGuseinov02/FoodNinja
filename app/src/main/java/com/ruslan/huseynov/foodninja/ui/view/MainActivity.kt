package com.ruslan.huseynov.foodninja.ui.view


import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Proqramı full screen etmək üçün
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHost.navController)
        navHost.navController.addOnDestinationChangedListener{_,destination,_->

            when(destination.id){

                // Login Page və Sign Up Page-də navigationBar-ı görünməz etmək üçün
                R.id.loginFragment, R.id.signUpFragment -> binding.bottomNavigationView.visibility = View.GONE
                // BasketFragmentdə navigationBar-ı görünməz etmək üçün
                R.id.basketFragment2 -> binding.bottomNavigationView.visibility = View.GONE
                // DetailsFragmentdə navigationBar-ı görünməz etmək üçün
                R.id.detailsFragment -> binding.bottomNavigationView.visibility = View.GONE

                else -> binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}