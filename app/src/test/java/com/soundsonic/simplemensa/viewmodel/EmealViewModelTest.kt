package com.soundsonic.simplemensa.viewmodel

import com.soundsonic.simplemensa.data.cardcheck.cardreader.ValueData
import com.soundsonic.simplemensa.ui.emeal.EmealViewModel
import com.soundsonic.simplemensa.util.InstantExecutorExtension
import com.soundsonic.simplemensa.util.getOrAwaitValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class EmealViewModelTest {

    private val viewModel = EmealViewModel()

    @Test
    fun `convert ValueData to euro currency`() {

        viewModel.updateData(ValueData(9400, 1000))
        assertEquals("9,40 €", viewModel.balance.getOrAwaitValue())

        viewModel.updateData(ValueData(0, 0))
        assertEquals("0,00 €", viewModel.balance.getOrAwaitValue())
    }
}
