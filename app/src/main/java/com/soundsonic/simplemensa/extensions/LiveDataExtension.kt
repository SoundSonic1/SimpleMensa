package com.soundsonic.simplemensa.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * https://stackoverflow.com/questions/50599830/how-to-combine-two-live-data-one-after-the-other
 */
fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {

    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}
