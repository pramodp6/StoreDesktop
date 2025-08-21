package com.pramod.store



import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

class ForgotController {

    @FXML
    private lateinit var emailField: TextField

    @FXML
    private lateinit var newPasswordField: PasswordField

    @FXML
    private lateinit var confirmPasswordField: PasswordField

    @FXML
    fun onResetClicked() {
        val email = emailField.text


        if (email.isBlank()) {
            showAlert("Error", "Please fill all fields")

            return
        }



        // यहाँ आप backend / database से update करा सकते हो
        showAlert("Success", "Password reset successful!")
        goToLogin()
    }

    @FXML
    fun onBackToLoginClicked() {
        goToLogin()
    }

    private fun goToLogin() {
        val stage = emailField.scene.window as Stage
        val loader = FXMLLoader(javaClass.getResource("/login.fxml"))
        val scene = Scene(loader.load())
        stage.scene = scene
    }

    private fun showAlert(title: String, message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = title
        alert.headerText = null
        alert.contentText = message
        alert.showAndWait()
    }
}
