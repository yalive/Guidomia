package ca.bell.guidomia.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import ca.bell.guidomia.common.event.Event
import ca.bell.guidomia.common.event.EventObserver

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    val owner = if (this is Fragment) viewLifecycleOwner else this
    liveData.observe(owner) { it?.let { t -> body(t) } }
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, body: (T) -> Unit = {}) {
    val owner = if (this is Fragment) viewLifecycleOwner else this
    liveData.observe(owner, EventObserver { it?.let { t -> body(t) } })
}
