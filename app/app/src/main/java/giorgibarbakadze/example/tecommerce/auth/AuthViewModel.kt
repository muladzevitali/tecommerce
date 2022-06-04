package giorgibarbakadze.example.tecommerce.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import giorgibarbakadze.example.tecommerce.model.LoginResponse
import giorgibarbakadze.example.tecommerce.model.SignInBody
import giorgibarbakadze.example.tecommerce.model.UserBody
import giorgibarbakadze.example.tecommerce.repository.Repository
import giorgibarbakadze.example.tecommerce.utils.Status
import kotlinx.coroutines.launch


class AuthViewModel(private val repository: Repository) : ViewModel() {
    private val loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    private var _status: MutableLiveData<Status> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val status: LiveData<Status>
        get() = _status

    fun logIn(email: String, password: String) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            loginResponse.value = repository.signIn(SignInBody(email, password))
            if (errorMessage.value == null) {
                _status.value = Status.SUCCESS
            } else {
                _status.value = Status.ERROR
            }
        }
    }

    fun register(
        email: String,
        firstName: String,
        lastName: String,
        city: Int,
        address: String,
        zipCode: String,
        password: String
    ) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            repository.signUp(
                UserBody(
                    email,
                    firstName,
                    lastName,
                    city,
                    address,
                    zipCode,
                    password
                )
            )
            errorMessage.value = repository.errorMessage
            if (errorMessage.value == null) {
                _status.value = Status.SUCCESS
            } else {
                _status.value = Status.ERROR
            }
        }
    }
}