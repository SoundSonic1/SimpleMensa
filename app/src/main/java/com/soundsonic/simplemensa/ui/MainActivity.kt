package com.soundsonic.simplemensa.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var api: OpenMensaApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)
        toolbarMain.title = getString(R.string.app_name)

        lifecycleScope.launch {
            val canteens = api.getCanteens()
        }

    }
}
