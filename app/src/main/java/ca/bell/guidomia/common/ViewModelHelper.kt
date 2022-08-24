package ca.bell.guidomia.common

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Simplify view model factories creation inside activities
 * The same can be done for Fragments
 */
inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}
