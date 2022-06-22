package com.jordan.jetpacknavigation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "",
            password = "123456",
            confirmedPassword = "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password return true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "James",
            password = "123456",
            confirmedPassword = "123456"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exits return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Jordan",
            password = "123456",
            confirmedPassword = "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philipp",
            "123456",
            "abcdefg"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philipp",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philipp",
            "abcdefg5",
            "abcdefg5"
        )
        assertThat(result).isFalse()
    }
}