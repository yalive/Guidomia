package ca.bell.guidomia.common.event

import androidx.lifecycle.MutableLiveData


/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * Examples:
 *    -> Show error/toast message
 *    -> In general, for any event/effect that should be consumed only once
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * Shortcut to convert any object to an event
 */
fun <T> T.asEvent(): Event<T> {
    return Event(this)
}


fun MutableLiveData<Event<Unit>>.trigger() {
    postValue(Unit.asEvent())
}

fun <T> MutableLiveData<Event<T>>.trigger(data: T) {
    postValue(data.asEvent())
}