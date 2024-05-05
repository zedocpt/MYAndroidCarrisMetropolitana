package com.example.carrismetropolitana.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class MockMainDispatcherRule(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
       return object : Statement() {
            override fun evaluate() {
                try {
                    Dispatchers.setMain(testDispatcher)
                    base.evaluate()
                }finally{
                    Dispatchers.resetMain()
                }
            }
        }
    }

    fun advanceUntilIdle() = testDispatcher.scheduler.advanceUntilIdle()
}