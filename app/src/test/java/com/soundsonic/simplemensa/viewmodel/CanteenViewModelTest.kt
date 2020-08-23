package com.soundsonic.simplemensa.viewmodel

import com.soundsonic.simplemensa.ui.home.CanteenViewModel
import com.soundsonic.simplemensa.util.InstantExecutorExtension
import com.soundsonic.simplemensa.util.getOrAwaitValue
import com.soundsonic.simplemensa.util.provideCanteenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class CanteenViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun beforeEach() = Dispatchers.setMain(mainThreadSurrogate)

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `load canteens on creation`() {
        val viewModel = CanteenViewModel(provideCanteenRepo())
        assertTrue(viewModel.canteens.getOrAwaitValue().isNotEmpty())
    }
}
