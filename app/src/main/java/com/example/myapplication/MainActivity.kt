package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Exercici( modifier = Modifier.padding(padding), 1)
                }
            }
        }
    }
}

@Composable
fun Exercici( modifier: Modifier = Modifier, exercici:Int = 1) {

    when (exercici) {
        1 -> Exercici1(modifier)
    }
}


@Composable
fun Exercici1(modifier: Modifier = Modifier) {
//    Exercici 1 Calcula propina
//    Crea una app que demani el preu total d’un menú i un percentatge
//    de propina (mitjançant dos TextField diferents).
//    Quan l’usuari premi sobre un botó amb el text “Calcular” es mostrarà
//    el valor de la propina que hem de deixar, i el preu total (menú més propina).

    Column(modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        var preuTotal by remember { mutableStateOf("") }
        var percentatgePropina by remember { mutableStateOf("") }
        var textPreu by remember { mutableStateOf( "" )}

        TextField(
            value = preuTotal,
            onValueChange = { preuTotal = it },
            label = { Text(text = "Preu total") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            )
        )

        Spacer(modifier.padding( 5.dp))

        TextField(
            value = percentatgePropina,
            onValueChange = { percentatgePropina = it },
            label = { Text(text = "Percentatge de propina")} ,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            )
        )

        Spacer(modifier.padding( 5.dp))

        Button(
            onClick = {
                    var preuAmbPropina = preuTotal.toDouble() * (1 + percentatgePropina.toDouble()/100)
                    textPreu = String.format(java.util.Locale("es", "ES"),"El preu total és %.2f €", preuAmbPropina)
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier.padding(5.dp))

        Text(text = textPreu)

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExercicisAmbEstat() {
    MyApplicationTheme {
        Exercici(modifier = Modifier,exercici = 1)
    }
}