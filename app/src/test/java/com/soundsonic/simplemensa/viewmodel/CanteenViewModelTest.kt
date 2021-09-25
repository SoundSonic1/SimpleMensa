package com.soundsonic.simplemensa.viewmodel

import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.UserRepository
import com.soundsonic.simplemensa.ui.home.CanteenViewModel
import com.soundsonic.simplemensa.util.InstantExecutorExtension
import com.soundsonic.simplemensa.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, MockitoExtension::class)
class CanteenViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var canteenRepository: CanteenRepository

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun beforeEach() = Dispatchers.setMain(mainThreadSurrogate)

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `load canteens on creation`() {

        runBlocking {
            `when`(userRepository.userProfile).thenReturn(MutableStateFlow(null))

            `when`(canteenRepository.getCanteens()).thenReturn(
                listOf(
                    Canteen(
                        id = 1,
                        name = "",
                        city = "",
                        address = "",
                        coordinates = listOf(),
                        url = "",
                        menu = ""
                    )
                )
            )

            val viewModel = CanteenViewModel(userRepository, canteenRepository)
            assertTrue(viewModel.canteens.getOrAwaitValue().isNotEmpty())
        }
    }
}
