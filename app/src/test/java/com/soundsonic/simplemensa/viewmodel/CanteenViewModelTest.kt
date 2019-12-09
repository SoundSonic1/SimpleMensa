package com.soundsonic.simplemensa.viewmodel

import com.soundsonic.simplemensa.ui.main.viewmodel.CanteenViewModel
import com.soundsonic.simplemensa.util.InstantExecutorExtension
import com.soundsonic.simplemensa.util.getOrAwaitValue
import com.soundsonic.simplemensa.util.provideMockCanteenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class CanteenViewModelTest {

    init {
        Dispatchers.setMain(newSingleThreadContext("UI thread"))
    }

    @Test
    fun `load canteens on instantiation`() {

        val vm = CanteenViewModel(provideMockCanteenRepo())
        assertTrue(vm.canteens.getOrAwaitValue().isNotEmpty())
    }
}
