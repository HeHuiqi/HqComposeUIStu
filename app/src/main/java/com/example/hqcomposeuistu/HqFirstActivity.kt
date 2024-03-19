package com.example.hqcomposeuistu

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.material.icons.filled.More
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hqcomposeuistu.ui.theme.HqComposeUIStuTheme

class HqFirstActivity :  ComponentActivity() {

    companion object {
        fun actionStart(context: Context) {
               val intent = Intent(context, HqFirstActivity::class.java)
               context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HqComposeUIStuTheme {
                HqApp()
            }

        }
        
    }
}
@Composable
fun HqApp() {

    //使用 rememberSaveable，而不使用 remember。这会保存每个在配置更改（如旋转）和进程终止后保留下来的状态。
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface {
        if (shouldShowOnboarding) {
            OnBoardingScreen(onContinueClicked = {
                shouldShowOnboarding = false
            })
        } else {
            Greetings()

        }
    }


}
@Composable
fun Greetings(modifier: Modifier = Modifier,names:List<String> = List(1000){"$it"}){

    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String,modifier: Modifier = Modifier){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}
@Composable
fun CardContent(name: String,modifier: Modifier = Modifier){
    // remember 或 rememberSaveable 可以起到保护作用，防止状态在重组时被重置
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    // 使用 animateDpAsState 可组合项。该可组合项会返回一个 State 对象，该对象的 value 会被动画持续更新，直到动画播放完毕
    // animate*AsState  *表示可动画的类型如Dp、Color、Size 等
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp, label = "yi",
//        //自定义动画方式
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
        Row(modifier = modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ){
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(text = "Hello")
                Text(text = name,
    //                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if (expanded){
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
//                Text(text = if (expanded) "Show Less" else "Show More")
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
                    , contentDescription = if (expanded) "Show Less" else "Show More"
                )
            }
        }


}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier) {

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome to the Basics Codelab!")
        Button(
            modifier = modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview(){
    HqComposeUIStuTheme {
        Greetings()
    }

}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    OnBoardingScreen(onContinueClicked = {

    })
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun HqAppPreview(){
    HqApp()
}
