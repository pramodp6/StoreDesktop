package com.pramod.store

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.stage.Stage

class RegisterController {

    @FXML private lateinit var nameField: TextField
    @FXML private lateinit var emailField: TextField
    @FXML private lateinit var usernameField: TextField
    @FXML private lateinit var passwordField: PasswordField
    @FXML private lateinit var confirmPasswordField: PasswordField
    @FXML private lateinit var mobileField: TextField
    @FXML private lateinit var addressField: TextArea

    @FXML private lateinit var countryCombo: ComboBox<String>
    @FXML private lateinit var stateCombo: ComboBox<String>
    @FXML private lateinit var cityCombo: ComboBox<String>

    @FXML private lateinit var statusLabel: Label

    @FXML
    fun initialize() {
        // Sample data (replace with REST API)
        val countries = listOf("India", "USA")
        val indiaStates = listOf("Uttar Pradesh", "Maharashtra")
        val usaStates = listOf("California", "Texas")
        val indiaCities = mapOf(
            "Uttar Pradesh" to listOf("Lucknow", "Kanpur"),
            "Maharashtra" to listOf("Mumbai", "Pune")
        )
        val usaCities = mapOf(
            "California" to listOf("Los Angeles", "San Francisco"),
            "Texas" to listOf("Dallas", "Houston")
        )

        countryCombo.items.addAll(countries)

        countryCombo.setOnAction {
            val selectedCountry = countryCombo.value
            stateCombo.items.clear()
            cityCombo.items.clear()

            when (selectedCountry) {
                "India" -> stateCombo.items.addAll(indiaStates)
                "USA" -> stateCombo.items.addAll(usaStates)
            }
        }

        stateCombo.setOnAction {
            val selectedCountry = countryCombo.value
            val selectedState = stateCombo.value
            cityCombo.items.clear()

            if(selectedCountry == "India") indiaCities[selectedState]?.let { cityCombo.items.addAll(it) }
            else if(selectedCountry == "USA") usaCities[selectedState]?.let { cityCombo.items.addAll(it) }
        }
    }

    @FXML
    fun onRegisterClicked() {
        if (nameField.text.isEmpty() || emailField.text.isEmpty() || usernameField.text.isEmpty() ||
            passwordField.text.isEmpty() || confirmPasswordField.text.isEmpty() ||
            mobileField.text.isEmpty() || addressField.text.isEmpty()
        ) {
            showStatus("Please fill all fields")
            return
        }

        if (passwordField.text != confirmPasswordField.text) {
            showStatus("Passwords do not match")
            return
        }

        if (countryCombo.value.isNullOrEmpty()) { showStatus("Please select a country"); return }
        if (stateCombo.value.isNullOrEmpty()) { showStatus("Please select a state"); return }
        if (cityCombo.value.isNullOrEmpty()) { showStatus("Please select a city"); return }

        showStatus("Registration Successful ✅")
        // TODO: Call API or store locally
    }

    @FXML
    fun onBackClicked() {
        val stage = nameField.scene.window as Stage
        val loader = FXMLLoader(javaClass.getResource("/com/pramod/store/login-view.fxml"))
        val scene = Scene(loader.load(), 500.0, 500.0)
        stage.scene = scene
    }

    private fun showStatus(message: String) {
        statusLabel.text = message
    }
}
