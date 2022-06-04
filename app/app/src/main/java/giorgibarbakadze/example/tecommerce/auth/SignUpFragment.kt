package giorgibarbakadze.example.tecommerce.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import giorgibarbakadze.example.tecommerce.BaseFragment
import giorgibarbakadze.example.tecommerce.R
import giorgibarbakadze.example.tecommerce.databinding.FragmentSignUpBinding
import giorgibarbakadze.example.tecommerce.utils.Status
import giorgibarbakadze.example.tecommerce.utils.extensions.checkIfEmpty
import giorgibarbakadze.example.tecommerce.utils.extensions.isValidEmail
import giorgibarbakadze.example.tecommerce.utils.extensions.isValidPassword

class SignUpFragment : BaseFragment<AuthViewModel, FragmentSignUpBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionbar = (activity as AppCompatActivity?)?.supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


        binding.signUpName.addTextChangedListener(textWatcher(binding.signUpName))
        binding.signUpLastName.addTextChangedListener(textWatcher(binding.signUpLastName))
        binding.signUpEmail.addTextChangedListener(textWatcher(binding.signUpEmail))
        binding.signUpPasswd.addTextChangedListener(textWatcher(binding.signUpPasswd))
        binding.signUpConfirmPasswd.addTextChangedListener(textWatcher(binding.signUpConfirmPasswd))
        binding.signUpZipCode.addTextChangedListener(textWatcher(binding.signUpZipCode))
        binding.signUpAddress.addTextChangedListener(textWatcher(binding.signUpAddress))

        binding.registerBtn.setOnClickListener { if (validate()) register() }
    }

    private fun register() {
        val email = binding.signUpEmail.text.toString().trim()
        val firstName = binding.signUpName.text.toString().trim()
        val lastName = binding.signUpLastName.text.toString().trim()
        val address = binding.signUpAddress.text.toString().trim()
        val zipCode = binding.signUpZipCode.text.toString().trim()
        val password: String = binding.signUpPasswd.text.toString().trim()

        authViewModel.register(email, firstName, lastName, 1, address, zipCode, password)
        authViewModel.status.observe(viewLifecycleOwner) {
            when (it!!) {
                Status.LOADING -> Toast.makeText(
                    requireContext(),
                    getString(R.string.sending_data),
                    Toast.LENGTH_SHORT
                ).show()
                Status.SUCCESS -> Toast.makeText(
                    requireContext(),
                    getString(R.string.registration_succeed),
                    Toast.LENGTH_SHORT
                ).show()
                Status.ERROR -> Toast.makeText(
                    requireContext(),
                    getString(R.string.registration_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    private fun validate(): Boolean {
        var isValid = true
        if (binding.signUpName.checkIfEmpty()) {
            binding.signUpNameTextInput.error = getString(R.string.empty_filed)
            isValid = false
        } else if (binding.signUpName.text?.length!! > 50) {
            isValid = false
        }

        if (binding.signUpLastName.checkIfEmpty()) {
            binding.signUpLastNameTextInput.error = getString(R.string.empty_filed)
            isValid = false
        } else if (binding.signUpLastName.text?.length!! > 50) {
            isValid = false
        }

        if (binding.signUpEmail.checkIfEmpty()) {
            binding.signUpEmailTextInput.error = getString(R.string.empty_filed)
            isValid = false
        } else if (!binding.signUpEmail.isValidEmail()) {
            binding.signUpEmailTextInput.error = getString(R.string.invalid_email)
            isValid = false
        } else if (binding.signUpEmail.text?.length!! > 254) {
            isValid = false
        }


        if (binding.signUpPasswd.checkIfEmpty()) {
            binding.signUpPasswdTextInput.error = getString(R.string.empty_filed)
            isValid = false
        } else if (!binding.signUpPasswd.isValidPassword()) {
            isValid = false
        } else if (binding.signUpPasswd.text?.length!! > 128) {
            isValid = false
        } else if (binding.signUpPasswd.text.toString() != binding.signUpConfirmPasswd.text.toString()) {
            isValid = false
        }

        if (binding.signUpZipCode.text?.length!! > 16) {
            isValid = false
        }

        if (binding.signUpAddress.text?.length!! > 128) {
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
                    R.id.sign_up_name -> {
                        if (binding.signUpName.text?.length!! > 50) {
                            binding.signUpNameTextInput.error = getString(R.string.max_length, 50)
                        } else {
                            binding.signUpNameTextInput.isErrorEnabled = false
                        }
                    }
                    R.id.sign_up_last_name -> {
                        if (binding.signUpLastName.text?.length!! > 50) {
                            binding.signUpNameTextInput.error = getString(R.string.max_length, 50)
                        } else {
                            binding.signUpLastNameTextInput.isErrorEnabled = false
                        }
                    }
                    R.id.sign_up_email -> {
                        if (binding.signUpEmail.text?.length!! > 254) {
                            binding.signUpEmailTextInput.error = getString(R.string.max_length, 254)
                        } else {
                            binding.signUpEmailTextInput.isErrorEnabled = false
                        }
                    }
                    R.id.sign_up_passwd -> {
                        if (binding.signUpPasswd.text?.isNotEmpty()!!) {
                            if (!binding.signUpPasswd.isValidPassword()) {
                                binding.signUpPasswdTextInput.error =
                                    getString(R.string.invalid_password)
                            } else if (binding.signUpPasswd.text?.length!! > 128) {
                                binding.signUpPasswdTextInput.error =
                                    getString(R.string.max_length, 128)
                            } else {
                                binding.signUpPasswdTextInput.isErrorEnabled = false
                            }
                        }
                    }
                    R.id.sign_up_confirm_passwd -> {
                        if (binding.signUpPasswd.text.toString() == binding.signUpConfirmPasswd.text.toString()) {
                            binding.signUpConfirmPasswdTextInput.isErrorEnabled = false
                        } else {
                            binding.signUpConfirmPasswdTextInput.error =
                                getString(R.string.passwords_not_match)
                        }
                    }
                    R.id.sign_up_zip_code -> {
                        if (binding.signUpZipCode.text?.length!! > 16) {
                            binding.signUpZipcodeTextInput.error =
                                getString(R.string.max_length, 16)
                        } else {
                            binding.signUpZipcodeTextInput.isErrorEnabled = false
                        }
                    }
                    R.id.sign_up_address -> {
                        if (binding.signUpAddress.text?.length!! > 128) {
                            binding.signUpAddressTextInput.error =
                                getString(R.string.max_length, 128)
                        } else {
                            binding.signUpAddressTextInput.isErrorEnabled = false
                        }
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
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }


}