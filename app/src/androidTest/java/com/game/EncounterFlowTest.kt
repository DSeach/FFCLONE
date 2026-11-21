package com.game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ffclone.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncounterFlowTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun encounterFromStartup_returnAndFightRemainingEnemy() {
        repeat(12) { composeTestRule.onNodeWithText("\u2192").performClick() }
        repeat(2) { composeTestRule.onNodeWithText("\u2193").performClick() }
        composeTestRule.onNodeWithText("BATTLE").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("BATTLE").assertDoesNotExist()

        composeTestRule.onNodeWithText("\u2192").performClick()
        composeTestRule.onNodeWithText("BATTLE").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("BATTLE").assertDoesNotExist()

        composeTestRule.onNodeWithText("\u2190").performClick()
        composeTestRule.onNodeWithText("BATTLE").assertDoesNotExist()
        composeTestRule.onNodeWithText("\u2192").assertIsDisplayed()
    }
}
