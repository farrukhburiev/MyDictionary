package uz.sardor.noteapp.navigation

import androidx.compose.material3.Text


sealed class Screens (val route: String) {
    object HomeScreen: Screens("home_screen")

    object AddScreen: Screens("add_screen/{id}")
    object WordScreen: Screens("word_screen/{id}")

}