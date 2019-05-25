package com.example.dacg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dacg.R
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.ui.navigation.IScreensRouter
import com.example.dacg.ui.vehiclesList.VehiclesListFragment
import com.example.dacg.ui.vehiclesMap.VehiclesLocationFragment

class MainActivity : AppCompatActivity(), IScreensRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToList()
    }

    override fun navigateToList() {
        Log.d(TAG, "navigateToList")

        if (supportFragmentManager.findFragmentByTag(VehiclesListFragment::class.java.simpleName) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flContainer, VehiclesListFragment.create(), VehiclesListFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun navigateToMap(itemToShow: Vehicle) {
        Log.d(TAG, "navigateToMap")

        if (supportFragmentManager.findFragmentByTag(VehiclesLocationFragment::class.java.simpleName) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flContainer, VehiclesLocationFragment.create(itemToShow), VehiclesLocationFragment::class.java.simpleName)
                .addToBackStack(VehiclesLocationFragment::class.java.simpleName)
                .commit()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
