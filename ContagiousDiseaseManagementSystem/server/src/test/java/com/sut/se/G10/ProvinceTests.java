package com.sut.se.G10;

import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProvinceTests {

    private Validator validator;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5913480_testProvinceAllCorrect() {
        Province province = new Province();
        province.setProvince("กรุงเทพมหานคร");

        province =  provinceRepository.saveAndFlush(province);

        Optional<Province> found = provinceRepository.findById(province.getId());
        assertEquals("กรุงเทพมหานคร", found.get().getProvince());
    }

    @Test
    void b5913480_testProvinceNotNull() {
        Province province = new Province();
        province.setProvince(null);

        Set<ConstraintViolation<Province>> result = validator.validate(province);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());
    }

}