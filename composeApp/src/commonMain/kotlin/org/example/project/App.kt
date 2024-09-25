package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform

// Modelo de datos mockeado
data class Item(val id: Int, val title: String, val imageUrl: String)

// Función que genera 100 elementos mockeados
fun getMockedItems(): List<Item> {
    return List(100) { index ->
        Item(
            id = index,
            title = "Item $index",
            imageUrl = "https://via.placeholder.com/150" // URL mockeada para la imagen
        )
    }
}
@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ItemList(getMockedItems())
        }

    }
}

@Composable
fun ItemList(items: List<Item>) {
    LazyColumn {
        items(items = items) { item ->
            ItemRow(item)
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Row(modifier = Modifier.padding(8.dp)) {
        // Carga de imagen desde URL usando Coil
        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
        // Texto
        Text(
            text = item.title,
            modifier = Modifier.padding(8.dp),
        )
    }
}