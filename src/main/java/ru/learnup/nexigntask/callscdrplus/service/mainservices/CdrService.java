package ru.learnup.nexigntask.callscdrplus.service.mainservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.enums.CallCode;
import ru.learnup.nexigntask.callscdrplus.dto.getnumberdetails.Call;
import ru.learnup.nexigntask.callscdrplus.service.repservices.ClientService;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;

@Service
@Slf4j
public class CdrService {

    private final ClientService clientService;
    private final Map<String, Queue<Call>> generatedCalls;
    private final File cdrFile;

    public CdrService(ClientService clientService, File cdrFile) {
        this.clientService = clientService;
        this.cdrFile = cdrFile;
        generatedCalls = new HashMap<>();
    }

    /**
     * Executes CDR Service.
     * Итогом работы данного сервиса является сгенерированный CDR файл.
     */
    public void execute() {
        generateCalls();
        generateCdr();
    }

    /**
     * Генерация cdr файла
     */
    private void generateCdr() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cdrFile, false))) {
            writeRecords(writer);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written!");
        }
    }

    /**
     * Звонки формируются следующим образом:
     * множество звонков для каждого номера хранятся в виде очереди
     * (то есть в мапе ключ - номер, значение - очередь звонков),
     * производится проход по всем ключам, вынимая по одному звонку у каждого номера,
     * когда звонки по номеру заканчиваются - убираем из мапы пару ключ-значение по
     * этому номеру.
     * Это нужно для того, чтобы сделать вид файла разнообразным (как будто
     * звонки максимально случайны)
     *
     * @param writer записывает в файл
     * @throws IOException для BufferedWriter
     */
    private void writeRecords(BufferedWriter writer) throws IOException {
        while (!generatedCalls.isEmpty()) {
            Call tempCall;
            for (String number : generatedCalls.keySet()) {
                tempCall = generatedCalls.get(number).remove();
                assert tempCall != null;
                String record = makeCdrRecord(number, tempCall);
                writer.write(record);
                if (generatedCalls.get(number).isEmpty()) {
                    generatedCalls.remove(number);
                    break;
                }
            }
        }
    }

    /**
     * Создаёт запись звонка (номер + инфорайия о звонке)
     *
     * @param number номер телефона
     * @param call   звонок
     * @return строка - запись номер с информацией о звонке
     */
    private String makeCdrRecord(String number, Call call) {
        return call.getCallCode().getCode() +
                "," +
                number +
                "," +
                call.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "," +
                call.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "\n";
    }

    /**
     * Случайно генерируем звонки либо для номера из базы данных, либо для сгенерированного номера
     * Таким образом в отчётный период, выбираем не все номера из базы данных
     */
    private void generateCalls() {
        // случайно генерируем номер
        // если его нет в базе, то добавляем его к списку "прочих" номеров
        // (чтобы случайно не сгенерировать номер, представленный в базе)
        Set<String> romashkaNumbers = clientService.getNumbers();
        List<String> otherNumber = new ArrayList<>();
        for (int i = 0; i < romashkaNumbers.size(); i++) {
            String newNumber = generateNumber();
            if (!romashkaNumbers.contains(newNumber)) {
                otherNumber.add(newNumber);
            }
        }
        // если flag == 1 - генерируем звонки для номеров из базы
        // если flag == 2 - генерируем звонки для случайно сгенерированного номера
        int flag;
        int randomCallsAmount;
        int j = 0;
        for (String number : romashkaNumbers) {
            flag = new Random().nextInt(3);
            // случайно выбираем количество звонков
            randomCallsAmount = 3 + new Random().nextInt(12);
            if (flag == 1) {
                generatedCalls.put(number, new LinkedList<>());
                for (int i = 0; i < randomCallsAmount; i++) {
                    generateCall(number);
                }
            } else if (flag == 2) {
                generatedCalls.put(otherNumber.get(j), new LinkedList<>());
                for (int i = 0; i < randomCallsAmount; i++) {
                    generateCall(otherNumber.get(j));
                }
            }
            j++;
        }
    }

    /**
     * Из уже имеющихся звонков выбираем максимальную дату окончания звонка, чтобы
     * на её основе определить дату начала нового звонка (чтобы звонки не могли пересекаться).
     * Если звонков ещё нет формируем рандомную дату звонка
     *
     * @param number номер телефона, для которого формируем звонок
     */
    private void generateCall(String number) {
        Queue<Call> calls = generatedCalls.get(number);
        // выбираем максимальную дату окончания
        LocalDateTime maxEndTime = calls.stream()
                .map(Call::getEndDateTime)
                .max(LocalDateTime::compareTo)
                .orElseGet(() -> generateRandomDate(
                        LocalDateTime.of(2022, 1, 1, 1, 1, 1),
                        LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                );
        // выбираем текущую дату, вычитая 1 день
        LocalDateTime present = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        // если текущая дата оказалось меньше максимальной даты окончания,
        // то насильно делаем её больше
        if (maxEndTime.compareTo(present) >= 0) {
            present = maxEndTime.plus(1, ChronoUnit.HOURS);
        }
        // генерируем дату начала и конца звонка
        LocalDateTime startCallDate = generateRandomDate(maxEndTime, present);
        LocalDateTime startCallDateDop = startCallDate.plus(1, ChronoUnit.HOURS);
        LocalDateTime endCallDate = generateRandomDate(startCallDate, startCallDateDop);
        generatedCalls.get(number).add(new Call(generateCallType(), startCallDate, endCallDate));
    }

    /**
     * Генерирует рандомный номер телефона, начинающийся на 7 и состоящий из 11 символов
     *
     * @return номер телефона
     */
    private String generateNumber() {
        StringBuilder number = new StringBuilder("7");
        int digit;
        for (int i = 0; i < 10; i++) {
            digit = (int) (Math.random() * 10);
            number.append(digit);
        }
        return number.toString();
    }

    /**
     * Рандомно генерируем тип звонка
     *
     * @return тип звонка
     */
    private CallCode generateCallType() {
        int code = (int) (Math.random() * 2);
        if (code == 0) {
            return CallCode.CALL_OUT;
        } else {
            return CallCode.CALL_IN;
        }
    }

    /**
     * Создаёт рандомную дату в диапозоне от startTime до endTime
     *
     * @param startTime начальное время
     * @param endTime   конечное время
     * @return случайная дата в диапазоне от startTime до endTime
     */
    private LocalDateTime generateRandomDate(LocalDateTime startTime, LocalDateTime endTime) {
        Random random = new Random();
        long startEpochSecond = startTime.toEpochSecond(ZoneOffset.UTC);
        long endEpochSecond = endTime.toEpochSecond(ZoneOffset.UTC);
        long randomEpochSecond = startEpochSecond + random.nextLong((Math.abs(endEpochSecond - startEpochSecond)) + 1);
        Instant randomInstant = Instant.ofEpochSecond(randomEpochSecond);
        return LocalDateTime.ofInstant(randomInstant, ZoneOffset.UTC);
    }
}
