package com.Canhan.Car.dto;

import com.Canhan.Car.model.enums.VehicleStatus;

public class VehicleStatusRequest {
    private VehicleStatus Status;
    public void  setStatus(VehicleStatus status) {
        Status = status;
    }
    public VehicleStatus getStatus() {
        return Status;
    }
}
