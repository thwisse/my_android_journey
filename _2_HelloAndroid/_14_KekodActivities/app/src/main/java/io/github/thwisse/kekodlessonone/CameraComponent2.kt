package io.github.thwisse.kekodlessonone

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class CameraComponent2 : DefaultLifecycleObserver {

    // lifecycle fonksiyonlari direkt override edilebiliyor.

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        startCamera()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        stopCamera()
    }

    fun startCamera() {
        Log.e("CameraComponent2", "startCamera")
    }

    fun stopCamera() {
        Log.e("CameraComponent2", "stopCamera")
    }
}