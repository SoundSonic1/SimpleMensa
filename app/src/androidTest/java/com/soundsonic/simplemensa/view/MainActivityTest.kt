package com.soundsonic.simplemensa.view

import android.view.Gravity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.MainActivity
import com.soundsonic.simplemensa.util.Constants.WAITING_TIME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mGrantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @Test
    fun navigateToMap() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.drawerLayoutMain))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.navViewMain)).perform(NavigationViewActions.navigateTo(R.id.nav_map))

        Thread.sleep(WAITING_TIME)
        onView(withId(R.id.toolbarMain)).check(matches(hasDescendant(withText(R.string.menu_map))))
    }
}
