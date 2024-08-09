package io.github.thwisse.kekodlessonone

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class CameraComponent : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startCamera() {
        Log.e("CameraComponent", "startCamera")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopCamera() {
        Log.e("CameraComponent", "stopCamera")
    }
}