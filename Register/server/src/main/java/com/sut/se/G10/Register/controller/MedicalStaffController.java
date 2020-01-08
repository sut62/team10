package com.sut.se.G10.Register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sut.se.G10.Register.entity.*;
import com.sut.se.G10.Register.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
public class MedicalStaffController {

    @Autowired 
    private final MedicalStaffRepository medicalStaffRepository;
    @Autowired 
    private GenderRepository genderRepository;
    @Autowired 
    private PositionRepository  positionRepository;
    @Autowired 
    private ProvinceRepository provinceRepository;

    MedicalStaffController(MedicalStaffRepository medicalStaffRepository,
                            GenderRepository genderRepository,
                            PositionRepository  positionRepository,
                            ProvinceRepository provinceRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
        this.genderRepository = genderRepository;
        this.positionRepository  = positionRepository;
        this.provinceRepository = provinceRepository;
    }
    
    @GetMapping("/medicalstaff")
    public Collection<MedicalStaff> medicalStaffs() {
        return medicalStaffRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/medicalstaff/{gender_id}/{position_id}/{province_id}/{address}/{fullname}/{email}/{phone}/{password}/{bdate}")
    public MedicalStaff newMedicalStaff(  MedicalStaff newMedicalStaff, 
                                    @PathVariable long gender_id, 
                                    @PathVariable long position_id, 
                                    @PathVariable long province_id,
                                    @PathVariable String address,
                                    @PathVariable String fullname,
                                    @PathVariable String email,
                                    @PathVariable String phone,
                                    @PathVariable String bdate,
                                    @PathVariable String password){
                                        
        Gender gender = genderRepository.findById(gender_id);
        Position position = positionRepository.findById(position_id);
        Province province = provinceRepository.findById(province_id);
        
        newMedicalStaff.setGender(gender);
        newMedicalStaff.setPosition(position);
        newMedicalStaff.setProvince(province);
        newMedicalStaff.setAddress(address);
        newMedicalStaff.setFullname(fullname);
        newMedicalStaff.setEmail(email);
        newMedicalStaff.setPhone(phone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthdate = formatter.parse(bdate);
            newMedicalStaff.setBirthdate(birthdate);
        } catch (ParseException e) {
        }
        newMedicalStaff.setPassword(password);

        return medicalStaffRepository.save(newMedicalStaff);
    }
}

