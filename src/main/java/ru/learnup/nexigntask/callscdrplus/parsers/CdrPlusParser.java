package ru.learnup.nexigntask.callscdrplus.parsers;

import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.enums.Tariff;
import ru.learnup.nexigntask.callscdrplus.parsers.contracts.Parser;
import ru.learnup.nexigntask.callscdrplus.pojoclasses.callresults.Subscriber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CdrPlusParser implements Parser {

    public void parseFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        while (line != null) {
            line = line.replaceAll(" ", "");
            String[] tokens = line.split(",");
            if (Subscriber.subscribers.containsKey(tokens[1])) {
                Subscriber.subscribers.get(tokens[1]).addCall(tokens[0], tokens[2], tokens[3]);
            } else {
                Subscriber sub = new Subscriber(tokens[1], Tariff.getTariffByCode(tokens[4]));
                sub.addCall(tokens[0], tokens[2], tokens[3]);
                Subscriber.subscribers.put(sub.getNumber(), sub);
            }
            line = reader.readLine();
        }
        for (String key : Subscriber.subscribers.keySet()) {
            Subscriber.subscribers.get(key).countCallsCost();
        }

        reader.close();
    }
}
