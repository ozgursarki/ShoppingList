package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import javax.inject.Inject

class SaveSaveToolTip @Inject constructor(
    private val repository: ShoppingRepository
) {

    fun saveSaveToolTip() {
        return repository.saveSave()
    }
}