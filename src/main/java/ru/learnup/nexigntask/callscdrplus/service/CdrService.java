package ru.learnup.nexigntask.callscdrplus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.pojo.enums.CallCode;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Call;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class CdrService {

    private final RomashkaService romashkaService;
    private final Map<String, Queue<Call>> generatedCalls;
    private final File cdrFile;

    public CdrService(RomashkaService romashkaService, File cdrFile) {
        this.romashkaService = romashkaService;
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

    private void generateCdr() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cdrFile, false))) {
            writeRecords(writer);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written!");
        }
    }

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

    private void generateCalls() {
        Set<String> romashkaNumbers = romashkaService.getNumbers();
        List<String> otherNumber = new ArrayList<>();
        for (int i = 0; i < romashkaNumbers.size(); i++) {
            String newNumber = generateNumber();
            if (!romashkaNumbers.contains(newNumber)) {
                otherNumber.add(newNumber);
            }
        }
        int flag;
        int randomCallsAmount;
        int j = 0;
        for (String number : romashkaNumbers) {
            flag = new Random().nextInt(3);
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

    private void generateCall(String number) {
        Queue<Call> calls = generatedCalls.get(number);
        Optional<LocalDateTime> maxEndTimeOptional = calls.stream()
                .map(Call::getEndDateTime)
                .max(LocalDateTime::compareTo);
        LocalDateTime maxEndTime;
        maxEndTime = maxEndTimeOptional
                .orElseGet(() -> generateRandomDate(LocalDateTime.of(2022, 1, 1, 1, 1, 1)
                                , LocalDateTime.of(2023, 1, 1, 1, 1, 1)));
        LocalDateTime present = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        if(maxEndTime.compareTo(present) > 0) {
            present = maxEndTime.plus(1, ChronoUnit.HOURS);
        }
        LocalDateTime startCallDate = generateRandomDate(maxEndTime, present);
        LocalDateTime startCallDateDop = startCallDate.plus(1, ChronoUnit.HOURS);
        LocalDateTime endCallDate = generateRandomDate(startCallDate, startCallDateDop);
        generatedCalls.get(number).add(new Call(generateCallType(), startCallDate, endCallDate));
    }

    private String generateNumber() {
        StringBuilder number = new StringBuilder("7");
        int digit;
        for (int i = 0; i < 10; i++) {
            digit = (int) (Math.random() * 10);
            number.append(digit);
        }
        return number.toString();
    }

    private CallCode generateCallType() {
        int code = (int) (Math.random() * 2);
        if (code == 0) {
            return CallCode.CALL_OUT;
        } else {
            return CallCode.CALL_IN;
        }
    }

    private LocalDateTime generateRandomDate(LocalDateTime startTime, LocalDateTime endTime) {
        Random random = new Random();
        long startEpochSecond = startTime.toEpochSecond(ZoneOffset.UTC);
        long endEpochSecond = endTime.toEpochSecond(ZoneOffset.UTC);
        long randomEpochSecond = startEpochSecond + random.nextLong((endEpochSecond - startEpochSecond));
        Instant randomInstant = Instant.ofEpochSecond(randomEpochSecond);
        return LocalDateTime.ofInstant(randomInstant, ZoneOffset.UTC);
    }
}
