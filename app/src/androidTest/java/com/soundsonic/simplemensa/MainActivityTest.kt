package com.soundsonic.simplemensa

import android.view.Gravity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.soundsonic.simplemensa.di.MockNetworkModule.HOST
import com.soundsonic.simplemensa.di.MockNetworkModule.PORT
import com.soundsonic.simplemensa.extensions.enqueueResponse
import com.soundsonic.simplemensa.util.atPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
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
    fun showCanteenAndNavigateToMeal() {
        mockWebServer.enqueueResponse("canteens.json")
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(1000)
        onView(withId(R.id.canteen_recycler_view))
            .check(matches(atPosition(0, hasDescendant(withText("Mensa Reichenbachstraße")))))
        mockWebServer.enqueueResponse("canteen_meals.json")
        onView(withId(R.id.canteen_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(allOf(withId(R.id.meal_recycler_view), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(
                            withText("Chili sin carne (I, L) mit Basmati Reis, dazu Salat (J)")
                        )
                    )
                )
            )
        onView(allOf(withId(R.id.meal_recycler_view), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun navigateThroughDrawer() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open())
        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_map))
        Thread.sleep(3000)
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open())
        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_settings))
    }
}
