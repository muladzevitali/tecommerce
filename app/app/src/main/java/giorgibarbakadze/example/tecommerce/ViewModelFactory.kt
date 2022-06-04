package giorgibarbakadze.example.tecommerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import giorgibarbakadze.example.tecommerce.auth.AuthViewModel
import giorgibarbakadze.example.tecommerce.repository.Repository


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom
                (AuthViewModel::class.java) -> AuthViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}