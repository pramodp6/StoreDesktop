package com.pramod.store

import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.stage.Stage

class AccountProfileController {

    @FXML private lateinit var nameField: TextField
    @FXML private lateinit var emailField: TextField
    @FXML private lateinit var mobileField: TextField

    @FXML
    fun onUpdateProfile() {
        val name = nameField.text.trim()
        val email = emailField.text.trim()
        val mobile = mobileField.text.trim()

        if (name.isEmpty() || email.isEmpty() || mobile.isEmpty()) {
            Toast.show(nameField.scene.window as Stage, ToastType.ERROR, 2500, "All fields are required ❌")
            return
        }

        // Simple validation
        if (!email.contains("@") || mobile.length != 10) {
            Toast.show(nameField.scene.window as Stage, ToastType.ERROR, 2500, "Invalid email or mobile ❌")
            return
        }

        Toast.show(nameField.scene.window as Stage, ToastType.SUCCESS, 2500, "Profile updated successfully ✅")
    }
}
