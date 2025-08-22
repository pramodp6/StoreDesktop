package com.pramod.store

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.util.Duration
import javafx.application.Platform
import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

class OtpController {

    @FXML private lateinit var otp1: TextField
    @FXML private lateinit var otp2: TextField
    @FXML private lateinit var otp3: TextField
    @FXML private lateinit var otp4: TextField
    @FXML private lateinit var timerLabel: Label
    @FXML private lateinit var resendButton: Button
    @FXML private lateinit var statusLabel: Label

    private val correctOtp = PrefManager.getString("otp_code")
    private var remainingSeconds = 120 // 2 minutes
    private lateinit var timeline: Timeline

    @FXML
    fun initialize() {
        startTimer()
    }

    private fun startTimer() {
        resendButton.isVisible = false
        remainingSeconds = 120
        timeline = Timeline(KeyFrame(Duration.seconds(1.0), {
            remainingSeconds--
            val minutes = remainingSeconds / 60
            val seconds = remainingSeconds % 60
            timerLabel.text = String.format("%02d:%02d", minutes, seconds)

            if (remainingSeconds <= 0) {
                timeline.stop()
                timerLabel.text = "OTP expired"
                resendButton.isVisible = true
            }
        }))
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
    }

    @FXML
    fun onVerifyClicked() {
        if (remainingSeconds <= 0) {
            statusLabel.text = "OTP expired, please resend"
            return
        }

        val enteredOtp = otp1.text + otp2.text + otp3.text + otp4.text
        if (enteredOtp == correctOtp) {
            statusLabel.text = "OTP Verified ✅"
            // Move to password reset page
            val stage = otp1.scene.window as Stage
            val loader = FXMLLoader(javaClass.getResource("/com/pramod/store/password-change-view.fxml"))
            val scene = Scene(loader.load(), 400.0, 250.0)
            stage.scene = scene
        } else {
            statusLabel.text = "Invalid OTP ❌"
        }
    }

    @FXML
    fun onResendClicked() {
        // Normally server call for new OTP
        PrefManager.putString("otp_code", "5678") // example new OTP
        statusLabel.text = "New OTP sent ✅"
        startTimer()
    }
}
