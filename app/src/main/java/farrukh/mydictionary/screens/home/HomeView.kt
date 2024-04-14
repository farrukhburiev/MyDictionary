package farrukh.mydictionary.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import farrukh.mydictionary.R
import farrukh.mydictionary.screens.add_edit.AddEditModel
import farrukh.mydictionary.ui.theme.backCard
import farrukh.mydictionary.ui.theme.deleteRed
import farrukh.mydictionary.ui.theme.mainGreen
import farrukh.mydictionary.ui.theme.secondaryGreen
import uz.ictschool.mywords.localDatabse.entity.Word
import uz.sardor.noteapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, homeViewModel: HomeViewModel) {
    homeViewModel.loadList()
    var list = homeViewModel.list.observeAsState().value
    val name = homeViewModel.name.observeAsState().value
    val state = homeViewModel.state.observeAsState().value
    Log.d("TAG", "HomeView:${list!!.joinToString()}")
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainGreen,
                    titleContentColor = Color.White,
                ),
                title = {
                    Column(Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    homeViewModel.filterByName()
                                    homeViewModel.loadList()
                                    Log.d("TAG", "filter:${homeViewModel.filterByName().joinToString()} ")},
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Menu,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                            Text(text = "My dictionary", Modifier.padding(top = 5.dp, end = 10.dp))

                            IconButton(
                                onClick = { navController.navigate("add_screen/${-1}") },
                                modifier = Modifier.padding(end = 7.dp)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.icon_filter),
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        }


                    }
                }

            )
        },
    )
    { innerPadding ->


        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())

                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(mainGreen),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = secondaryGreen,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedPlaceholderColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    value = name!!,
                    label = { Text("Search for words") },
                    onValueChange = { homeViewModel.searchText(it) },
                )
            }



            Column {
                LazyColumn(
                    Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 6.dp)
                ) {
                    if (name!!.isNotEmpty()) {
                        items(homeViewModel.filterSearch()) {
                            WordItem(navController = navController, word = it, homeViewModel)
                        }
                    }

                   else if (state == true) {
                        items(homeViewModel.filterByName()) {
                            WordItem(navController = navController, word = it, homeViewModel)
                        }
                    }

                    else {
                        items(list!!) {
                            WordItem(navController = navController, word = it, homeViewModel)
                        }
                    }

                }
            }

        }

    }


}

@Composable
fun WordItem(navController: NavController, word: Word, homeViewModel: HomeViewModel) {
    Card(
        modifier = Modifier
            .background(backCard)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(5.dp) // CardDefaults.cardElevation is deprecated, you can directly use dp values
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backCard)
                    .padding(top = 15.dp, end = 5.dp, start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.icon_sun),
                    contentDescription = "null",
                    tint = mainGreen,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )

                Text(
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp, top = 5.dp),
                    text = word.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    IconButton(onClick = {
                        navController.navigate("add_screen/${word.id}")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            modifier = Modifier.size(32.dp),
                            tint = mainGreen,
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(onClick = {
                        homeViewModel.delete(word)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            modifier = Modifier.size(32.dp),
                            tint = deleteRed,
                            contentDescription = "Settings"
                        )
                    }
                }
            }
        }
    }
}