package com.thefork.challenge.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.thefork.challenge.api.Page
import com.thefork.challenge.api.UserPreview
import com.thefork.challenge.api.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchPresenterTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutinesApi = TestCoroutineRule()

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var view: SearchUsersContract.View
    private lateinit var presenter: SearchUsersContract.Presenter

    @Mock
    private lateinit var uiHandler: SearchUsersContract.View.UIEvenHandler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, userService)
    }

    @Test
    fun `WHEN init SHOULD execute get users api call successfully`() {
        testCoroutinesApi.runBlockingTest {
            // GIVEN
            given(userService.getUsers(1u)).willReturn(
                Response.success(Page(emptyList(), 10u))
            )

            // WHEN
            presenter.init()

            // THEN
            verify(view).displayUsers(emptyList())
        }
    }

    @Test
    fun `WHEN init SHOULD execute get users api call with error response`() {
        testCoroutinesApi.runBlockingTest {
            // GIVEN
            given(userService.getUsers(1u)).willReturn(
                Response.error(
                    502,
                    ResponseBody.create(null, "")
                )
            )

            // WHEN
            presenter.init()

            // THEN
            verify(view).displayError()
        }
    }

    @Test
    fun `onUserClicked SHOULD execute fetch user detail api call successfully`() {
        testCoroutinesApi.runBlockingTest {
            // GIVEN
            given(userService.fetchUserDetail(ID)).willReturn(
                Response.success(userResponse)
            )

            // WHEN
            uiHandler.onUserClicked(ID)

            // THEN
            verify(view).navigateToUser(userResponse)
        }
    }

    private companion object {
        const val ID = "user_id"
        val userResponse = UserPreview("", "", "", "", "", "", "")
    }

}

@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    override fun apply(base: Statement, description: Description?) = object : Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
            testCoroutineScope.cleanupTestCoroutines()
        }

    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest {
            block()
        }
}
