package farrukh.mydictionary.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import farrukh.mydictionary.R
import farrukh.mydictionary.screens.add_edit.AddEditViewModel
import farrukh.mydictionary.screens.home.WordItem
import farrukh.mydictionary.ui.theme.backCard
import farrukh.mydictionary.ui.theme.mainGreen
import farrukh.mydictionary.ui.theme.secondaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordView(navController: NavController,addEditViewModel:AddEditViewModel) {

    val name = addEditViewModel.name.observeAsState().value!!
    val translate = addEditViewModel.translation.observeAsState().value!!
    val defination = addEditViewModel.defination.observeAsState().value!!


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainGreen,
                    titleContentColor = Color.White,
                ),
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            Modifier.padding(end = 80.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Text(text = "My word", Modifier.padding(5.dp))
                    }
                }

            )
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { presses++ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }
//        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(
                    text = name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 20.dp),
                )

                Spacer(modifier = Modifier.height(60.dp))

                Box(modifier = Modifier
                    .background(backCard).clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth()) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "Meaning",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = mainGreen,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp),
                        )

                        Text(
                            text = translate,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp),
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(30.dp))

                Box(modifier = Modifier
                    .background(backCard).clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth()) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "Defination",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = mainGreen,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp),
                        )

                        Text(
                            text = defination,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 20.dp),
                        )
                    }
                }

            }
        }


    }


}
