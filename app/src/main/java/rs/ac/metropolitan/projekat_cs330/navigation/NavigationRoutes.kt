package rs.ac.metropolitan.projekat_cs330.navigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes(route = "home")
    object NewPerson : NavigationRoutes(route = "new")
    object PersonDetailScreen: NavigationRoutes(route = "detail/{elementId}")
}