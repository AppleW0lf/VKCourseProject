package com.example.vkcourseproject

data class AppItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val icon: String
)

val sampleApps = listOf(
    AppItem(1, "Minecraft", "Строй, исследуй и выживай в кубическом мире", "Песочница", "⛏️"),
    AppItem(2, "Genshin Impact", "Открытый мир, магия и приключения", "RPG", "⚔️"),
    AppItem(3, "Standoff 2", "Динамичный мобильный шутер", "Экшен", "🔫"),
    AppItem(4, "Brawl Stars", "Быстрые бои 3 на 3 с кучей бойцов", "MOBA", "⭐"),
    AppItem(5, "Clash of Clans", "Строй деревню, собирай армию, воюй", "Стратегия", "🏰"),
    AppItem(6, "Among Us", "Найди предателя на космическом корабле", "Мультиплеер", "🚀"),
    AppItem(7, "Asphalt 9: Legends", "Гонки на гиперкарах по всему миру", "Гонки", "🏎️"),
    AppItem(8, "PUBG Mobile", "Королевская битва на 100 игроков", "Battle Royale", "🪂")
)