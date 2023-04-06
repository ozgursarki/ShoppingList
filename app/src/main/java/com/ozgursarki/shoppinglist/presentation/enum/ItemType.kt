package com.ozgursarki.shoppinglist.presentation.enum

enum class ItemType(val type: String) {
    FRUIT("Fruit"), VEGETABLES("Vegetables"), SNACK("Snack"), BREAKFAST("Breakfast"), OTHER("Other");


}

fun generateItemType(title: String): ItemType {
    return when (title) {
        "Kahvaltılık" -> ItemType.BREAKFAST
        "Sebze" -> ItemType.VEGETABLES
        "Meyve" -> ItemType.FRUIT
        "Atıştırmalık" -> ItemType.SNACK
        "Diğer" -> ItemType.OTHER
        else -> {
            ItemType.BREAKFAST
        }
    }
}