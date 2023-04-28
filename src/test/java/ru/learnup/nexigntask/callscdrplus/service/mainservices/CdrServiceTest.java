package ru.learnup.nexigntask.callscdrplus.service.mainservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import ru.learnup.nexigntask.callscdrplus.enums.CallCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    /**
     * Генерация типа звонка
     */
    @Test
    public void testCallCodeGeneration() {
        CallCode resultCallCode = generateCallCode();
        assertThat(resultCallCode, anyOf(is(CallCode.CALL_IN), is(CallCode.CALL_OUT)));
    }

    /**
     * Возвращает тип вызова (входящий, исходящий), случайно генерирующийся
     * методом CdrService
     *
     * @return call type
     */
    private CallCode generateCallCode() {
        Method generateCallCode;
        try {
            generateCallCode = CdrService.class.getDeclaredMethod("generateCallType");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        generateCallCode.setAccessible(true);
        CallCode callCode;
        try {
            callCode = (CallCode) generateCallCode.invoke(cdrService);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return callCode;
    }

    /**
     * Возвращает номер телефона (входящий, исходящий), случайно генерирующийся
     * методом CdrService
     *
     * @return phone number
     */
    private String generateNumber() {
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
