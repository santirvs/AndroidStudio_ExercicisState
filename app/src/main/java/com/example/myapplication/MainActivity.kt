package com.example.myapplication

import android.net.ParseException
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Calendar
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Exercici(modifier = Modifier.padding(padding), 42)
                }
            }
        }
    }
}

@Composable
fun Exercici(modifier: Modifier = Modifier, exercici: Int = 1) {

    when (exercici) {
        1 -> Exercici1(modifier)
        2 -> Exercici2(modifier)
        3 -> Exercici3(modifier)
        41 -> Exercici4()
        42 -> Exercici42()
    }
}


//region exercici1 - Càlcul de la propina

@Composable
fun Exercici1(modifier: Modifier = Modifier) {
//    Exercici 1 Calcula propina
//    Crea una app que demani el preu total d’un menú i un percentatge
//    de propina (mitjançant dos TextField diferents).
//    Quan l’usuari premi sobre un botó amb el text “Calcular” es mostrarà
//    el valor de la propina que hem de deixar, i el preu total (menú més propina).

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var preuTotal by remember { mutableStateOf("") }
        var percentatgePropina by remember { mutableStateOf("") }
        var textPreu by remember { mutableStateOf("") }

        TextField(
            value = preuTotal,
            onValueChange = { preuTotal = it },
            label = { Text(text = "Preu total") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            )
        )

        Spacer(modifier.padding(5.dp))

        TextField(
            value = percentatgePropina,
            onValueChange = { percentatgePropina = it },
            label = { Text(text = "Percentatge de propina") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            )
        )

        Spacer(modifier.padding(5.dp))

        Button(
            onClick = {
                var preuAmbPropina =
                    preuTotal.toDouble() * (1 + percentatgePropina.toDouble() / 100)
                textPreu = String.format(
                    java.util.Locale("es", "ES"),
                    "El preu total és %.2f €",
                    preuAmbPropina
                )
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

//endregion exercici_1

//region exercici2 - Càlcul IMC

fun composarMissatge(nom: String, anyNaixement: String, alcada: String, pes: String): String {
    var missatge: String = ""
    try {
        var anyActual = Calendar.getInstance().get(Calendar.YEAR);
        var edad = anyActual - Integer.parseInt(anyNaixement)
        var imc = pes.toDouble() / Math.pow(alcada.toDouble() / 100, 2.0)

        var diagnostic: String = ""
        if (imc in 0.0..<18.5) diagnostic = "Pes insuficient"
        else if (imc in 18.9..<24.9) diagnostic = "Pes normal"
        else if (imc in 24.9..<50.0) diagnostic = "Sobrepès"
        else diagnostic = "Obesitat"

        var strIMC = String.format(java.util.Locale("es", "ES"), "%.2f", imc)

        missatge =
            "Hola, $nom. Tens $edad anys. El teu IMC és $strIMC i, per tant, el teu diagnòstic és: $diagnostic"
    } catch (e: Exception) {
        missatge = "Paràmetres incorrectes!"
    }

    return missatge
}

@Composable
fun Exercici2(modifier: Modifier = Modifier) {
//    Exercici 1 Calcula propina
//    Crea una app que demani el preu total d’un menú i un percentatge
//    de propina (mitjançant dos TextField diferents).
//    Quan l’usuari premi sobre un botó amb el text “Calcular” es mostrarà
//    el valor de la propina que hem de deixar, i el preu total (menú més propina).

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var nomUsuari by remember { mutableStateOf("") }
        var anyNaixement by remember { mutableStateOf("") }
        var alcada by remember { mutableStateOf("") }
        var pes by remember { mutableStateOf("") }
        var missatge by remember { mutableStateOf("") }

        TextField(
            value = nomUsuari,
            onValueChange = { nomUsuari = it },
            label = { Text(text = "Nom usuari") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        TextField(
            value = anyNaixement,
            onValueChange = { anyNaixement = it },
            label = { Text(text = "Any Naixement") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        TextField(
            value = alcada,
            onValueChange = { alcada = it },
            label = { Text(text = "Alçada") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        TextField(
            value = pes,
            onValueChange = { pes = it },
            label = { Text(text = "Pes") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        Button(
            onClick = {
                missatge = composarMissatge(nomUsuari, anyNaixement, alcada, pes)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier.padding(2.dp))

        Text(text = missatge)

    }

}

//endregion exercici2

//region exercici3 - Endevina un número

fun composarMissatge3(numeroAEndevinar: Int, numeroIntentat: String): String {
    var missatge: String = ""
    try {
        var numIntentat: Int = Integer.parseInt(numeroIntentat)
        if (numIntentat == numeroAEndevinar)
            missatge = "Has encertat el número"
        else if (numIntentat < numeroAEndevinar)
            missatge = "El número que busques és més gran"
        else
            missatge = "El número que busques és més petit"
    } catch (e: Exception) {
        missatge = "Paràmetres incorrectes!"
    }

    return missatge
}

@Composable
fun Exercici3(modifier: Modifier = Modifier) {
//    Exercici 3 - Endevinar un número
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var numeroAEndevinar by remember { mutableIntStateOf((0..1000).random()) }
        var numeroIntentat by remember { mutableStateOf("") }
        var missatge by remember { mutableStateOf("") }
        var numIntents by remember { mutableIntStateOf(0) }

        TextField(
            value = numeroIntentat,
            onValueChange = { numeroIntentat = it },
            label = { Text(text = "Tria un número") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        Button(
            onClick = {
                missatge = composarMissatge3(numeroAEndevinar, numeroIntentat)
                numIntents++
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier.padding(2.dp))

        Text(text = missatge)
        Text(text = "Portes $numIntents intents")

    }

}

//endregion exercici3

//region exercici4 - Calculadora

fun realitzarCalcul(operand1: String, operand2: String, operador: String): Int {
    var missatge: String = ""
    var result: Int = 0
    try {
        var num1: Int = Integer.parseInt(operand1)
        var num2: Int = Integer.parseInt(operand2)

        when (operador) {
            "Sumar" -> result = num1 + num2
            "Restar" -> result = num1 - num2
            "Multiplicar" -> result = num1 * num2
            "Dividir" -> result = num1 / num2
        }
    } catch (e: ArithmeticException) {
        missatge = "No es pot dividir entre zero!"
    } catch (e: ParseException) {
        missatge = "Cal que els dos números estiguin informats i siguin vàlids"
    } catch (e: Exception) {
        missatge = "Algo malo ha pasado"
    }

    if (missatge != "") {
        /* No puc fer crides a objectes @Composable
        Column {
            val context = LocalContext.current

            Toast.makeText(context, missatge, Toast.LENGTH_SHORT).show()

         */
    }

    return result
}


@Composable
fun Exercici4(modifier: Modifier = Modifier) {
//    Exercici 4 - Calculadora
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var resultat by remember { mutableIntStateOf(0) }

        var selectedText by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
        var mostrar by remember { mutableStateOf(false) }

        TextField(

            value = num1,
            onValueChange = { num1 = it },
            label = { Text(text = "Primer operand") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text(text = "Segon operand") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Green,
                unfocusedContainerColor = Color.LightGray
            ),
            modifier = modifier.padding(2.dp)
        )

        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                operaciones.forEach { hobby ->
                    DropdownMenuItem(text = { Text(text = hobby) }, onClick = {
                        expanded = false
                        selectedText = hobby
                    })
                }
            }
        }

        Button(
            onClick = {

                //No se puede llamar a una funcion Composable
                // Y una funcion no composable no puede mostrar componentes
                // Probar con StateHoister

                resultat = realitzarCalcul(num1, num2, selectedText)
                mostrar = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier.padding(2.dp))

        if (mostrar) Text(text = "El resultat és $resultat")

    }

}

//endregion exercici4

//region exercici42 - Calculadora amb State Hoisting

data class calculadora (
    var num1 : String,
    var num2 : String,
    var resultat : Int,
    var missatge : String,
    var error: Boolean )


fun realitzarCalcul42(operand1: String, operand2: String, operador: String): Int {
    var missatge: String = ""
    var result: Int = 0
    try {
        var num1: Int = Integer.parseInt(operand1)
        var num2: Int = Integer.parseInt(operand2)

        when (operador) {
            "Sumar" -> result = num1 + num2
            "Restar" -> result = num1 - num2
            "Multiplicar" -> result = num1 * num2
            "Dividir" -> result = num1 / num2
        }
    } catch (e: ArithmeticException) {
        missatge = "No es pot dividir entre zero!"
    } catch (e: ParseException) {
        missatge = "Cal que els dos números estiguin informats i siguin vàlids"
    } catch (e: Exception) {
        missatge = "Algo malo ha pasado"
    }

    if (missatge != "") {
        /* No puc fer crides a objectes @Composable
        Column {
            val context = LocalContext.current

            Toast.makeText(context, missatge, Toast.LENGTH_SHORT).show()

         */
        throw Exception(missatge)
    }

    return result
}

@Composable
fun MyTextField(modifier: Modifier, texte: String, num: String, onChange: (valor: String) -> Unit) {
    TextField(
        value = num,
        onValueChange = { onChange(it) },
        label = { Text(text = texte) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Green,
            unfocusedContainerColor = Color.LightGray
        ),
        modifier = modifier.padding(2.dp)
    )
}

@Composable
fun MyDropDown(
    modifier: Modifier,
    selectedText: String,
    onChange: (valor: String) -> Unit,
    expanded: Boolean,
    clickable: (Boolean) -> Unit,
    valores: List<String>
) {
    Column(modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { onChange(it) },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { clickable(true) }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { clickable(false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            valores.forEach { valor ->
                DropdownMenuItem(text = { Text(text = valor) }, onClick = {
                    clickable(false)
                    onChange(valor)
                })
            }
        }
    }

}

@Composable
fun MyButtonCalcular(calcular: () -> Unit) {
    Button(
        onClick = {
            calcular()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        ),
        border = BorderStroke(5.dp, Color.Green)
    ) {
        Text(text = "Calcular")
    }
}


@Composable
fun Exercici42(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var resultat by remember { mutableIntStateOf(0) }

        var selectedText by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
        var mostrar by remember { mutableStateOf(false) }

        MyTextField(modifier, "Primer operand", num1) { num1 = it }
        MyTextField(modifier, "Segon operand", num2) { num2 = it }

        MyDropDown(
            modifier,
            selectedText,
            { selectedText = it },
            expanded,
            { expanded = it },
            operaciones
        )

        MyButtonCalcular({
            try {
                resultat = realitzarCalcul42(num1, num2, selectedText)
                mostrar = true
            }
            catch (e:Exception) {
                Column {
                    val context = LocalContext.current

                    Toast.makeText(context, missatge, Toast.LENGTH_SHORT).show()
                }
            }
        })


        Spacer(modifier.padding(2.dp))

        if (mostrar) Text(text = "El resultat és $resultat")

    }

}

//endregion exercici4


/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExercicisAmbEstat() {
    MyApplicationTheme {
        Exercici(modifier = Modifier,exercici = 1)
    }
}
*/
