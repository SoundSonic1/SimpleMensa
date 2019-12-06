package com.soundsonic.simplemensa.repositories

import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.CanteenRepository
import com.soundsonic.simplemensa.data.repositories.CanteenRepositoryImpl
import com.soundsonic.simplemensa.util.provideApi
import com.soundsonic.simplemensa.util.provideMockApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CanteenRepositoryTest {

    private val repo: CanteenRepository = CanteenRepositoryImpl(provideApi())

    @Test
    fun `fetch canteens`() {
        runBlocking {
            assertTrue(repo.getCanteens().isNotEmpty())
        }
    }

    @Test
    fun `find canteen`() {
        val canteen = Canteen(
            id = 4,
            name = "Alte Mensa",
            city = "Dresden",
            address = "Mommsenstr. 13, 01069 Dresden",
            coordinates = listOf(51.02696733929933, 13.726491630077364),
            url = "https://www.studentenwerk-dresden.de/mensen/details-alte-mensa.html",
            menu = "https://www.studentenwerk-dresden.de/mensen/speiseplan/alte-mensa.html"
        )

        runBlocking {
            assertTrue(repo.getCanteens().contains(canteen))
        }
    }

    @Test
    fun `wrong endpoint`() {
        val mockRepo = CanteenRepositoryImpl(provideMockApi())
        runBlocking {
            assertTrue(mockRepo.getCanteens().isEmpty())
        }
    }
}
