package com.pramod.store

import com.pramod.store.api.ApiClient.apiService
import com.pramod.store.model.LoginRequest
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.PasswordField
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.TextField
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginController {

    @FXML
    private lateinit var usernameField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var progressIndicator: ProgressIndicator

    @FXML
    fun onLoginClicked() {

        loginUser()
    }

    private fun loginUser() {
        val username = usernameField.text
        val password = passwordField.text


        val stage = usernameField.scene.window as Stage
        // 🔴 Validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.show(stage, ToastType.ERROR, 2000, "Username Required")
            return
        }

        if (password.length < 4) {
            Toast.show(stage, ToastType.ERROR, 2000, "Password must be at least 4 characters")
            return
        }


        progressIndicator.isVisible = true


        CoroutineScope(Dispatchers.IO).launch {

            val response = apiService.login(LoginRequest(username, password))
            print(response)
            try {
                withContext(Dispatchers.Main) {
                    progressIndicator.isVisible = false
                    if (response.isSuccessful) {
                        Toast.show(stage, ToastType.ERROR, 2000, "Sucess")
                        openNewWindow("/com/pramod/store/dashboard.fxml", "Dashboard")
                        stage.close()

                    } else {
                        Toast.show(stage, ToastType.ERROR, 2000, "User & Pssword Incorrect")
                    }
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                  //  print(e)
                    progressIndicator.isVisible = false
                    Toast.show(stage, ToastType.ERROR, 2000, e.localizedMessage ?: "Unknown error")                }
            }

            /* if (username == "admin" && password == "1234") {

            Toast.show(stage, ToastType.SUCCESS, 2000, "Login successful")


            openNewWindow("/com/pramod/store/dashboard.fxml", "Dashboard")

            // Close login window
            stage.close()
        } else {

            Toast.show(stage, ToastType.ERROR, 2000, "Invalid username or password ")
        }*/
        }
    }
        @FXML
        fun onRegisterClicked() {
            openNewWindow("/com/pramod/store/register_view.fxml", "Register")
        }

        @FXML
        fun onForgotPasswordClicked() {
            openNewWindow("/com/pramod/store/forgot_password.fxml", "Forgot")
        }

        private fun openNewWindow(fxmlFile: String, title: String) {
            try {
                val loader = FXMLLoader(javaClass.getResource(fxmlFile))
                val root: Parent = loader.load()
                val stage = Stage()
                stage.title = title
                stage.scene = Scene(root)
                stage.show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.show(usernameField.scene.window as Stage, ToastType.ERROR, 2000, "Unable to open $title screen")
            }
        }
    }
