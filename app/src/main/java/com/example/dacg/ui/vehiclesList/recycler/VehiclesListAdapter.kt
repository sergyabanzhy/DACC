package com.example.dacg.ui.vehiclesList.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dacg.R
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle

class VehiclesListAdapter(private val onItemClicked: (vehicle: Vehicle) -> Unit) :
    RecyclerView.Adapter<VehicleViewHolder>() {

    var vehicles: List<Vehicle> = emptyList()

    override fun onCreateViewHolder(container: ViewGroup, type: Int): VehicleViewHolder {

        val view = LayoutInflater.from(container.context).inflate(R.layout.row_vehicle_item, container, false)

        return VehicleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles[position], onItemClicked)
    }
}