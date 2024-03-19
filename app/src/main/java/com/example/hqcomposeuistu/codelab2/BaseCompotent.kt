package com.example.hqcomposeuistu.codelab2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hqcomposeuistu.R
import com.example.hqcomposeuistu.ui.theme.HqComposeUIStuTheme

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }
private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun SearchBarPreview(){
    SearchBar()
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
        // Column 对齐方式有 Start CenterHorizontally End
        // Row 对齐方式有 Top CenterVertically Bottom
        // Box 对齐方式有TopStart TopCenter TopEnd
        //CenterStart Center CenterEnd
        //BottomStart BottomCenter BottomEnd

        //让其子项目水平居中对齐
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = drawable) ,
            contentDescription = null,
            //图片的缩放方式
            // Fit FillBounds、Crop FillHeight FillWidth  Inside None
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview(){
    HqComposeUIStuTheme {
        AlignYourBodyElement(
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions)
    }

}
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {
    // RoundedCornerShape(12.0.dp) 圆角矩形
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row(
            modifier = modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(80.dp),
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(drawable = item.drawable, text = item.text)
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview(){
    HqComposeUIStuTheme {
        FavoriteCollectionCard(
            drawable =  R.drawable.fc2_nature_meditations,
            text =  R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview(){
    HqComposeUIStuTheme {
        FavoriteCollectionsGrid()
    }
}
@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable,item.text)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    AlignYourBodyRow()
}

@Composable
fun HomeSection(
    @StringRes title:Int,
    modifier: Modifier = Modifier,
    content: @Composable ()->Unit) {
    Column (modifier){
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    HqComposeUIStuTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Composable
fun TopCenterTitle(text: String = "Home") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
    ) {
        Text(
            text = text,
            modifier = Modifier.wrapContentSize(), // 让文本根据内容大小自适应
            color = Color.Black, // 文本颜色
            fontSize = 18.sp, // 文本大小
            textAlign = TextAlign.Center // 文本居中对齐
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopCenterTitlePreview() {
    TopCenterTitle()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FlowRowUse(){
    val filters = listOf(
        "Washer/Dryer", "Ramp access", "Garden", "Cats OK", "Dogs OK", "Smoke-free"
    )
    FlowRow (
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        filters.forEach { title ->
            var selected by rememberSaveable { mutableStateOf(false) }

            val leadingIcon : @Composable () -> Unit = {
                Icon(Icons.Default.Check, null)
            }
            FilterChip(
                selected = selected,
                onClick = { selected = !selected },
                label = { Text(text = title, color = Color.White) },
                leadingIcon = if (selected) leadingIcon else null
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FlowRowUsePreview(){
    FlowRowUse()
}