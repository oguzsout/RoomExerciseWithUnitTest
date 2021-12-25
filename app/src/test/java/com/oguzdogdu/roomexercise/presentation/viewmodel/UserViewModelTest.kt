package com.oguzdogdu.roomexercise.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.oguzdogdu.roomexercise.MainCoroutineRules
import com.oguzdogdu.roomexercise.getOrAwaitValueTest
import com.oguzdogdu.roomexercise.repo.FakeUserRepository
import com.oguzdogdu.roomexercise.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

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

        val value = viewModel.insertUserMessage.getOrAwaitValueTest()

        Truth.assertThat(value.status).isEqualTo(Status.ERROR)
    }
}