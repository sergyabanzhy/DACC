package com.example.dacg.presentation.vehicleLocation

import android.util.Log
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.domain.vehicleUseCase.IVehicleUseCase
import com.example.dacg.domain.vehicleUseCase.VehicleUseCase
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VehicleLocationPresenter(private val useCase: IVehicleUseCase = VehicleUseCase(),
                               private val uiScheduler: Scheduler = AndroidSchedulers.mainThread(),
                               private val bgScheduler: Scheduler = Schedulers.io()) : IVehicleLocationPresenter {

    private var view: IView? = null

    private val disposables = CompositeDisposable()

    override fun attach(view: IView) {
        Log.d(TAG, "attach")

        this.view = view
    }

    override fun detach() {
        Log.d(TAG, "detach")

        disposables.dispose()

        view = null
    }

    override fun mapIsReadyToShow(vehicle: Vehicle) {
        Log.d(TAG, "mapIsReadyToShow, vehicle id [${vehicle.id}]")

        view?.showVehicleOnMap(vehicle)
    }

    override fun onMapBoundsCalculated(farLeft: LatLng, nearRight: LatLng) {
        Log.d(TAG, "onMapBoundsCalculated, mapBoundsOnScreen farLeft - [$farLeft], nearRight - [$nearRight] ")

        val vehiclesDisposable = useCase.getVehicle(farLeft.latitude, farLeft.longitude, nearRight.latitude, nearRight.longitude)
            .observeOn(uiScheduler)
            .subscribeOn(bgScheduler)
            .subscribe ({
                Log.d(TAG, "onResponse, ${it.size}")

                view?.onShowVehiclesOnMap(it)

            }, {
                Log.d(TAG, "onError, ${it.message}")
                view?.onShowError(it.message ?: "Request error")
            })

        disposables.add(vehiclesDisposable)
    }

    companion object {
        private val TAG = VehicleLocationPresenter::class.java.simpleName
    }
}