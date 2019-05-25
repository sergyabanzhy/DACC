package com.example.dacg.ui.vehiclesList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dacg.R
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import com.example.dacg.presentation.vehicleList.IVehicleListPresenter
import com.example.dacg.presentation.vehicleList.IView
import com.example.dacg.presentation.vehicleList.VehicleListPresenter
import com.example.dacg.ui.navigation.IScreensRouter
import com.example.dacg.ui.vehiclesList.recycler.VehiclesListAdapter
import kotlinx.android.synthetic.main.fragment_vehicles.*


class VehiclesListFragment : Fragment(), IView {

    private val presenter: IVehicleListPresenter = VehicleListPresenter()

    private lateinit var vehiclesListAdapter: VehiclesListAdapter

    private lateinit var callback: IScreensRouter

    override fun onAttach(context: Context) {
        Log.d(TAG, "onCreateView")
        super.onAttach(context)

        callback = context as IScreensRouter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.fragment_vehicles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        savedInstanceState?.getParcelableArrayList<Vehicle>(SAVED_VEHICLES)?.apply {
            presenter.storeVehiclesList(this)
        }
    }

    override fun onShowVehicles(vehicles: List<Vehicle>) {
        Log.d(TAG, "onShowVehicles, vehicles size [${vehicles.size}]")

        vehiclesListAdapter.vehicles = vehicles

        vehiclesListAdapter.notifyDataSetChanged()
    }

    override fun onShowError(message: String) {
        Log.d(TAG, "onShowError, message [$message]")
    }

    override fun showProgress() {
        Log.d(TAG, "showProgress")
    }

    override fun hideProgress() {
        Log.d(TAG, "hideProgress")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(SAVED_VEHICLES, ArrayList(vehiclesListAdapter.vehicles))
    }

    override fun showVehicleLocation(vehicle: Vehicle) {
        Log.d(TAG, "showVehicleLocation]")

        callback.navigateToMap(vehicle)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()

        presenter.attach(this)
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()

        presenter.detach()
    }

    private fun initRecycler() {
        Log.d(TAG, "initRecycler")

        val dividerItemDecoration = DividerItemDecoration(rvVehicles.context, LinearLayoutManager.VERTICAL)

        rvVehicles.addItemDecoration(dividerItemDecoration)

        vehiclesListAdapter = VehiclesListAdapter {
            presenter.vehicleItemPressed(it)
        }

        rvVehicles.adapter = vehiclesListAdapter
    }

    companion object {

        private const val SAVED_VEHICLES = "SAVED_VEHICLES"

        private val TAG = VehiclesListFragment::class.java.simpleName

        fun create(): VehiclesListFragment {

            return VehiclesListFragment()
        }
    }
}