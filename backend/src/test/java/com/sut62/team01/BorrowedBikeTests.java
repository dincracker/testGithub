package com.sut62.team01;

import com.sut62.team01.entity.*;
import com.sut62.team01.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BorrowedBikeTests {

    private Validator validator;

    @Autowired
    private BorrowedBikeRepository borrowedBikeRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;

    @Autowired
    private DateTypeRepository dateTypeRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private BranchesRepository branchesRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInsertDataToBorrowedBikeOK(){
//        จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup","B6000783","pontep","1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students,rooms,branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

//        สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
//        บันทึก borrowedBike
        borrowedBike = borrowedBikeRepository.saveAndFlush(borrowedBike);
//        เรียก borrowedBike ที่พึ่งบันทึกขึ้นมาดู
        Optional<BorrowedBike> found = borrowedBikeRepository.findById(borrowedBike.getId());
//        assert check
        assertEquals(borrowedBike,found.get());
    }

    @Test
    void testRequestDateMustNotBeNull(){
        //        จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup","B6000783","pontep","1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students,rooms,branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

//        สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
//        ทดลองให้ set requestDate เป็น null
        borrowedBike.setRequestDate(null);
//        validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
//        ต้องมี 1 Error
        assertEquals(1,result.size());
//        error message ตรงชนิด และ ถูก field
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("requestDate",result.iterator().next().getPropertyPath().toString());
    }
    @Test
    void testBikeTypeMustNotBeNull(){
//        จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup","B6000783","pontep","1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students,rooms,branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

//        สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
//        ทดลองให้ set BikeType เป็น null
        borrowedBike.setBikeType(null);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRoomBooking(roomBooking);
        borrowedBike.setRequestDate(new Date());
//        validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
//        ต้องมี 1 Error
        assertEquals(1,result.size());
//        error message ตรงชนิด และ ถูก field
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("bikeType",result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testRoomBookingMustNotBeNull(){
        //        จำลองข้อมูลที่จำเป็นในการบันทึก BorrowedBike
        BikeType bikeType = new BikeType("จักรยานล้อเดียว");
        bikeType = bikeTypeRepository.saveAndFlush(bikeType);
        DateType dateType = new DateType("ยืมแป๊ปเดียว");
        dateType = dateTypeRepository.saveAndFlush(dateType);
        Students students = new Students("Pontep Thaweesup","B6000783","pontep","1234");
        students = studentsRepository.saveAndFlush(students);
        Rooms rooms = new Rooms("7133");
        rooms = roomsRepository.saveAndFlush(rooms);
        Branches branches = new Branches("What's branch?");
        branches = branchesRepository.saveAndFlush(branches);
        RoomBooking roomBooking = new RoomBooking(students,rooms,branches);
        roomBooking = roomBookingRepository.saveAndFlush(roomBooking);

//        สร้าง borrowedBike และ set ค่าต่างๆ
        BorrowedBike borrowedBike = new BorrowedBike();
        borrowedBike.setBikeType(bikeType);
        borrowedBike.setDateType(dateType);
        borrowedBike.setRequestDate(new Date());
//        ทดลองให้ set RoomBooking เป็น null
        borrowedBike.setRoomBooking(null);

//        validate borrowedBike
        Set<ConstraintViolation<BorrowedBike>> result = validator.validate(borrowedBike);
//        ต้องมี 1 Error
        assertEquals(1,result.size());
//        error message ตรงชนิด และ ถูก field
        assertEquals("must not be null",result.iterator().next().getMessage());
        assertEquals("roomBooking",result.iterator().next().getPropertyPath().toString());
    }
}
