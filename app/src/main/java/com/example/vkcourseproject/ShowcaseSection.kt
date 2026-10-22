package com.example.vkcourseproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ShowcaseSection(
    apps: List<AppItem>,
    onAppClick: (AppItem) -> Unit
) {
    val pages = apps.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)) {
        Text(
            text = stringResource(R.string.showcase_text),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                pages[page].forEach { app ->
                    AppListItem(
                        app = app,
                        onClick = { onAppClick(app) }
                    )
                }
            }
        }
    }
}