package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class SaveListID @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) {
    operator fun invoke(listID: Long) {
        shoppingRepository.saveListID(listID)
    }
}