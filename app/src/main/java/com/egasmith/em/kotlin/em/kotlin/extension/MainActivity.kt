package com.egasmith.em.kotlin.em.kotlin.extension

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.egasmith.em.kotlin.em.kotlin.extension.ui.theme.EM1KotlinExtensionTheme
import kotlin.random.Random

/*
Задача 3

Написать extention-функцию для List, которая в рантайме будет искать Int в списке типа Any и
возвращать его. Заранее подготовить список, наполненный разными классами(5-10 шт будет достаточно).

По нажатию на кнопку выводить результат в логи (не использовать рефлексию).
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EM1KotlinExtensionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FirstScreen(modifier: Modifier) {
    val listOfAny: MutableList<Any> = mutableListOf("1", 2, 2, 3, 1, "awfa", "waf",999)

    Column(
        modifier = modifier. fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            val intList = listOfAny.findIntegers()
            Log.d("Int list", intList.toString())
        }) {
            Text(text = "Log it")
        }

        Button(onClick = {
            listOfAny.add(getRandomNumberOrString())
        }) {
            Text(text = "Add something random")
        }
    }
}

fun List<Any>.findIntegers(): List<Int> {
    return this.filterIsInstance<Int>()
}

//Функция для проверки. Добавляет рандомное число или строку.
fun getRandomNumberOrString(): Any {
    return when (Random.nextBoolean()) {
        true -> Random.nextInt(1, 1000).also { Log.d("Int list", "Add number $it") }
        false -> getRandomString(5).also { Log.d("Int list", "Add string $it") }
    }
}

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
