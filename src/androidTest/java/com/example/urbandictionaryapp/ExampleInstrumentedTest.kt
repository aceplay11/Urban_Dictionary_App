package com.example.urbandictionaryapp

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.urbandictionaryapp", appContext.packageName)
    }

    @Test
    fun checkView() {


        Espresso.onView(ViewMatchers.withId(R.id.btnSubmit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("rat")).check(ViewAssertions.doesNotExist())

    }
}
