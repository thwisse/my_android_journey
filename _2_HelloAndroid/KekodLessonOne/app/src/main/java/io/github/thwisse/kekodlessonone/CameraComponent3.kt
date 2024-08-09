package io.github.thwisse.kekodlessonone

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class CameraComponent3 : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> startCamera()
            Lifecycle.Event.ON_PAUSE -> stopCamera()
            Lifecycle.Event.ON_CREATE -> TODO()
            Lifecycle.Event.ON_START -> TODO()
            Lifecycle.Event.ON_STOP -> TODO()
            Lifecycle.Event.ON_DESTROY -> TODO()
            Lifecycle.Event.ON_ANY -> TODO()
            // burada eklemek istemedigin eventler olursa else kismini kullan.
            // geri kalanini otomatik ekledi diye ben de ekledim yoksa hepsini
            // yazmak zorunda degilsin.
        }
    }

    fun startCamera() {
        Log.e("CameraComponent3", "startCamera")
    }

    fun stopCamera() {
        Log.e("CameraComponent3", "stopCamera")
    }

}