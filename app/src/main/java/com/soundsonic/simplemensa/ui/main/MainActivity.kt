package com.soundsonic.simplemensa.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.map.fragment.MapFragment
import com.soundsonic.simplemensa.util.replaceFragment
import com.soundsonic.simplemensa.util.replaceFragmentNoBackStack
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val mapFragment by lazy { MapFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayoutMain, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayoutMain.addDrawerListener(toggle)
        toggle.syncState()

        navViewMain.setNavigationItemSelectedListener(this)

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true) // show back button
                toolbarMain.setNavigationOnClickListener { onBackPressed() }
            } else {
                // show hamburger
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toggle.syncState()
                toolbarMain.setNavigationOnClickListener {
                    drawerLayoutMain.openDrawer(GravityCompat.START)
                }
            }
        }

        if (savedInstanceState == null) {
            replaceFragmentNoBackStack(supportFragmentManager, R.id.mainContent, CanteenFragment())
        }
    }

    override fun onBackPressed() {
        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_home -> {
                replaceFragment(supportFragmentManager, R.id.mainContent, CanteenFragment())
            }
            R.id.nav_map -> {
                replaceFragment(supportFragmentManager, R.id.mainContent, mapFragment)
            }
        }
        drawerLayoutMain.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        mapFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
