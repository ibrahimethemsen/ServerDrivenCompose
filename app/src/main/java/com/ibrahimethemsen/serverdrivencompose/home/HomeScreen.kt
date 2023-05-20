@file:OptIn(ExperimentalComposeUiApi::class)

package com.ibrahimethemsen.serverdrivencompose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ibrahimethemsen.serverdrivencompose.DEF_STRING
import com.ibrahimethemsen.serverdrivencompose.model.ContainerActivity
import com.ibrahimethemsen.serverdrivencompose.model.ItemActivity
import com.ibrahimethemsen.serverdrivencompose.R.drawable as AppDrawable


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeScreenUi by viewModel.homeScreenUi
    val activityList by viewModel.activityList

    LazyColumn(
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            }
            .testTag("containerList")
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(homeScreenUi) {
            when (it.viewType) {
                "container_appbar" -> {
                    ContainerAppbarView()
                }

                "container_activity" -> {
                    ContainerActivityView(it.containerActivity, activityList)
                }

                "container_mock" -> {
                    ContainerMockView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerAppbarView() {
    var searchRemember by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = searchRemember,
            onValueChange = { searchRemember = it },
            leadingIcon = {
                Icon(painter = painterResource(id = AppDrawable.ic_search), contentDescription = "")
            },
            label = {
                Text(text = "Hint")
            }
        )
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = AppDrawable.ic_notification),
            contentDescription = ""
        )
    }
}

@Composable
fun ContainerActivityView(containerActivity: ContainerActivity, activityList: List<ItemActivity>) {
    Column(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        }.testTag("containerActivity")
    ) {
        Row {
            Text(
                text = containerActivity.title,
                fontSize = containerActivity.titleSize.sp,
                color = containerActivity.titleColor.setTextColor()
            )
            Spacer(modifier = Modifier.weight(1f))
            if (containerActivity.endTitle != String.DEF_STRING) {
                Text(text = containerActivity.endTitle)
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(activityList) {
                ItemActivityView(it, containerActivity)
            }
        }
    }
}

@Composable
fun ItemActivityView(activityItem: ItemActivity, containerActivity: ContainerActivity) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(240.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AsyncImage(
                    model = activityItem.productImage,
                    contentDescription = "",
                    Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                if (activityItem.productFavorite) Icon(
                    painter = painterResource(id = AppDrawable.ic_star),
                    contentDescription = "",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = activityItem.productReduction,
                color = activityItem.productReductionColor.setTextColor(),
                modifier = Modifier
                    .background(activityItem.productReductionBg.setTextColor())
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = activityItem.productName,
                color = containerActivity.adapterActivity.productTitleColor.setTextColor(),
                fontSize = containerActivity.adapterActivity.productTitleSize.sp,
            )
            Text(
                text = activityItem.productPrice.toString(),
                color = containerActivity.adapterActivity.productPriceColor.setTextColor(),
                fontSize = containerActivity.adapterActivity.productPriceSize.sp
            )
        }
    }
}


@Composable
fun ContainerMockView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Blue)
    ) {
        Text(
            text = "Container Mock",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}


fun String.setTextColor(): Color {
    return when (this) {
        "red" -> Color.Red
        "blue" -> Color.Blue
        "green" -> Color.Green
        else -> {
            Color.Black
        }
    }
}
