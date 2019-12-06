package com.soundsonic.simplemensa.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.fragment.CanteenFragment
import com.soundsonic.simplemensa.util.replaceFragmentNoBackStack
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.toolbarMain

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)

        toolbarMain.title = getString(R.string.app_name)

        replaceFragmentNoBackStack(supportFragmentManager, R.id.mainContent, CanteenFragment())
    }
}
