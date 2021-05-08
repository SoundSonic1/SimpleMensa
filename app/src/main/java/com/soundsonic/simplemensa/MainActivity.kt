package com.soundsonic.simplemensa

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.soundsonic.simplemensa.databinding.ActivityMainBinding
import com.soundsonic.simplemensa.util.Constants.DARK_THEME_ON
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainLayout.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        if (getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.switchCompatMain.isChecked = true
        }

        binding.switchCompatMain.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPref.edit {
                    putInt(DARK_THEME_ON, AppCompatDelegate.MODE_NIGHT_YES)
                }
            } else {
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPref.edit {
                    putInt(DARK_THEME_ON, AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
