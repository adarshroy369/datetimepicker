package com.example.datetimepicker

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datetimepicker.ui.theme.DatetimepickerTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatetimepickerTheme {
                var pickedDate by remember {
                    mutableStateOf(LocalDate.now())
                }

                var pickedTime by remember {
                    mutableStateOf(LocalTime.NOON)
                }


                val formattedDate by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("MMM dd yyyy")
                            .format(pickedDate)
                    }
                }

                val formattedTime by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("hh:mm")
                            .format(pickedTime)

                    }
                }

                val dateDialogState = rememberMaterialDialogState()
                val timeDialogState = rememberMaterialDialogState()


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Button(onClick = { dateDialogState.show() }) {
                        Text(text = "pick a date")
                    }
                    Text(text = formattedDate)
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { timeDialogState.show() }) {
                        Text(text = "pick a time")
                    }
                    Text(text = formattedTime)

                }

                MaterialDialog(dialogState = dateDialogState,
                    buttons = {
                        positiveButton(text = "ok") {
                            Toast.makeText(
                                applicationContext,
                                "Clicked ok",
                                Toast.LENGTH_LONG
                            ).show()
                        }


                    }) {


                    datepicker(
                        initialDate = LocalDate.now(),
                        title = "Pick a date",


                        ) {
                        pickedDate = it
                    }


                }

                    MaterialDialog(dialogState = timeDialogState,

                        buttons = {
                            positiveButton(text = "ok") {
                                Toast.makeText(
                                    applicationContext,
                                    "Clicked ok",
                                    Toast.LENGTH_LONG
                                ).show()
                            }


                        }) {
                        timepicker(
                            initialTime = LocalTime.NOON,
                            title = "pick a time",
                            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
                        ) {
                            pickedTime = it
                        }


                    }
                }
            }
        }
    }


