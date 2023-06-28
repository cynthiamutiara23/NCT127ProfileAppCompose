package com.dicoding.mycomposeapp.ui.screen.detail

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mycomposeapp.R
import com.dicoding.mycomposeapp.di.Injection
import com.dicoding.mycomposeapp.ui.ViewModelFactory
import com.dicoding.mycomposeapp.ui.common.UiState
import com.dicoding.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun DetailScreen(
    memberId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMemberById(memberId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.image,
                    name = data.name,
                    birthdate = data.birthdate,
                    position = data.position,
                    description = data.description,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    birthdate: String,
    position: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
                title = { Text(name) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onBackClick() }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier.size(170.dp, 260.dp)
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 8.dp)
                        .weight(1.0f),
                ) {
                    Text(
                        text = stringResource(R.string.name),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = name,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        ),
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.birthdate),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = birthdate,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                        ),
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.position),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = position,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                        ),
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                }
            }
            Text(
                text = stringResource(R.string.description),
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            )
            Text(
                text = description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                ),
                modifier = modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MyComposeAppTheme {
        DetailContent(
            R.drawable.member_3,
            "Taeyong",
            "July 1, 1995",
            "Leader, Main Rapper, Main Dancer, Sub Vocalist, Visual, Center, Face of the Group",
            description = "Lee Tae-yong (Hangul: 이태용), known by his mononym Taeyong, is a South Korean lead rapper, songwriter, lead dancer, choreographer, visual, and singer. He is the leader and member of the South Korean male vocal group NCT in the subunit NCT U and is the leader of NCT 127. He is also currently a member of another SM Entertainment vocal group, SuperM.",
            onBackClick = {},
        )
    }
}