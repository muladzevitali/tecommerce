package giorgibarbakadze.example.tecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import giorgibarbakadze.example.tecommerce.api.RetrofitClient
import giorgibarbakadze.example.tecommerce.repository.Repository

abstract class BaseFragment<VM : ViewModel, B : ViewBinding>() : Fragment() {

    protected lateinit var binding: B
    protected lateinit var authViewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getViewBinding(inflater, container)

        val factory = ViewModelFactory(Repository(RetrofitClient.apiInterface))
        authViewModel = ViewModelProvider(this, factory).get(getViewModel())
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): B


}