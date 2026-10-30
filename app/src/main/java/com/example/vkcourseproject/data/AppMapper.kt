package com.example.vkcourseproject.data

import com.example.vkcourseproject.domain.AppItem

fun AppDto.toDomain(): AppItem {
    return AppItem(
        id = this.id,
        name = this.appName,
        description = this.shortDesc,
        category = this.cat,
        icon = this.iconEmoji,
        developer = this.dev,
        ageRestriction = this.age,
        size = this.fileSize,
        fullDescription = this.longDesc
    )
}