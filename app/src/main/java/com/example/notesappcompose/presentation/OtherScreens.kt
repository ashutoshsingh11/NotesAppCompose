package com.example.notesappcompose.presentation

import com.example.notesappcompose.R.*
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.notesappcompose.ui.theme.orange
import com.example.notesappcompose.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable // LazyTextDisplay
fun DisplayTextSlowly(modifier: Modifier = Modifier, originalText: String, delay: Long = 300) {
    var textToDisplay by remember { mutableStateOf("") }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            for (char in originalText) {
                delay(delay)
                textToDisplay += char
            }
        }
    }
    Text(text = textToDisplay)
}

@Preview(showBackground = true)
@Composable
fun DialogBoxPrev(modifier: Modifier = Modifier) {
    DialogBox(onDismiss = {} , )
}
@Composable
fun DialogBox(
    onDismiss:()->Unit,
//    onConfirm:()->Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, color = orange, shape = RoundedCornerShape(15.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ){

                Text(
                    text = "Your selected items. Please select a payment method to continue.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Samsung Galaxy S22")
                        Text("799,00$")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Galaxy S22 Cover Case")
                        Text("32,00$")
                    }
                    Divider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Total", fontWeight = FontWeight.Bold)
                        Text("831,00$", fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Image(
                            painter = painterResource(id = drawable.klarna),
                            contentDescription = "klarna",
                            modifier = Modifier
                                .fillMaxWidth(0.2f)
                                .clip(RoundedCornerShape(15.dp))
                                .clickable { }
                        )
                        Image(
                            painter = painterResource(id = drawable.paypal),
                            contentDescription = "klarna",
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .clip(RoundedCornerShape(15.dp))
                                .clickable { }
                        )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                        ,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
//                            onConfirm()
                        },
                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }
    }
}






@Composable
fun DialogBox2(
//    modifier: Modifier = Modifier.size(100.dp),
    onDismiss: () -> Unit ,
) {
    var showDialog by remember { mutableStateOf(onDismiss) }
    Dialog(
        onDismissRequest = {
            showDialog()
//            showDialog = false
            Log.d("TAG", "DialogBox: ")
        }, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    )
    {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            Box(modifier = Modifier.fillMaxWidth(.95f)) {
                Column(
                    modifier = Modifier.fillMaxWidth(.95f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "",
                        modifier = Modifier.clickable {
                            onDismiss()
//                      showDialog=false
                            Log.d("TAG", "Close Button  ")
                        })

                    Text(text = "Dialog")
                }
            }
        }
    }
}
