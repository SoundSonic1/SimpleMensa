package com.soundsonic.simplemensa.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.ui.main.adapter.CanteenListAdapter
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

        val adapter = CanteenListAdapter()

        recyclerViewMain.adapter = adapter
        recyclerViewMain.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            adapter.submitList(api.getCanteens())
        }
    }
}
