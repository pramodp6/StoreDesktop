package com.pramod.store

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.Stage

class RegisterController {

    @FXML private lateinit var nameField: TextField
    @FXML private lateinit var emailField: TextField
    @FXML private lateinit var mobileField: TextField
    @FXML private lateinit var addressField: TextArea

    @FXML private lateinit var usernameField: TextField
    @FXML private lateinit var passwordField: PasswordField
    @FXML private lateinit var confirmPasswordField: PasswordField

    @FXML
    fun onRegisterClicked() {
        val name = nameField.text.trim()
        val email = emailField.text.trim()
        val mobile = mobileField.text.trim()
        val address = addressField.text.trim()
        val username = usernameField.text.trim()
        val password = passwordField.text.trim()
        val confirmPassword = confirmPasswordField.text.trim()

        val stage = nameField.scene.window as Stage  // For Toast owner
        println("DEBUG -> Name: '$name'")
        // 🔴 Check Empty Fields
        if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || address.isEmpty() ||
            username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.show(stage, ToastType.ERROR, 2500, "All fields are required ")
            return
        }

        // 🔴 Email Validation
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!email.matches(emailRegex)) {
            Toast.show(stage, ToastType.ERROR, 2500, "Invalid Email Address ❌")
            return
        }

        // 🔴 Mobile Validation (10 digit only, starting with 6-9)
        if (!mobile.matches("^[6-9][0-9]{9}$".toRegex())) {
            Toast.show(stage, ToastType.ERROR, 2500, "Invalid Mobile Number ❌")
            return
        }

        // 🔴 Username length check
        if (username.length < 4) {
            Toast.show(stage, ToastType.ERROR, 2500, "Username must be at least 4 characters ❌")
            return
        }

        // 🔴 Password Strength Check
        val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#\$%^&+=!]).{6,}$".toRegex()
        if (!password.matches(passwordRegex)) {
            Toast.show(
                stage,
                ToastType.ERROR,
                4000,
                "Password must contain:\n• At least 6 characters\n• One uppercase letter\n• One lowercase letter\n• One number\n• One special character (@#\$%^&+=!) ❌"
            )
            return
        }

        // 🔴 Password Match Check
        if (password != confirmPassword) {
            Toast.show(stage, ToastType.ERROR, 2500, "Passwords do not match ❌")
            return
        }

        // ✅ SUCCESS
        Toast.show(stage, ToastType.SUCCESS, 2500, "User $name registered successfully ✅")
    }
}
