package com.example.vkcourseproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkcourseproject.R
import com.example.vkcourseproject.data.AppRepositoryImpl
import com.example.vkcourseproject.domain.AppItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    appId: Int,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val repository = remember { AppRepositoryImpl() }
    val app by produceState<AppItem?>(initialValue = null, appId) {
        val apps = repository.getApps()
        value = apps.find { it.id == appId } ?: apps.firstOrNull()
    }
    if (app == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    val showcaseApps by produceState<List<AppItem>>(initialValue = emptyList()) {
        value = repository.getShowcaseApps()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(app!!.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = app!!.icon, fontSize = 40.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(app!!.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(app!!.developer, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(app!!.ageRestriction, fontWeight = FontWeight.Bold)
                            Text(stringResource(R.string.app_detail_age), style = MaterialTheme.typography.labelSmall)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(app!!.size, fontWeight = FontWeight.Bold)
                            Text(stringResource(R.string.app_detail_size), style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.app_detail_install))
            }


            Text(
                stringResource(R.string.app_detail_screenshots),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            val placeholderColors = listOf(
                Color(0xFFE3F2FD), Color(0xFFFFF3E0), Color(0xFFE8F5E9),
                Color(0xFFF3E5F5), Color(0xFFFFEBEE)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(placeholderColors) { color ->
                    Box(
                        modifier = Modifier
                            .size(width = 160.dp, height = 280.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color)
                    ) {
                        Text(
                            "Скриншот",
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Gray
                        )
                    }
                }
            }


            Text(stringResource(R.string.app_detail_description), style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
            Text(app!!.fullDescription, modifier = Modifier.padding(horizontal = 16.dp))

            HorizontalDivider(modifier = Modifier.padding(16.dp))


            ShowcaseSection(
                apps = showcaseApps.filter { it.id != app!!.id },
                onAppClick = onShowcaseAppClick
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}