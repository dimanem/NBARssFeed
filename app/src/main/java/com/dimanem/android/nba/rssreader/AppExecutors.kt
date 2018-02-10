package com.dimanem.android.nba.rssreader

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by dimanemets on 10/02/2018.
 */
class AppExecutors (val diskIO: Executor = Executors.newSingleThreadExecutor(),
                    val networkIO: Executor = Executors.newFixedThreadPool(3),
                    val mainThread: Executor = MainThreadExecutor()){

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
