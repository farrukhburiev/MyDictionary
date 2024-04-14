package uz.sardor.noteapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import farrukh.mydictionary.screens.WordView
import farrukh.mydictionary.screens.add_edit.AddEditModel
import farrukh.mydictionary.screens.add_edit.AddEditView
import farrukh.mydictionary.screens.add_edit.AddEditViewModel
import farrukh.mydictionary.screens.home.HomeModel
import farrukh.mydictionary.screens.home.HomeView
import farrukh.mydictionary.screens.home.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            val model = HomeModel(context)
            val model2 = AddEditModel(context)
            val viewModel = HomeViewModel(model,model2)
//            HomeView(viewModel, navController)
            HomeView(navController, viewModel)
        }

        composable(Screens.AddScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
                )
            }) { entry ->
            val wordId = entry.arguments?.getInt("id")!!
            val model = AddEditModel(context)
            val addUpdateViewModel = AddEditViewModel(model,wordId)
            AddEditView(navController,addUpdateViewModel)
        }

        composable(Screens.WordScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
                )
            }) { entry ->
            val wordId = entry.arguments?.getInt("id")!!
            val model = AddEditModel(context)
            val addUpdateViewModel = AddEditViewModel(model,wordId)
            WordView(navController,addUpdateViewModel)
        }



//        composable(Screens.AddScreen.route){
//            val model = AddEditModel(context)
//            val viewModel = AddEditViewModel(model, -1)
////            AddView(viewModel = viewModel, navController = navController)
//            AddEditView(navController = navController, key = -1, addEditViewModel = viewModel)
//        }


//        composable(
//            route = Screens.AddScreen.route, arguments = listOf(navArgument("id"){
//                type = NavType.IntType }),
//            {
//            val model = AddEditModel(context)
//            val viewModel = AddEditViewModel(model, it.arguments?.getString("key")!!)
//            AddEditView(
//                navController = navController,
//                it.arguments?.getString("key")!!,
//                addEditViewModel = viewModel
//            }
//
//        }

//        composable(route = Screens.AddScreen.route,
//            arguments = listOf(navArgument("id"){
//                type = NavType.IntType
//            })
//        ){navBackStackEntry ->
//
//            val id = navBackStackEntry.arguments?.getInt("id")!!
//            val model = AddEditModel(context)
//            val viewModel = AddEditViewModel(model,id)
//            AddEditView(navController = navController, key = id, addEditViewModel = viewModel)
//        }

    }
}