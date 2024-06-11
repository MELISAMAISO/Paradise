package com.melisa.paradise.ui.screens.onboarding

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.melisa.paradise.data.domain.DogDatabase
import com.melisa.paradise.model.Dog
import com.melisa.paradise.ui.navigation.EdenAppNavigationRoutes
import com.melisa.paradise.ui.navigation.OnBoardingNavigationRoutes
import com.melisa.paradise.ui.screens.Adopt.AdoptyPetScreen
import com.melisa.paradise.ui.screens.SplashScreen
import com.melisa.paradise.ui.screens.homescreen.VeterinarianScreen
import com.melisa.paradise.ui.screens.view.Details
import com.melisa.paradise.ui.screens.view.Home
import com.melisa.paradise.utils.LogInViewModelFactory
import com.melisa.paradise.utils.SignUpViewModelFactory
import com.melisa.paradise.viewmodels.EdenLogInViewModel

@ExperimentalComposeUiApi
@ExperimentalPagerApi
fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavController,
    route: String,
    signUpViewModelFactory: SignUpViewModelFactory,
    logInViewModelFactory: LogInViewModelFactory
) {

    navigation(route = route, startDestination = OnBoardingNavigationRoutes.splashScreenRoute) {
        composable(OnBoardingNavigationRoutes.welcomeScreenRoute) {
            WelcomeScreen(
                onCreateAccountButtonClick = { navController.navigate(OnBoardingNavigationRoutes.signUpScreenRoute) },
                onLoginButtonClick = { navController.navigate(OnBoardingNavigationRoutes.loginScreenRoute) }
            )

        }
        composable(OnBoardingNavigationRoutes.splashScreenRoute){
            SplashScreen(navController)
        }
        composable(OnBoardingNavigationRoutes.signUpScreenRoute) {
            SignUpScreen(
                viewModel = viewModel(
                    viewModelStoreOwner = it,
                    factory = signUpViewModelFactory
                ),
                onAccountCreatedSuccessfully = { navigateToHomeScreen(navController) }
            )
        }
        composable(OnBoardingNavigationRoutes.loginScreenRoute) {
            LoginScreen(
                viewModel = viewModel<EdenLogInViewModel>(
                    viewModelStoreOwner = it,
                    factory = logInViewModelFactory
                ),
                onSuccessfulAuthentication = { navigateToHomeScreen(navController) }
            )
        }
        composable(EdenAppNavigationRoutes.veterinarianScreenRoute){
            VeterinarianScreen(navController)
        }
        composable(EdenAppNavigationRoutes.AdoptyPetScreenRoute){
            AdoptyPetScreen(navController)
        }
        composable(EdenAppNavigationRoutes.HomeScreenRoute){
            Home(navController, DogDatabase.dogList, toggleTheme ={} )

        }
        composable(EdenAppNavigationRoutes.DetailScreenRoute){
            Details(navController,id)
        }

    }
}


private fun navigateToHomeScreen(navController: NavController) {
    navController.navigate(EdenAppNavigationRoutes.homeScreenRoute) {
        popUpTo(OnBoardingNavigationRoutes.welcomeScreenRoute) { inclusive = true }
    }
}