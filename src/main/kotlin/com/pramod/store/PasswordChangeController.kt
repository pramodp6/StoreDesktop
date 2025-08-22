package com.pramod.store



import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.PasswordField

class PasswordChangeController {

    @FXML private lateinit var newPasswordField: PasswordField
    @FXML private lateinit var confirmPasswordField: PasswordField
    @FXML private lateinit var statusLabel: Label

    @FXML
    fun onUpdatePasswordClicked() {
        val newPassword = newPasswordField.text
        val confirmPassword = confirmPasswordField.text

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            statusLabel.text = "Please fill all fields"
            return
        }

        if (newPassword != confirmPassword) {
            statusLabel.text = "Passwords do not match"
            return
        }

        // Normally API call to update password on server
        PrefManager.putString("user_password", newPassword) // local simulation

        statusLabel.text = "Password changed successfully ✅"
    }
}
