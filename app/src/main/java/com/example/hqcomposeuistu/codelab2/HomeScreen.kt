package com.example.hqcomposeuistu.codelab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text


import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hqcomposeuistu.R
import com.example.hqcomposeuistu.ui.theme.HqComposeUIStuTheme


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    //verticalScroll 添加垂直滚动
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = modifier.height(16.dp))
        SearchBar(modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    HqComposeUIStuTheme {
        HomeScreen()
    }
}

@Composable
fun SootheBottomNavigation(
    modifier: Modifier = Modifier,
    selectedIndex:Int = 0,
    itemClick:(index:Int) -> Unit,
    ) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.navigationBarsPadding()
    ) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { itemClick(0) },
            icon = {
                Icon(imageVector = Icons.Default.Spa, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            }
        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { itemClick(1)},
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            }

        )
    }
}

@Preview(showBackground = true)
@Composable
fun SootheBottomNavigationPreview() {
    HqComposeUIStuTheme {
        SootheBottomNavigation(itemClick = {

        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySootheAppPortrait() {
    val titles = listOf<String>("首页","我的")
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomItemClick = fun (index:Int){
        selectedIndex = index
    }
    HqComposeUIStuTheme {
        Scaffold(

            topBar = {

                TopAppBar(
                    title = { TopCenterTitle(text = titles[selectedIndex]) },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            },
            bottomBar = { SootheBottomNavigation(selectedIndex = selectedIndex,itemClick = bottomItemClick) },
        ) { padding ->
            BoxWithConstraints(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                when (selectedIndex) {
                    0 -> {
                        HomeScreen()
                    }
                    1 -> {
                        Text(text = "我的信息")
                    }
                }
                
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MySootheAppPortraitPreView() {
    MySootheAppPortrait()
}

@Composable
fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Spa, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_home))
                }
            )
            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                }

            )
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    HqComposeUIStuTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MySootheAppLandscapePreview() {
    MySootheAppLandscape()
}

@Composable
fun MySootheApp(isPortrait:Boolean = true){
    if (isPortrait) {
        MySootheAppPortrait()
    } else {
        MySootheAppLandscape()
    }
}
