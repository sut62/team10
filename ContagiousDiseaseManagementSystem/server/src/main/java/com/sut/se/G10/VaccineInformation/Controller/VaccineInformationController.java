package com.sut.se.G10.VaccineInformation.Controller;

// import lombok.*;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import java.io.IOException;
// import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
// import java.util.List;
// import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.net.URLDecoder;

import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;
import com.sut.se.G10.VaccineInformation.Entity.*;
import com.sut.se.G10.VaccineInformation.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class VaccineInformationController {
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private VaccineInformationRepository vaccineinformationRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private TypeVaccineRepository typevaccineRepository;

    public VaccineInformationController(VaccineInformationRepository vaccineinformationRepository,
            MedicalStaffRepository medicalStaffRepository, VaccineRepository vaccineRepository,
            TypeVaccineRepository typevaccineRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
        this.vaccineinformationRepository = vaccineinformationRepository;
        this.vaccineRepository = vaccineRepository;
        this.typevaccineRepository = typevaccineRepository;

    }

    @GetMapping("/vaccineinformation")
    public Collection<VaccineInformation> vaccineinformation() {
        return vaccineinformationRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/vaccineinformation/{id}")
    public Optional<VaccineInformation> vaccineinformation(@PathVariable Long id) {
        Optional<VaccineInformation> vaccineinformation = vaccineinformationRepository.findById(id);
        return vaccineinformation;
    }
    
    @GetMapping("/vaccine")
    public Collection<Vaccine> vaccines() {
        return vaccineRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/typevaccine")
    public Collection<TypeVaccine> typeVaccines() {
        return typevaccineRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/vaccineinformation/{id}")
    public ResponseEntity<String> deleteVaccineInformation(@PathVariable("id") long id) {
        System.out.println("Delete VaccineInformation with ID = " + id + "...");

        vaccineinformationRepository.deleteById(id);

        return new ResponseEntity<>("VaccineInformation has been deleted!", HttpStatus.OK);
    }

    @PostMapping("/vaccineinformation/{fullname}/{vaccineid}/{typevaccineid}/{strdate}/{expdate}")
    public VaccineInformation newVaccineInformation(VaccineInformation newVaccineInformation,
            @PathVariable Long fullname, // edit
            @PathVariable Long vaccineid, @PathVariable Long typevaccineid, @PathVariable String strdate,
            @PathVariable String expdate) throws ParseException {

        MedicalStaff medicalStaff = medicalStaffRepository.findById(fullname).get();
        Vaccine vaccine = vaccineRepository.findByvaccineid(vaccineid);
        TypeVaccine typevaccine = typevaccineRepository.findBytypevaccineid(typevaccineid);

        DateFormat newstrdate = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = newstrdate.parse(strdate);

        DateFormat newexpdate = new SimpleDateFormat("yyyy-MM-dd");
        Date ed = newexpdate.parse(expdate);

       
        newVaccineInformation.setFullname(medicalStaff); // edit
        newVaccineInformation.setVaccineid(vaccine);
        newVaccineInformation.setTypevaccineid(typevaccine);
        newVaccineInformation.setStoragedate(sd);
        newVaccineInformation.setExpiredate(ed);
        return vaccineinformationRepository.save(newVaccineInformation);

    }

}
