package com.example.mangami.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mangami.R
import com.example.mangami.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.topAppBar) { view, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            view.updatePadding(top = topInset)
            insets
        }
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.MainfragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.profileFragment, R.id.settingsFragment)
        )
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.topAppBar.setNavigationIcon(R.drawable.ic_arrow_back)
            }
            binding.topAppBar.menu.clear()
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.topAppBar.inflateMenu(R.menu.top_app_home)
                }

                R.id.profileFragment -> {
                    binding.topAppBar.inflateMenu(R.menu.top_app_profile)
                }

                R.id.settingsFragment -> {
                    binding.topAppBar.inflateMenu(R.menu.top_app_settings)
                }
            }
        }
    }
}