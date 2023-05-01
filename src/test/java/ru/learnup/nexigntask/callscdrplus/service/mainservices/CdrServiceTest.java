package ru.learnup.nexigntask.callscdrplus.service.mainservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import ru.learnup.nexigntask.callscdrplus.enums.CallCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

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
        // длина номера должна быть 11 символов
        Assertions.assertEquals(11, generateNumber().length());
        // первая цифра в номере телефона обязательно 7
        Assertions.assertEquals("7", generateNumber().substring(0, 1));
        // каждый символ в номере является числом
        char[] number = generateNumber().toCharArray();
        for (char digit : number) {
            Assertions.assertTrue(Character.isDigit(digit));
        }
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
     * Генерация случайной даты
     */
    @Test
    public void testGenerateRandomDate() {
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 30, 1, 1, 1);
        LocalDateTime endDate = LocalDateTime.of(2020, 7, 30, 1,1,1);
        LocalDateTime middleDate = generateRandomDateCdr(startDate, endDate);
        Assertions.assertTrue(middleDate.isBefore(endDate));
        Assertions.assertTrue(middleDate.isAfter(startDate));
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

    /**
     * Метод генерации случайной датой между начальной и конечной
     *
     * @param startDate начальная дата
     * @param endDate конечная дата
     * @return случайная дата между начальной и конечной
     */
    private LocalDateTime generateRandomDateCdr(LocalDateTime startDate, LocalDateTime endDate) {
        Method generateRandomDate;
        try {
            generateRandomDate = CdrService.class.getDeclaredMethod("generateRandomDate", LocalDateTime.class, LocalDateTime.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        generateRandomDate.setAccessible(true);
        LocalDateTime result;
        try {
            result = (LocalDateTime) generateRandomDate.invoke(cdrService, startDate, endDate);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
