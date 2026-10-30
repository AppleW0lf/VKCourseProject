package com.example.vkcourseproject.data

data class AppDto(
    val id: Int,
    val appName: String,
    val shortDesc: String,
    val cat: String,
    val iconEmoji: String,
    val dev: String,
    val age: String,
    val fileSize: String,
    val longDesc: String
)
val mockDatabase = listOf(
    AppDto(1, "Minecraft", "Строй, исследуй и выживай", "Песочница", "⛏️", "Mojang", "6+", "800 MB", "Minecraft — это игра, в которой можно построить всё, что угодно."),
    AppDto(2, "Genshin Impact", "Открытый мир и магия", "RPG", "⚔️", "HoYoverse", "12+", "3 GB", "Эпическая RPG в открытом мире."),
    AppDto(3, "Standoff 2", "Динамичный мобильный шутер", "Экшен", "🔫", "Axlebolt", "16+", "1.2 GB", "Динамичный шутер от первого лица."),
    AppDto(4, "Brawl Stars", "Быстрые бои 3 на 3", "MOBA", "⭐", "Supercell", "6+", "400 MB", "Сражайтесь в команде 3 на 3."),
    AppDto(5, "Clash of Clans", "Строй деревню и воюй", "Стратегия", "🏰", "Supercell", "12+", "500 MB", "Стратегия, в которой вы строите деревню и собираете армию."),
    AppDto(6, "Among Us", "Найди предателя", "Мультиплеер", "🚀", "Innersloth", "12+", "250 MB", "Игра на социальную дедукцию."),
    AppDto(7, "Asphalt 9", "Гонки на гиперкарах", "Гонки", "🏎️", "Gameloft", "6+", "2.5 GB", "Аркадные гонки с лучшими машинами."),
    AppDto(8, "PUBG Mobile", "Королевская битва", "Battle Royale", "🪂", "Level Infinite", "16+", "1.8 GB", "Побеждайте в королевской битве."),
    AppDto(9, "Stardew Valley", "Фермерский симулятор", "Симулятор", "🌾", "ConcernedApe", "6+", "300 MB", "Уютная игра про ферму и общение.")
)