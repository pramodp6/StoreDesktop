package com.pramod.store

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File

class UserProfileController {

    @FXML
    private lateinit var profileImageView: ImageView

    @FXML
    private lateinit var nameField: TextField

    @FXML
    private lateinit var emailField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var mobileField: TextField

    @FXML
    private lateinit var addressField: TextArea

    // Called when user clicks "Change Photo"
    @FXML
    fun onChangePhoto() {
        val fileChooser = FileChooser()
        fileChooser.title = "Select Profile Photo"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"))

        val stage = profileImageView.scene.window as Stage
        val selectedFile: File? = fileChooser.showOpenDialog(stage)

        selectedFile?.let {
            val image = Image(it.toURI().toString())
            profileImageView.image = image
        }
    }

    // Called when user clicks "Update Profile"
    @FXML
    fun onUpdateProfile() {
        val name = nameField.text
        val email = emailField.text
        val password = passwordField.text
        val mobile = mobileField.text
        val address = addressField.text
        val stage = addressField.scene.window as Stage
        // Here you can save this data to database or file
        println("Updated Profile: $name, $email, $mobile, $address")

        // Show success alert
        Toast.show(stage, ToastType.SUCCESS, 2500, "User $name registered successfully ✅")    }

    // Optionally, a method to load existing user data
    fun loadUserData(name: String, email: String, mobile: String, address: String, profilePhoto: Image?) {
        nameField.text = name
        emailField.text = email
        mobileField.text = mobile
        addressField.text = address
        profilePhoto?.let { profileImageView.image = it }
    }
}
