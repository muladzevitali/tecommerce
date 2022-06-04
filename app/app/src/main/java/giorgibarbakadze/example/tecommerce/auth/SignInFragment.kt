package giorgibarbakadze.example.tecommerce.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import giorgibarbakadze.example.tecommerce.R
import giorgibarbakadze.example.tecommerce.databinding.FragmentSingInBinding
import giorgibarbakadze.example.tecommerce.BaseFragment
import giorgibarbakadze.example.tecommerce.utils.Status
import giorgibarbakadze.example.tecommerce.utils.extensions.*


class SignInFragment : BaseFragment<AuthViewModel, FragmentSingInBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signInEmail.addTextChangedListener(textWatcher(binding.signInEmail))
        binding.signInPassword.addTextChangedListener(textWatcher(binding.signInPassword))
        binding.signUpBtn.setOnClickListener {
            (activity as SignInUpActivity).loadSignUpFragment()
        }
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        binding.signInBtn.setOnClickListener { if (validate()) logIn() }


    }


    private fun logIn() {
        val username = binding.signInEmail.text.toString().trim()
        val password = binding.signInPassword.text.toString().trim()


        authViewModel.logIn(username, password)
        authViewModel.status.observe(viewLifecycleOwner) {
            when (it!!) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.signInBtn.isEnabled = false
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.signInBtn.isEnabled = true
                }
                Status.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        authViewModel.errorMessage.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.signInBtn.isEnabled = true
                }
            }
        }
    }


    private fun validate(): Boolean {
        var isValid = true

        if (binding.signInEmail.checkIfEmpty()) {
            binding.signInEmailTextInput.error = getString(R.string.empty_filed)
            isValid = false
        } else if (!binding.signInEmail.isValidEmail()) {
            binding.signInEmailTextInput.error = getString(R.string.invalid_email)
        }
        if (binding.signInPassword.checkIfEmpty()) {
            binding.signInPasswordTextInput.error = getString(R.string.empty_filed)
            isValid = false
        }


        return isValid
    }

    private fun textWatcher(view: View): TextWatcher {
        val textWatcher = (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                when (view.id) {
                    R.id.sign_in_email -> {
                        binding.signInEmailTextInput.isErrorEnabled = false
                    }
                    R.id.sign_in_password -> {
                        binding.signInPasswordTextInput.isErrorEnabled = false
                    }
                }

            }

        })
        return textWatcher
    }


    override fun getViewModel(): Class<AuthViewModel> {
        return AuthViewModel::class.java
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingInBinding {
        return FragmentSingInBinding.inflate(inflater, container, false)
    }

}