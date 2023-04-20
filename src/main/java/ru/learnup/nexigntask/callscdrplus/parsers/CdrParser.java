package ru.learnup.nexigntask.callscdrplus.parsers;

import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.entity.Tariff;
import ru.learnup.nexigntask.callscdrplus.parsers.contracts.Parser;
import ru.learnup.nexigntask.callscdrplus.servicedb.RomashkaService;

import java.io.*;
import java.util.Map;

@Component
public class CdrParser implements Parser {

    private final RomashkaService romashkaService;

    public CdrParser(RomashkaService romashkaService) {
        this.romashkaService = romashkaService;
    }

    public void parseFile(File file) throws IOException {
        File cdrPlus = new File(".\\src\\main\\resources\\static\\cdrplus.txt");

        Map<String, Tariff> numberTariffMap = romashkaService.getPositive();

        BufferedWriter writer = new BufferedWriter(new FileWriter(cdrPlus));
        //
        //
        // наладить создание и работу с файлами!
        //
        //
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
        }
        reader.close();
        writer.close();
    }
}
