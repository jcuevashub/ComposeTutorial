package com.example.composetutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme{
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
       items(items = names) {
               name ->   Greeting(name = name)
       }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
         colors = CardDefaults.cardColors(
             containerColor = MaterialTheme.colorScheme.primary
         ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
        CardContent(name)
    }
//    var expanded by remember { mutableStateOf(false) }
//
//    val extraPadding by animateDpAsState(
//        if(expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        ),
//        label = ""
//    )
//
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
//                Text(text = "Hello ", style = MaterialTheme.typography.headlineMedium.copy(
//                    fontWeight = FontWeight.ExtraBold
//                ))
//                Text(text = name, style = MaterialTheme.typography.headlineMedium)
//            }
//            ElevatedButton(onClick = { expanded = !expanded }) {
//                Text(if (expanded) "Show less" else "Show more")
//            }
//        }
//    }
}

@Composable
fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .padding(12.dp).animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
            Text("Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Welcome to the Basic CodeLab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked) {
            Text("Continue")
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
    }
}
@Preview
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}


//data class Message(val author: String, val body: String)

//@Composable
//fun MessageCard(msg: Message) {
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(R.drawable.profile),
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        var isExpanded by remember { mutableStateOf(false) }
//        val surfaceColor by animateColorAsState(
//            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
//            label = ""
//        )
//
//        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
//            Text(
//                text = msg.author,
//                color = surfaceColor,
//                style = MaterialTheme.typography.titleSmall
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Surface(
//                shape = MaterialTheme.shapes.medium,
//                shadowElevation = 1.dp,
//                color = surfaceColor,
//                modifier = Modifier.animateContentSize().padding(1.dp)
//            ) {
//                Text(
//                    text = msg.body,
//                    modifier = Modifier.padding(all = 4.dp),
//                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//
//        }
//    }
//
//}

//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewMessageCard() {
//    MessageCard(
//        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
//    )
//}

//@Composable
//fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}

//@Preview
//@Composable
//fun PreviewConversation() {
//    ComposeTutorialTheme {
//        Conversation(SampleData.conversationSample)
//    }
//}