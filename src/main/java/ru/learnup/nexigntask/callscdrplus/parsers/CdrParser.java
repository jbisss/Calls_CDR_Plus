package ru.learnup.nexigntask.callscdrplus.parsers;

import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.parsers.contracts.Parser;
import ru.learnup.nexigntask.callscdrplus.pojoclasses.dbresults.NumberTariff;
import ru.learnup.nexigntask.callscdrplus.service.RomashkaService;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CdrParser implements Parser {

    private final RomashkaService romashkaService;

    public CdrParser(RomashkaService romashkaService) {
        this.romashkaService = romashkaService;
    }

    public void parseFile(File file) throws IOException {
        File cdrPlus = new File(".\\src\\main\\resources\\static\\cdrplus.txt");

        List<NumberTariff> numberTariffs = romashkaService.getPositive();
        Map<String, Tariff> numberTariffMap = numberTariffs.stream()
                .collect(Collectors.toMap(NumberTariff::getNumber, NumberTariff::getTariffId));

        BufferedWriter writer = new BufferedWriter(new FileWriter(cdrPlus));
        //
        //
        // наладить создание и работу с файлами!
        //
        //
        int i = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            StringBuilder lineBuilder = new StringBuilder(line);
            String[] tokens = line.split(",");
            String number = tokens[1];

            if(numberTariffMap.containsKey(number)) {
                lineBuilder.append(",").append(numberTariffMap.get(number).getTariffId()).append("\n");

                writer.write(lineBuilder.toString());
            }
            line = reader.readLine();
            i++;
        }
        reader.close();
        writer.close();
    }
}
