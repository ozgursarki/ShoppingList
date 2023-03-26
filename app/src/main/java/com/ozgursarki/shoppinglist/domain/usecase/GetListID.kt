package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class GetListID @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) {
    operator fun invoke(): Long {
        return shoppingRepository.getListID()
    }
}