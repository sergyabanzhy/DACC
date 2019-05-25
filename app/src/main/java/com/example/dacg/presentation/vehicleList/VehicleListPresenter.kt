package com.example.dacg.presentation.vehicleList

import android.util.Log
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.domain.vehicleUseCase.IVehicleUseCase
import com.example.dacg.domain.vehicleUseCase.VehicleUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VehicleListPresenter(private val useCase: IVehicleUseCase = VehicleUseCase(),
                           private val uiScheduler: Scheduler = AndroidSchedulers.mainThread(),
                           private val bgScheduler: Scheduler = Schedulers.io()): IVehicleListPresenter {

    private var view: IView? = null

    private var disposables: CompositeDisposable = CompositeDisposable()

    private var savedVehicles: List<Vehicle>? = null

    override fun attach(view: IView) {
        Log.d(TAG, "attach")

        this.view = view

        requestVehicles()
    }

    private fun requestVehicles() {
        Log.d(TAG, "requestVehicles")

        savedVehicles?.apply {
            view?.onShowVehicles(this)
            return
        }

        view?.showProgress()

        val vehiclesDisposable = useCase.getVehicle(53.694865, 9.757589, 53.394655, 10.099891)
            .observeOn(uiScheduler)
            .subscribeOn(bgScheduler)
            .subscribe ({
                Log.d(TAG, "onResponse, ${it.size}")

                view?.onShowVehicles(it)

                view?.hideProgress()

            }, {
                Log.d(TAG, "onError, ${it.message}")
                view?.onShowError(it.message ?: "Request error")
            })

        disposables.add(vehiclesDisposable)
    }

    override fun vehicleItemPressed(vehicle: Vehicle) {
        Log.d(TAG, "vehicleItemPressed, vehicle id [${vehicle.id}]")

        view?.showVehicleLocation(vehicle)
    }

    override fun storeVehiclesList(vehicles: List<Vehicle>) {
        Log.d(TAG, "storeVehiclesList")

        savedVehicles = vehicles
    }

    override fun detach() {
        Log.d(TAG, "detach")

        disposables.dispose()

        view = null
    }

    companion object {
        private const val TAG = "VehicleListPresenter"
    }
}