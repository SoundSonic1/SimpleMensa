package com.soundsonic.simplemensa

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.soundsonic.simplemensa.di.MockNetworkModule.HOST
import com.soundsonic.simplemensa.di.MockNetworkModule.PORT
import com.soundsonic.simplemensa.extensions.enqueueResponse
import com.soundsonic.simplemensa.util.atPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.InetAddress
import javax.inject.Inject

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Before
    fun startServer() {
        hiltRule.inject()
        mockWebServer.start(InetAddress.getByName(HOST), PORT)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun showCanteens() {
        mockWebServer.enqueueResponse("canteens.json")
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.canteen_recycler_view))
            .check(matches(atPosition(0, hasDescendant(withText("Mensa Reichenbachstraße")))))
    }
}
