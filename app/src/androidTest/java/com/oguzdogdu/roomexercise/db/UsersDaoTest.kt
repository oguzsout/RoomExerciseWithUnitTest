package com.oguzdogdu.roomexercise.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.oguzdogdu.roomexercise.data.local.UserDao
import com.oguzdogdu.roomexercise.data.local.UserDatabase
import com.oguzdogdu.roomexercise.domain.model.Users
import com.oguzdogdu.roomexercise.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class UsersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: UserDatabase

    private lateinit var dao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertUserTesting() = runBlockingTest {
        val user = Users("Ali", "Dogdu", 24, 1)
        dao.addUser(user)
        val list = dao.readAllData().getOrAwaitValue()
        assertThat(list).contains(user)

    }
}

