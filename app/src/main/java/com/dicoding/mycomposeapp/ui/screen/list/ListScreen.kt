package com.dicoding.mycomposeapp.ui.screen.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mycomposeapp.di.Injection
import com.dicoding.mycomposeapp.model.Member
import com.dicoding.mycomposeapp.ui.ViewModelFactory
import com.dicoding.mycomposeapp.ui.common.UiState
import com.dicoding.mycomposeapp.ui.components.MemberItem

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMembers()
            }
            is UiState.Success -> {
                ListContent(
                    members = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ListContent(
    members: List<Member>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
    ) {
        items(members, key = { it.id }) { data ->
            MemberItem(
                image = data.image,
                name = data.name,
                description = data.description,
                modifier = modifier
                    .clickable { navigateToDetail(data.id) }
                    .padding(8.dp)
            )
            Divider()
        }
    }
}