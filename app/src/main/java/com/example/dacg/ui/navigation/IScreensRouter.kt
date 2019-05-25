package com.example.dacg.ui.navigation

import com.example.dacg.domain.vehicleUseCase.entity.Vehicle

interface IScreensRouter {
    fun navigateToList()
    fun navigateToMap(itemToShow: Vehicle)
}