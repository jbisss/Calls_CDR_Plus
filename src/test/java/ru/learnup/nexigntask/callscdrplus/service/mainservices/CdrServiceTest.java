package ru.learnup.nexigntask.callscdrplus.service.mainservices;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootTest
public class CdrServiceTest {

    @Autowired
    private CdrService cdrService;

    /**
     * Длина номера телефона должна быть 11 символов
     */
    @Test
    public void testNumberGeneration() {
        Assertions.assertEquals(11, generateNumber().length());
    }

    private String generateNumber(){
        Method generateNumber;
        try {
            generateNumber = CdrService.class.getDeclaredMethod("generateNumber");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        generateNumber.setAccessible(true);
        String result;
        try {
            result = (String) generateNumber.invoke(cdrService);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
