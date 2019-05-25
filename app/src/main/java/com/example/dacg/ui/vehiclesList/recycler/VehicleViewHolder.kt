package com.example.dacg.ui.vehiclesList.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dacg.R
import com.example.dacg.domain.vehicleUseCase.entity.Vehicle
import kotlinx.android.synthetic.main.row_vehicle_item.view.*

class VehicleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Vehicle, onItemClicked: (vehicle: Vehicle) -> Unit) {

        bindItem(item)

        bindClick(onItemClicked, item)
    }

    private fun bindItem(item: Vehicle) {

        view.tvType.text = item.fleetType
        val icon = if (item.fleetType == "TAXI") R.drawable.ic_local_taxi else R.drawable.ic_directions_car
        view.ivCarIcon.setImageDrawable(view.context.getDrawable(icon))
    }

    private fun bindClick(onItemClicked: (vehicle: Vehicle) -> Unit, item: Vehicle) {

        view.setOnClickListener {
            onItemClicked.invoke(item)
        }
    }
}