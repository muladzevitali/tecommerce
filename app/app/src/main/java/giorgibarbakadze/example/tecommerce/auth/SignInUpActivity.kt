package giorgibarbakadze.example.tecommerce.auth

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import giorgibarbakadze.example.tecommerce.R
import giorgibarbakadze.example.tecommerce.databinding.ActivitySignInUpBinding
import giorgibarbakadze.example.tecommerce.utils.extensions.addFragment
import giorgibarbakadze.example.tecommerce.utils.extensions.replaceFragment

class SignInUpActivity : AppCompatActivity() {
    private val mSignInFragment: SignInFragment = SignInFragment()
    private val mSignUpFragment: SignUpFragment = SignUpFragment()
    private lateinit var binding: ActivitySignInUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        addFragment(mSignInFragment, R.id.fragmentContainerView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                replaceFragment(mSignInFragment, R.id.fragmentContainerView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        replaceFragment(mSignInFragment, R.id.fragmentContainerView)
    }

    fun loadSignUpFragment() {
        if (mSignInFragment.isAdded) {
            replaceFragment(mSignUpFragment, R.id.fragmentContainerView)
        } else {
            addFragment(mSignUpFragment, R.id.fragmentContainerView)
        }
    }
}