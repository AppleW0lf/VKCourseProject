package com.example.vkcourseproject.domain

data class AppItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val icon: String,
    val developer: String = "Неизвестный разработчик",
    val ageRestriction: String = "0+",
    val size: String = "100 MB",
    val fullDescription: String = "Подробное описание отсутствует."
)

val sampleApps = listOf(
    AppItem(1, "Minecraft", "Строй, исследуй и выживай", "Песочница", "⛏️", "Mojang", "6+", "800 MB", "Minecraft — это игра, в которой можно построить всё, что угодно."),
    AppItem(2, "Genshin Impact", "Открытый мир и магия", "RPG", "⚔️", "HoYoverse", "12+", "3 GB", "Эпическая RPG в открытом мире."),
    AppItem(3, "Standoff 2", "Динамичный мобильный шутер", "Экшен", "🔫", "Axlebolt", "16+", "1.2 GB", "Динамичный шутер от первого лица."),
    AppItem(4, "Brawl Stars", "Быстрые бои 3 на 3", "MOBA", "⭐", "Supercell", "6+", "400 MB", "Сражайтесь в команде 3 на 3."),
    AppItem(5, "Clash of Clans", "Строй деревню и воюй", "Стратегия", "🏰", "Supercell", "12+", "500 MB", "Стратегия, в которой вы строите деревню и собираете армию."),
    AppItem(6, "Among Us", "Найди предателя", "Мультиплеер", "🚀", "Innersloth", "12+", "250 MB", "Игра на социальную дедукцию."),
    AppItem(7, "Asphalt 9", "Гонки на гиперкарах", "Гонки", "🏎️", "Gameloft", "6+", "2.5 GB", "Аркадные гонки с лучшими машинами."),
    AppItem(8, "PUBG Mobile", "Королевская битва", "Battle Royale", "🪂", "Level Infinite", "16+", "1.8 GB", "Побеждайте в королевской битве."),
    AppItem(9, "Stardew Valley", "Фермерский симулятор", "Симулятор", "🌾", "ConcernedApe", "6+", "300 MB", "Уютная игра про ферму и общение.")
)
val showcaseApps = sampleApps.take(9)