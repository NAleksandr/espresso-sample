package com.busylee.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.Test

class MainTest {

    companion object {
        private const val CONST_TEXT = "Test text!"
    }

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun typeText() {

        //Проверяем заголовок
        onView(withId(R.id.action_bar))
            .check(matches(hasDescendant(withText("My Application"))))

        //Проверяем отображение надписи "Введите текст"
        onView(withText("Enter a text!")).check(matches(isDisplayed()))

        //Вводим текст и сворачиваем клавиатуру
        onView(withId(R.id.et_text)).perform(
            typeText(CONST_TEXT), closeSoftKeyboard())

        //Проверяем текст на кнопку и кликаем по ней
        onView(withId(R.id.btn_action)).check(matches(withText("OPEN")))
            .perform(click())

        //Проверяем отображение надписи "Ваш текст"
        onView(withText("Your text:")).check(matches(isDisplayed()))

        //Проверяем, что значение поля == введеному значению
        onView(withId(R.id.tv_text)).check(matches(withText(CONST_TEXT)))
    }
}