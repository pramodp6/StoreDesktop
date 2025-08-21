package com.pramod.store

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Popup
import javafx.stage.Stage
import javafx.util.Duration

enum class ToastType {
    SUCCESS, ERROR, INFO
}

object Toast {

    fun show(ownerStage: Stage, type: ToastType = ToastType.INFO, duration: Int = 2000, vararg messages: String) {
        messages.forEach { message ->
            val popup = Popup()

            val label = Label(message)
            label.style = "-fx-background-radius: 10; -fx-padding: 10px; -fx-text-fill: white; -fx-font-size: 14px;"
            label.style += when (type) {
                ToastType.SUCCESS -> "-fx-background-color: #4CAF50;" // Green
                ToastType.ERROR -> "-fx-background-color: #F44336;"   // Red
                ToastType.INFO  -> "-fx-background-color: #2196F3;"   // Blue
            }

            val root = StackPane(label)
            root.style = "-fx-background-color: transparent;"
            root.alignment = Pos.BOTTOM_CENTER

            popup.content.add(root)
            popup.show(ownerStage)

            // Fade out animation
            val fadeTimeline = Timeline(
                KeyFrame(Duration.millis(duration.toDouble()), {
                    popup.hide()
                })
            )
            fadeTimeline.play()
        }
    }
}
