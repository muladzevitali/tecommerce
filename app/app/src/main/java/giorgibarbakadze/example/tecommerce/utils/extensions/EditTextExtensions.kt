package giorgibarbakadze.example.tecommerce.utils.extensions

import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern


private val PASSWORD_PATTERN: Pattern =
    Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=\\S+$)" +           //no white spaces
                ".{7,}" +               //at least 7 characters
                "$"
    );

fun EditText.textToString(): String {
    return this.text.toString()
}

fun TextInputEditText.checkIfEmpty(): Boolean {
    return text == null || "" == textToString() || text.toString().equals("null", ignoreCase = true)
}

fun TextInputEditText.isValidEmail(): Boolean {
    val text = text as CharSequence
    return Patterns.EMAIL_ADDRESS.matcher(text).matches()
}


fun TextInputEditText.isValidPassword(): Boolean {
    val text = text as CharSequence
    return  PASSWORD_PATTERN.matcher(text).matches()
}

fun View.showSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}