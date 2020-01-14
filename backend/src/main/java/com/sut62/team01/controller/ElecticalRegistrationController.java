package com.sut62.team01.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.entity.ElectricalRegistration;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.payload.NewElectricalRegistrationPayload;
import com.sut62.team01.repository.ElectricTypeRepository;
import com.sut62.team01.repository.ElectricalRegistrationRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ElecticalRegistrationController {

    @Autowired
    private ElectricalRegistrationRepository electricalregitrationRepository;

    @Autowired
    private ElectricTypeRepository electrictypeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoomBookingRepository roombookingRepository;

    @GetMapping("/ElectricalRegis")
    public Collection<ElectricalRegistration> getAllElecticalRegistration() {
        return electricalregitrationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/ElectricalRegis/{ROOMBOOKING_ID}/{ELECTRICTYPE_ID}/{STAFF_ID}")
    public ElectricalRegistration Save(ElectricalRegistration newElectricalRegistration,
            @PathVariable long ELECTRICTYPE_ID, @PathVariable long ROOMBOOKING_ID, @PathVariable long STAFF_ID) {
        Staff staff = staffRepository.findById(STAFF_ID);
        RoomBooking roomBooking = roombookingRepository.findById(ROOMBOOKING_ID);
        ElectricType electricType = electrictypeRepository.findById(ELECTRICTYPE_ID);
        newElectricalRegistration.setElectricalRegistrationdate(new Date());
        newElectricalRegistration.setStaff(staff);
        newElectricalRegistration.setRoomBooking(roomBooking);
        newElectricalRegistration.setElectricType(electricType);
        return electricalregitrationRepository.save(newElectricalRegistration);
    }

    @PostMapping("/electricRegister")
    public ElectricalRegistration newElectricalRegistration(@RequestBody NewElectricalRegistrationPayload electric) {
        ElectricalRegistration electricalRegistration = new ElectricalRegistration();

        Optional<Staff> staff = staffRepository.findById(electric.getStaffId());
        Optional<RoomBooking> roomBooking = roombookingRepository.findById(electric.getRoomBookingId());
        Optional<ElectricType> electricType = electrictypeRepository.findById(electric.getElectricTypeId());

        electricalRegistration.setElectricalRegistrationdate(new Date());
        electricalRegistration.setStaff(staff.get());
        electricalRegistration.setRoomBooking(roomBooking.get());
        electricalRegistration.setElectricType(electricType.get());
        electricalRegistration.setDetails(electric.getDetails());
        return electricalregitrationRepository.save(electricalRegistration);
    }
}