package com.soundsonic.simplemensa.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.ui.main.viewmodel.UserProfileViewModel
import com.soundsonic.simplemensa.ui.map.fragment.MapFragment
import com.soundsonic.simplemensa.util.Constants.DARK_THEME_ON
import com.soundsonic.simplemensa.util.NfcReader.getValueData
import com.soundsonic.simplemensa.util.replaceFragment
import com.soundsonic.simplemensa.util.replaceFragmentNoBackStack
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var userProfileViewModel: UserProfileViewModel

    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        nfcAdapter?.let {
            if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
                onNewIntent(intent)
            }
        }

        val toggle = ActionBarDrawerToggle(
            this, drawerLayoutMain, toolbarMain,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayoutMain.addDrawerListener(toggle)
        toggle.syncState()

        navViewMain.setNavigationItemSelectedListener(this)

        if (getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switch_compat_main.isChecked = true
        }

        switch_compat_main.setOnCheckedChangeListener { _, isChecked ->
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

        supportFragmentManager.addOnBackStackChangedListener {

            when (supportFragmentManager.findFragmentById(R.id.mainContent)) {
                is CanteenFragment -> {
                    navViewMain.setCheckedItem(R.id.nav_home)
                }
                is MapFragment -> {
                    navViewMain.setCheckedItem(R.id.nav_map)
                }
            }

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
            replaceFragmentNoBackStack(
                supportFragmentManager, R.id.mainContent, CanteenFragment(), CANTEEN_FRAGMENT_TAG
            )
            navViewMain.setCheckedItem(R.id.nav_home)
        }
        userProfileViewModel.userProfile.observe(this, Observer {
            it?.let {
                Log.d("user", it.toString())
            }
        })
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
                if (visibleFragments().all { it !is CanteenFragment }) {
                    val fragment = supportFragmentManager
                        .findFragmentByTag(CANTEEN_FRAGMENT_TAG) ?: CanteenFragment()

                    replaceFragment(supportFragmentManager, R.id.mainContent, fragment)
                }
            }
            R.id.nav_map -> {
                if (visibleFragments().all { it !is MapFragment }) {
                    val fragment =
                        supportFragmentManager.findFragmentByTag(MAP_FRAGMENT_TAG) ?: MapFragment()

                    replaceFragment(
                        supportFragmentManager,
                        R.id.mainContent,
                        fragment,
                        MAP_FRAGMENT_TAG
                    )
                }
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
        val fragment = supportFragmentManager.findFragmentByTag(MAP_FRAGMENT_TAG) as? MapFragment
        fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent?.action) {
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            tag?.let {
                getValueData(tag)
            }
        }
    }

    private fun visibleFragments() = supportFragmentManager.fragments.filter {
        it.isVisible
    }

    companion object {
        private const val CANTEEN_FRAGMENT_TAG = "CANTEEN_FRAGMENT_TAG"
        private const val MAP_FRAGMENT_TAG = "MAP_FRAGMENT_TAG"
    }
}
