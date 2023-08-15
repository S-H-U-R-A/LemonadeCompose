package com.sergio.rodriguez.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergio.rodriguez.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Composable
fun LemonApp(modifier: Modifier = Modifier) {

    var currentStep: Int by remember {
        mutableStateOf(1)
    }

    var squeezeCount: Int by remember {
        mutableStateOf(0)
    }

    when (currentStep) {
        1 -> {
            LemonTextAndImage(
                imageResource = R.drawable.lemon_tree,
                contentText = R.string.lemon_select,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            LemonTextAndImage(
                imageResource = R.drawable.lemon_squeeze,
                contentText = R.string.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description,
                onImageClick = {
                    squeezeCount -= 1
                    if(squeezeCount == 0){
                        currentStep = 3
                    }
                }
            )
        }
        3 -> {
            LemonTextAndImage(
                imageResource = R.drawable.lemon_drink,
                contentText = R.string.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description,
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            LemonTextAndImage(
                imageResource = R.drawable.lemon_restart,
                contentText = R.string.lemon_empty_glass,
                contentDescriptionResourceId = R.string.empty_glass_content_description,
                onImageClick = {
                    currentStep = 1
                }
            )
        }

    }
}

@Composable
fun LemonTextAndImage(
    imageResource: Int,
    contentText: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                onImageClick()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = contentDescriptionResourceId),
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = contentText),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun LemonAppPreview() {
    LemonadeTheme {
        LemonApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}