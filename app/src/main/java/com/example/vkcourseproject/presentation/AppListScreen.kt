package com.example.vkcourseproject.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkcourseproject.domain.AppItem

@Composable
fun AppListScreen(
    onAppClick: (AppItem) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is AppListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            AppListToolbar(onMenuClick = { viewModel.onToolbarClicked() })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        if (state.isLoading) {
            Box(modifier = modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
        LazyColumn(
            modifier = modifier.padding(innerPadding).fillMaxSize(),
        ) {
            items(state.apps, key = { it.id }) { app ->
                AppListItem(app = app,
                    onClick = {onAppClick(app)})
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                }
            }
        }
    }
}
