package com.raazi.cddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.raazi.cddemo.destinations.ProfileViewDestination
import com.raazi.cddemo.ui.theme.CDDemoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CDDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                            DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeView(navigator: DestinationsNavigator){

    Column(modifier  = Modifier
        .fillMaxSize()
        .padding(18.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the main screen, Pass info from here")
        var text = remember{
            mutableStateOf("")
        }
        OutlinedTextField(value = text.value, label = { Text(text = "Enter your data to be passed")}, onValueChange = {text.value = it})
        Button(onClick = {navigator.navigate(ProfileViewDestination(text.value))}) {
            Text(text ="Goto Next Screen" )
        }
    }
}
@Destination
@Composable
fun ProfileView(data:String, navigator: DestinationsNavigator){
    Column(modifier  = Modifier
        .fillMaxSize()
        .padding(18.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text("The data passed is $data")

        Button(onClick = { navigator.popBackStack() }) {
            Text(text ="Goto Home Screen" )
        }

    }
}

@Destination
@Composable
fun ReturnBoolean(resultBackNavigator: ResultBackNavigator<Boolean>){
    Dialog(onDismissRequest = { resultBackNavigator.navigateBack(result = false)}, ) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(18.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Button(onClick = { resultBackNavigator.navigateBack(result = true) }) {
                Text(text = "True")
            }
            Button(onClick = { resultBackNavigator.navigateBack(result = false) }) {
                Text(text = "False")
            }
        }
    }
}