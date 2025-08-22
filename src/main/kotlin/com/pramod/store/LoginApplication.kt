package com.pramod.store

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class LoginApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(LoginApplication::class.java.getResource("/com/pramod/store/login-view.fxml"))

        val bgImage = Image(javaClass.getResourceAsStream("/com/pramod/store/values/images/appican.png"))



        val scene = Scene(fxmlLoader.load(), 600.0, 600.0)
        stage.title = "Login"
        stage.icons.add(Image(LoginApplication::class.java.getResourceAsStream("/com/pramod/store/values/images/appican.png")))

        stage.scene = scene
        stage.show()
    }
}


