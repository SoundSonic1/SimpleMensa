package com.soundsonic.simplemensa.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nextmove.eclever.util.clickOnViewChild
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.ui.main.MainActivity
import com.soundsonic.simplemensa.util.Constants.WAITING_TIME
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanteenFragmentTest {

    @Test
    fun selectCanteen() {
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(WAITING_TIME)

        (0..2).forEach { index ->
            onView(withId(R.id.recyclerViewMain)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    clickOnViewChild(R.id.mensaName)
                )
            )
            Espresso.pressBack()
        }
    }
}
