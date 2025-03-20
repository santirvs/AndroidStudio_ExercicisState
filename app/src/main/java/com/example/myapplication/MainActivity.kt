package com.example.myapplication

import android.net.ParseException
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Exercici(modifier = Modifier.padding(padding), 7)
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
        5 -> Exercici5()
        6 -> Exercici6()
        7 -> Exercici7()
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

data class DadesCalculadora(
    var num1: String,
    var num2: String,
    var operador: String,
    var resultat: Int,
    var missatge: String,
    var error: Boolean
)


fun realitzarCalcul42(dades: DadesCalculadora) {
    dades.error = true
    try {
        var num1: Int = Integer.parseInt(dades.num1)
        var num2: Int = Integer.parseInt(dades.num2)

        when (dades.operador) {
            "Sumar" -> dades.resultat = num1 + num2
            "Restar" -> dades.resultat = num1 - num2
            "Multiplicar" -> dades.resultat = num1 * num2
            "Dividir" -> dades.resultat = num1 / num2
        }

        dades.error = false
        dades.missatge = ""
    } catch (e: ArithmeticException) {
        dades.missatge = "No es pot dividir entre zero!"
    } catch (e: ParseException) {
        dades.missatge = "Cal que els dos números estiguin informats i siguin vàlids"
    } catch (e: Exception) {
        dades.missatge = "Hem tingut un imprevist... \n ${e.stackTrace} \n ${e.message}"
    }
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
fun MyButton(titulo: String, calcular: () -> Unit) {
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
        Text(text = titulo)
    }
}


@Composable
fun Exercici42(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var dadesCalc by remember { mutableStateOf(DadesCalculadora("", "", "", 0, "", false)) }

        var expanded by remember { mutableStateOf(false) }
        val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
        var mostrar by remember { mutableStateOf(false) }

        MyTextField(modifier, "Primer operand", dadesCalc.num1) {
            dadesCalc = dadesCalc.copy(num1 = it); mostrar = false
        }
        MyTextField(modifier, "Segon operand", dadesCalc.num2) {
            dadesCalc = dadesCalc.copy(num2 = it); mostrar = false
        }

        MyDropDown(
            modifier,
            dadesCalc.operador,
            { dadesCalc.operador = it; mostrar = false },
            expanded,
            { expanded = it },
            operaciones
        )

        MyButton("Calcular") {
            realitzarCalcul42(dadesCalc)
            mostrar = true
        }

        if (mostrar) {
            if (!dadesCalc.error) {
                Spacer(modifier.padding(2.dp))
                Text(text = "El resultat és ${dadesCalc.resultat}")
            } else {
                val context = LocalContext.current
                Toast.makeText(context, dadesCalc.missatge, Toast.LENGTH_LONG).show()
            }
        }

    }

}

//endregion exercici4

//region exercici5 - Conversor d'unitats

data class DadesConversor(
    var mesura: String,
    var conversio: String,
    var resultat: Float,
    var missatge: String,
    var error: Boolean
)


// Constants de conversio
val MILE_TO_KM: Float = 1.60934f
val IN_TO_CM: Float = 2.54f
val MT_TO_YARD: Float = 1.0936133f

val llistaConversions = listOf(
    "polzada a centímetre",
    "iarda a metre",
    "milla a quilometre",
    "centimetre a polzada",
    "metre a iarda",
    "quilometre a milla"
)

fun realitzarCalcul5(dades: DadesConversor) {
    dades.error = true
    try {
        var num1: Float = dades.mesura.toFloat()

        when (llistaConversions.indexOf(dades.conversio)) {
            0 -> dades.resultat = num1 * IN_TO_CM
            1 -> dades.resultat = num1 / MT_TO_YARD
            2 -> dades.resultat = num1 * MILE_TO_KM
            3 -> dades.resultat = num1 / IN_TO_CM
            4 -> dades.resultat = num1 * MT_TO_YARD
            5 -> dades.resultat = num1 / MILE_TO_KM
        }

        dades.error = false
        dades.missatge = ""
    } catch (e: ArithmeticException) {
        dades.missatge = "No es pot dividir entre zero!"
    } catch (e: ParseException) {
        dades.missatge = "Cal que el número estiguin informat i sigui vàlids"
    } catch (e: Exception) {
        dades.missatge = "Hem tingut un imprevist... \n ${e.stackTrace} \n ${e.message}"
    }
}


@Composable
fun Exercici5(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var dadesConversio by remember { mutableStateOf(DadesConversor("", "", 0.0f, "", false)) }

        var expanded by remember { mutableStateOf(false) }
        var mostrar by remember { mutableStateOf(false) }

        MyTextField(modifier, "Unitats a convertir", dadesConversio.mesura) {
            dadesConversio = dadesConversio.copy(mesura = it); mostrar = false
        }

        MyDropDown(
            modifier,
            dadesConversio.conversio,
            { dadesConversio.conversio = it; mostrar = false },
            expanded,
            { expanded = it },
            llistaConversions
        )

        MyButton("Convertir") {
            realitzarCalcul5(dadesConversio)
            mostrar = true
        }

        if (mostrar) {
            if (!dadesConversio.error) {
                Spacer(modifier.padding(2.dp))
                Text(text = "El resultat és ${dadesConversio.resultat}")
            } else {
                val context = LocalContext.current
                Toast.makeText(context, dadesConversio.missatge, Toast.LENGTH_LONG).show()
            }
        }

    }

}

//endregion exercici5

//region exercici6 - Lemonade App

enum class PassesLlimonada(val missatge: String, val imatge: Int) {
    AGAFAR("Agafa una llimona", R.drawable.lemon_tree),
    ESPREMER("Esprem la llimona", R.drawable.lemon_squeeze),
    BEURE("Beu-te-la", R.drawable.lemon_drink),
    TORNAR_A_COMENÇAR("Comença de nou", R.drawable.lemon_restart)
}

data class DadesLemonade(
    var pas: PassesLlimonada,
    var numClics: Int,
    var changed: Boolean = false
)


fun seguentPas(dades: DadesLemonade) {
    when (dades.pas) {
        PassesLlimonada.AGAFAR -> {
            dades.pas = PassesLlimonada.ESPREMER; dades.numClics = (1..10).random()
        }

        PassesLlimonada.ESPREMER -> {
            dades.numClics--; if (dades.numClics == 0) dades.pas = PassesLlimonada.BEURE
        }

        PassesLlimonada.BEURE -> {
            dades.pas = PassesLlimonada.TORNAR_A_COMENÇAR
        }

        PassesLlimonada.TORNAR_A_COMENÇAR -> {
            dades.pas = PassesLlimonada.AGAFAR
        }
    }
}


@Composable
fun Exercici6(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var dadesLemonade by remember { mutableStateOf(DadesLemonade(PassesLlimonada.AGAFAR, 0)) }
        dadesLemonade.changed = false

        Image(
            painter = painterResource(id = dadesLemonade.pas.imatge),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.clickable {
                seguentPas(dadesLemonade)
                dadesLemonade = dadesLemonade.copy(changed = true)
            }
        )

        Spacer(modifier.padding(20.dp))

        Text(dadesLemonade.pas.missatge, fontWeight = FontWeight.Bold, fontSize = 40.sp)

    }

}

//endregion exercici6

//region exercici7 - DiceRoller

data class DadesDices(
    var dice1: Int,
    var dice2: Int,
    var changed: Boolean
)

fun getDiceImage(infoDices:DadesDices, numDice:Int) : Int
{
    if (numDice == 1) {
      return getDiceImage(infoDices.dice1)
    }
    else
        return getDiceImage(infoDices.dice2)
}

fun getDiceImage(side:Int) : Int {
    return when (side) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice
    }
}

fun rollDices(infoDices: DadesDices) {
    rollDice(infoDices, 1)
    rollDice(infoDices, 2)
}

fun rollDice(infoDices: DadesDices, dice:Int) {
    if (dice==1) infoDices.dice1 = (1..6).random()
    else infoDices.dice2 = (1..6).random()
}

@Composable
fun Exercici7(modifier: Modifier = Modifier) {

    var infoDices by remember { mutableStateOf(DadesDices(0,0, false)) }
    infoDices.changed = false

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.tapestry),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }

    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier.weight(0.1f))

            Row(
                modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.title),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
            Row(modifier
                .fillMaxWidth()
                .weight(0.1f),
                horizontalArrangement = Arrangement.Center) {

                Button(
                    onClick = { rollDices(infoDices)
                                infoDices = infoDices.copy(changed = true)
                              },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red ),
                    modifier = modifier.fillMaxWidth().padding(2.dp)
                ) {
                    Text(text = "Roll the dice")
                }
            }
            Row(modifier
                .weight(0.3f)) {
                Image(
                    modifier = modifier.fillMaxHeight()
                        .clickable {
                            rollDice(infoDices,1)
                            infoDices = infoDices.copy(changed = true)
                        },
                    painter = painterResource(getDiceImage(infoDices,1)),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                Image(
                    modifier = modifier.fillMaxHeight()
                        .clickable {
                            rollDice(infoDices,2)
                            infoDices = infoDices.copy(changed = true)
                        },
                    painter = painterResource(getDiceImage(infoDices,2)),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }

            Spacer(modifier.weight(0.2f))

            if (infoDices.dice1 == 6 && infoDices.dice2 == 6) {
                val context = LocalContext.current
                Toast.makeText(context, "JACKPOT", Toast.LENGTH_SHORT).show()
            }

        }
    }


}

//endregion exercici5


/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExercicisAmbEstat() {
    MyApplicationTheme {
        Exercici(modifier = Modifier,exercici = 1)
    }
}
*/
