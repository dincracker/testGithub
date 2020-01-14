package com.sut62.team01;

import java.util.Collections;
import java.util.stream.Stream;

import com.sut62.team01.entity.BikeType;
import com.sut62.team01.entity.Branches;
import com.sut62.team01.entity.DateType;
import com.sut62.team01.entity.ElectricType;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Rooms;
import com.sut62.team01.entity.Staff;
import com.sut62.team01.entity.Students;
import com.sut62.team01.entity.VehicleType;
import com.sut62.team01.entity.PackageType;
import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.repository.DeviceNameRepository;
import com.sut62.team01.repository.DeviceTypeRepository;
import com.sut62.team01.repository.BikeTypeRepository;
import com.sut62.team01.repository.BorrowedBikeRepository;
import com.sut62.team01.repository.BranchesRepository;
import com.sut62.team01.repository.DateTypeRepository;
import com.sut62.team01.repository.ElectricTypeRepository;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RoomsRepository;
import com.sut62.team01.repository.StaffRepository;
import com.sut62.team01.repository.StudentsRepository;
import com.sut62.team01.repository.VehicleTypeRepository;
import com.sut62.team01.repository.PackageTypeRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class SUT62TEAM01 {

    public static void main(String[] args) {
        SpringApplication.run(SUT62TEAM01.class, args);
    }

    @Bean
    ApplicationRunner init(BorrowedBikeRepository borrowedBikeRepository, BikeTypeRepository bikeTypeRepository,
            DateTypeRepository dateTypeRepository, RoomBookingRepository roomBookingRepository,
            StudentsRepository studentsRepository, BranchesRepository branchesRepository,
            RoomsRepository roomsRepository, StaffRepository staffRepository,
            VehicleTypeRepository vehicleTypeRepository, ElectricTypeRepository electrictypeRepository,
            PackageTypeRepository packageTypeRepository,DeviceTypeRepository deviceTypeRepository, 
            DeviceNameRepository deviceNameRepository) {
          
        return args -> {

            // TODO: Inject test data
            // BikeType
            BikeType bikeType1 = new BikeType("จักรยาน 2 ล้อ สำหรับคนเดียว");
            BikeType bikeType2 = new BikeType("จักรยาน 2 ล้อ สำหรับสองคน");
            BikeType bikeType3 = new BikeType("จักรยาน 3 ล้อ แบบมีหลังคา");
            BikeType bikeType4 = new BikeType("จักรยาน 3 ล้อ แบบไม่มีหลังคา");
            Stream.of(bikeType1, bikeType2, bikeType3, bikeType4).forEach(bikeType -> {
                bikeTypeRepository.save(bikeType);
                System.out.println(bikeType);
            });

            // DateType
            DateType dateType1 = new DateType("ยืม 3 วัน");
            DateType dateType2 = new DateType("ยืม 7 วัน");
            DateType dateType3 = new DateType("ยืม 1 ภาคการศึกษา");
            Stream.of(dateType1, dateType2, dateType3).forEach(dateType -> {
                dateTypeRepository.save(dateType);
                System.out.println(dateType);
            });

            // Students
            Students student1 = new Students("นายพรเทพ ทวีทรัพย์", "b6000783", "pontep", "1234");
            Students student2 = new Students("นายเจตุพนน์ ศรีภูธร", "b6000123", "jayza", "1234");
            Students student3 = new Students("นายภูมิรินทร์ เพียวสูงเนิน", "b6003296", "leo2541", "1234");
            Stream.of(student1, student2, student3).forEach(student -> {
                studentsRepository.save(student);
                System.out.println(student);
            });

            Branches branches1 = new Branches("สำนักวิชาวิทยาศาสตร์");
			Branches branches2 = new Branches("สำนักวิชาเทคโนโลยีสังคม");
			Branches branches3 = new Branches("สำนักวิชาเทคโนโลยีการเกษตร");
			Branches branches4 = new Branches("สำนักวิชาวิศวกรรมศาสตร์ ");
			Branches branches5 = new Branches("สำนักวิชาแพทยศาสตร์");
			Branches branches6 = new Branches("สำนักวิชาพยาบาลศาสตร์");
			Branches branches7 = new Branches("สำนักวิชาทันตแพทยศาสตร์");
			Branches branches8 = new Branches("สำนักวิชาสาธารณสุขศาสตร์");
			Stream.of(branches1,branches2,branches3,branches4,branches5,branches6,branches7,branches8).forEach(bra -> {
				branchesRepository.save(bra); 
			});
            // Room
            for (int i = 1; i <= 40; i++) {
                Rooms room = new Rooms("71" + (i < 10 ? "0" + i : i));
                roomsRepository.save(room);
            }
            Rooms room7133 = roomsRepository.findByRoomId("7133");

            // RoomBooking
            RoomBooking roomBooking1 = new RoomBooking(student1, room7133, branches1);
            roomBookingRepository.save(roomBooking1);
                      
            //ElectricType-LEO
			ElectricType electricType1 = new ElectricType("พัดลม");
			ElectricType electricType2 = new ElectricType("เต้ารีด");
			ElectricType electricType3 = new ElectricType("กะทะไฟฟ้า");
			ElectricType electricType4 = new ElectricType("คอม");
			Stream.of(electricType1,electricType2,electricType3,electricType4).forEach(electricType -> {
				electrictypeRepository.save(electricType);
            });

	    //PackageType
            PackageType pt1 = new PackageType("ซองจดหมาย");
            PackageType pt2 = new PackageType("ซองเอกสาร");
            PackageType pt3 = new PackageType("ซองพลาสติก");
            PackageType pt4 = new PackageType("กล่องพัสดุ");
            Stream.of(pt1, pt2, pt3, pt4).forEach(pt -> {
                packageTypeRepository.save(pt);
                System.out.println(pt);
            });

            // STAFF
            Staff staff1 = new Staff("Chanwit Keawkasi", "chanwit", "62");
            Staff staff2 = new Staff("Nutthawut Sunthornrot", "black", "asdasd");
            Staff staff3 = new Staff("Apple Apyoon", "apyoon", "1234");

            Stream.of(staff1, staff2, staff3).forEach(staff -> {
                staffRepository.save(staff);
                System.out.println(staff);
            });

            Stream.of("รถจักรยานยนต์", "รถยนต์", "จักรยาน").forEach(type -> {
				VehicleType	vehicleType = new VehicleType(); // สร้าง Object Customer
				vehicleType.setType(type); // set ชื่อ (name) ให้ Object ชื่อ Customer
				vehicleTypeRepository.save(vehicleType); // บันทึก Objcet ชื่อ Customer
            });
            
            //Repair
            DeviceType deviceType1 = new DeviceType("อุปกรณ์ไฟฟ้า");
			DeviceType deviceType2 = new DeviceType("อุปกรณ์ประปา");
			DeviceType deviceType3 = new DeviceType("คุรุภัณฑ์");
			DeviceType deviceType4 = new DeviceType("อื่นๆ");
			Stream.of(deviceType1,deviceType2,deviceType3,deviceType4).forEach(deviceType -> {
				deviceTypeRepository.save(deviceType); // บันทึก Objcet ชื่อ Customer
			});
			
			DeviceName deviceName1 = new DeviceName("หลอดไฟ",deviceType1 );
			DeviceName deviceName2 = new DeviceName("โคมไฟ",deviceType1 );
			DeviceName deviceName3 = new DeviceName("พัดลม",deviceType1);
			DeviceName deviceName4 = new DeviceName("สวิตซ์ เปิด-ปิดไฟ",deviceType1);
			DeviceName deviceName5 = new DeviceName("สายไฟ",deviceType1);
			
			DeviceName deviceName6 = new DeviceName("ท่อน้ำ",deviceType2);
			DeviceName deviceName7 = new DeviceName("ก็อกน้ำอ่างล่างหน้า",deviceType2);
            DeviceName deviceName8 = new DeviceName("ท่อน้ำทิ้ง",deviceType2);
            DeviceName deviceName9 = new DeviceName("ชักโครก",deviceType2);

			DeviceName deviceName10 = new  DeviceName("มุ้งลวด",deviceType3);
			DeviceName deviceName11 = new DeviceName("เตียง",deviceType3);
			DeviceName deviceName12 = new DeviceName("เพดาน",deviceType3);
			DeviceName deviceName13 = new DeviceName("ลูกบิดประตู",deviceType3);
			DeviceName deviceName14 = new DeviceName("ประตูห้องพัก",deviceType3);
			DeviceName deviceName15 = new DeviceName("บานเกล็ด",deviceType3);
			

			DeviceName deviceName16 = new DeviceName("อื่นๆ (โปรดระบุอุปกรณ์ที่เสียในช่องระบุอาการ/ปัญหาที่เกิดขึ้น)",deviceType4);
			Stream.of(deviceName1,deviceName2,deviceName3,deviceName4,deviceName5,
			deviceName6,deviceName7,deviceName8,deviceName9,deviceName10,
			deviceName11,deviceName12,deviceName13,deviceName14,deviceName15,deviceName16).forEach(deviceName -> {
				deviceNameRepository.save(deviceName); // บันทึก Objcet ชื่อ Customer
			});
    };
}


    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
