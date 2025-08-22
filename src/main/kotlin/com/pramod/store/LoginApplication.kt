package com.pramod.store

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class LoginApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(LoginApplication::class.java.getResource("/com/pramod/store/login-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 500.0, 500.0)
        stage.title = "Login"
        stage.scene = scene
        stage.show()
    }
}


