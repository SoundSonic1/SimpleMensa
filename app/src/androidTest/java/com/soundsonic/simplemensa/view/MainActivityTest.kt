package com.soundsonic.simplemensa.view

import android.view.Gravity
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.nextmove.eclever.util.clickOnViewChild
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.MainActivity
import com.soundsonic.simplemensa.util.SharedPrefsHelper.clearSharedPrefs
import com.soundsonic.simplemensa.util.TestConstants.WAITING_TIME
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
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

    @Before
    fun clearPrefs() {
        clearSharedPrefs(InstrumentationRegistry.getInstrumentation().targetContext)
        Thread.sleep(2000)
    }

    @Test
    fun navigateThroughDrawer() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.drawerLayoutMain))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.navViewMain)).perform(NavigationViewActions.navigateTo(R.id.nav_map))

        Thread.sleep(WAITING_TIME)
        onView(withId(R.id.toolbarMain)).check(matches(hasDescendant(withText(R.string.menu_map))))

        Espresso.pressBack()

        onView(withId(R.id.drawerLayoutMain))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        onView(withId(R.id.navViewMain)).perform(NavigationViewActions.navigateTo(R.id.nav_balance))
        onView(withId(R.id.toolbarMain))
            .check(matches(hasDescendant(withText(R.string.menu_balance))))
    }

    @Test
    fun changeTheme() {
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(WAITING_TIME)

        onView(withId(R.id.recyclerViewMain)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                clickOnViewChild(R.id.mensaName)
            )
        )
        onView(withId(R.id.drawerLayoutMain))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())

        onView(withId(R.id.switch_compat_main)).perform(click())

        onView(withId(R.id.drawerLayoutMain))
            .check(matches(isOpen(Gravity.LEFT)))
            .perform(DrawerActions.close())

        onView(allOf(instanceOf(ImageButton::class.java), withParent(withId(R.id.toolbarMain))))
            .perform(click())

        onView(withId(R.id.recyclerViewMain)).check(matches(isDisplayed()))
    }
}
