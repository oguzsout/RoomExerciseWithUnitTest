package com.oguzdogdu.roomexercise.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.oguzdogdu.roomexercise.MainCoroutineRules
import com.oguzdogdu.roomexercise.getOrAwaitValueTest
import com.oguzdogdu.roomexercise.repo.FakeUserRepository
import com.oguzdogdu.roomexercise.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRules()

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        viewModel = UserViewModel(FakeUserRepository())
    }

    @Test
    fun `insert user without age returns error`() {
        viewModel.makeUser("Oguz", "Dogdu", "")

        val value = viewModel.insertUsersMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert user without name returns error`() {
        viewModel.makeUser("", "Dogdu", "24")

        val value = viewModel.insertUsersMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert user without lastName returns error`() {
        viewModel.makeUser("Oguz", "", "24")

        val value = viewModel.insertUsersMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}