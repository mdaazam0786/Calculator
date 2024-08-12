package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel : CalcViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val state = viewModel.viewState.collectAsState(initial = CalcViewModel.ViewState("0")).value
                CalculatorScreen(

                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}
@Composable
private fun CalculatorScreen(
    modifier: Modifier = Modifier,
    state : CalcViewModel.ViewState,
    onAction : (CalculatorAction) -> Unit,
){


    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            color = if (!isSystemInDarkTheme()){
                Color.White
            }else{
                Color(0xff17181A)
            }
        )) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = state.result,
                style = MaterialTheme.typography.displayLarge,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                textAlign = TextAlign.End,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Digits(number = "AC",
                    color = if(!isSystemInDarkTheme()){ Color.White } else Color.Black,
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = if (!isSystemInDarkTheme()){
                            Color(0xff505050)
                        }else{
                            Color.White
                        }, shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Clear)
                        })
                Digits(number = "Del",
                    color = if(!isSystemInDarkTheme()){ Color.White } else Color.Black,
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = if (!isSystemInDarkTheme()){
                            Color(0xff505050)
                        }else{
                            Color.White
                        }, shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Delete)
                        })
                Digits(number = "/",
                    color = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = Color(0xffFF9500), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                        })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Digits(number = "7",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(7))
                        })
                Digits(number = "8",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(8))
                        })
                Digits(number = "9",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(9))
                        })
                Digits(number = "X",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xffFF9500), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                        })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Digits(number = "4",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(4))
                        })
                Digits(number = "5",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(5))
                        })
                Digits(number = "6",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(6))
                        })
                Digits(number = "-",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xffFF9500), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                        })

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Digits(number = "1",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(1))
                        })
                Digits(number = "2",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(2))
                        })
                Digits(number = "3",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(3))
                        })
                Digits(number = "+",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xffFF9500), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                        })

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Digits(number = "0",
                    color = Color.White,
                    modifier = Modifier
                        .weight(1.5f)
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Number(0))
                        })
                Digits(number = ".",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xff505050), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Decimal)
                        })

                Digits(number = "=",
                    color = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xffFF9500), shape = RoundedCornerShape(50))
                        .clickable {
                            onAction(CalculatorAction.Calculate)
                        })

            }
        }
    }

}

