package ru.learnup.nexigntask.callscdrplus.parsers;

import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.parsers.contracts.Parser;

import java.io.*;

@Component
public class CdrParser implements Parser {

    private final File cdrPlusFile;
    private final SubscriberCache subscriberCache;

    public CdrParser(File cdrPlusFile, SubscriberCache subscriberCache) {
        this.cdrPlusFile = cdrPlusFile;
        this.subscriberCache = subscriberCache;
    }

    /**
     * Парсит cdr файл, записывая информацию об абонентах в cdrPlus файл -> сdr + тариф
     *
     * @param file CdrFile
     * @throws IOException exception
     */
    @Override
    public void parseFile(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(cdrPlusFile));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            StringBuilder lineBuilder = new StringBuilder(line);
            String[] tokens = line.split(",");
            String number = tokens[1];
            if (subscriberCache.getCachedNumberTariff().containsKey(number)) {
                lineBuilder.append(",").append(subscriberCache.getCachedNumberTariff().get(number).getTariffId()).append("\n");
                writer.write(lineBuilder.toString());
            }
            line = reader.readLine();
        }
        writer.close();
        reader.close();
    }
}
