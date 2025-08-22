package com.pramod.store

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

class DashboardController {

    @FXML
    private lateinit var logoutButton: Button

    @FXML
    fun onLogoutClicked() {
        try {
            // 1️⃣ Current Stage बंद करो
            val currentStage = logoutButton.scene.window as Stage
            currentStage.close()

            // 2️⃣ Login window खोलो
            val loader = FXMLLoader(javaClass.getResource("/com/pramod/store/login-view.fxml"))
            val root: Parent = loader.load()
            val loginStage = Stage()
            loginStage.title = "Login"
            loginStage.scene = Scene(root)
            loginStage.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun onProfileClicked() {
        // User Profile open करने का code
        try {
            val loader = FXMLLoader(javaClass.getResource("/com/pramod/store/values/user_profile.fxml"))
            val root: Parent = loader.load()
            val profileStage = Stage()
            profileStage.title = "User Profile"
            profileStage.scene = Scene(root)
            profileStage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
