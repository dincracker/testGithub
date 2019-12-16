package com.sut62.team01.sut62team01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

import com.sut62.team01.sut62team01.entity.Repair;
import com.sut62.team01.sut62team01.entity.DeviceProblem;
import com.sut62.team01.sut62team01.entity.DeviceType;

import com.sut62.team01.sut62team01.repository.RepairRepository;
import com.sut62.team01.sut62team01.repository.DeviceTypeRepository;
import com.sut62.team01.sut62team01.repository.DeviceProblemRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RepairController{
    
    @Autowired
    private final RepairRepository repairRepository;
    
    @Autowired
    private final DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private final DeviceProblemRepository deviceProblemRepository;

    RepairController(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }
    

    @GetMapping("/Repar")
    public Collection<Repair> getAllRepairs() { // collection ส่งกลับทั้งหมด
        return repairRepository.findAll().stream().collect(Collectors.toList());

    }

    @PostMapping("/Repair/{RoomBooking_id}/{DeviceType_id}/{DeviceProblem_id}/{list}")
    public Repair newRepair(Repair newRepair,
    @PathVariable String list,
    @PathVariable long RoomBooking_id,
    @PathVariable long DeviceType_id,
    @PathVariable long DeviceProblem_id)

    {
        //ของฟร้อง
        DeviceType type = deviceTypeRepository.findById(DeviceType_id);
        DeviceProblem problem = deviceProblemRepository.findById(DeviceProblem_id);

        newRepair.setList(list);
        newRepair.setType(type);
        newRepair.setProblem(problem);

        return repairRepository.save(newRepair);


    }

    


}