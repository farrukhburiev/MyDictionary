package farrukh.mydictionary.screens.add_edit

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import farrukh.mydictionary.ui.theme.mainGreen
import farrukh.mydictionary.ui.theme.secondaryGreen
import uz.ictschool.mywords.localDatabse.entity.Word
import uz.sardor.noteapp.navigation.Screens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddEditView(navController: NavController, addEditViewModel: AddEditViewModel) {

    Log.d("TAG", "AddEditView:${addEditViewModel.id}")
    val name = addEditViewModel.name.observeAsState().value!!
    val translate = addEditViewModel.translation.observeAsState().value!!
    val defination = addEditViewModel.defination.observeAsState().value!!


    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
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
                        Text(text = addEditViewModel.getTopAppBarText(), Modifier.padding(5.dp))
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
        ) {

            Text(
                text = "Add as many words as you can.\n It's totally free",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp),
            )

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = secondaryGreen,
                    focusedBorderColor = secondaryGreen,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.White
                ),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                value = name,
                label = { Text("Word") },
                onValueChange = { addEditViewModel.updateWord(it) },
            )

            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = secondaryGreen,
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                value = translate,
                label = { Text("Uzbek meaning") },
                onValueChange = { addEditViewModel.updateTranslate(it) },
            )

            OutlinedTextField(
                modifier = Modifier
                    .height(125.dp)
                    .padding(12.dp)
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = secondaryGreen,
                    unfocusedLabelColor = Color.Gray
                ),

                value = defination,
                label = { Text("English defination") },
                onValueChange = { addEditViewModel.updateDefination(it) },
            )

            Spacer(modifier = Modifier.height(100.dp))

            TextButton(
                enabled = addEditViewModel.enabledDisabled(), // hali ozgartiramiz
                onClick = {
                    val word = Word(
                        id = addEditViewModel.id,
                        name = name,
                        translation = translate,
                        defination = defination
                    )
                    if (addEditViewModel.id == -1){
                        addEditViewModel.addEditModel.addWord(Word(
                            name = name,
                            translation = translate,
                            defination = defination
                        ))
                    }
                    else{addEditViewModel.addEditModel.editWord(word)}
                    navController.navigate(Screens.HomeScreen.route)

                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = mainGreen,
                    disabledContainerColor = Color.Gray
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = addEditViewModel.getButtonText(), color = Color.White, fontSize = 17.sp)

            }
        }

    }
}