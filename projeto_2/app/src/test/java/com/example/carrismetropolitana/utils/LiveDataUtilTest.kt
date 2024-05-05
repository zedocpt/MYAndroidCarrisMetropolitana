/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.carrismetropolitana.utils

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Gets the value of a [LiveData] or waits for it to have one, with a timeout.
 *
 * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
 * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
 */
@ExperimentalCoroutinesApi
fun <T> LiveData<T>.getOrAwaitValueTest(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValueTest.removeObserver(this)
        }
    }
    observeForever(observer)
    afterObserve.invoke()
    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }
    return data
}

@ExperimentalCoroutinesApi
fun runBlockingTest(block: suspend () -> Unit) {
    val testCoroutineDispatcher = TestCoroutineDispatcher()
    Dispatchers.setMain(testCoroutineDispatcher)
    try {
        runBlocking { block() }
    } finally {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}