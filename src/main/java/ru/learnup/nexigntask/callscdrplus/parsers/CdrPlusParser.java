package ru.learnup.nexigntask.callscdrplus.parsers;

import org.springframework.stereotype.Component;
import ru.learnup.nexigntask.callscdrplus.cache.SubscriberCache;
import ru.learnup.nexigntask.callscdrplus.parsers.contracts.Parser;
import ru.learnup.nexigntask.callscdrplus.pojo.callresults.Subscriber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CdrPlusParser implements Parser {

    private final SubscriberCache subscriberCache;
    private final File cdrPlusFile;

    public CdrPlusParser(SubscriberCache subscriberCache, File cdrPlusFile) {
        this.subscriberCache = subscriberCache;
        this.cdrPlusFile = cdrPlusFile;
    }

    /**
     * Парсит cdrPlus файл, заносит в кэш информацию об абонентах, представленныз в файле
     *
     * @throws IOException exception
     */
    @Override
    public void parseFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cdrPlusFile));
        String line = reader.readLine();
        while (line != null) {
            line = line.replaceAll(" ", "");
            String[] tokens = line.split(",");
            // если абонент существует в кэше - добавляем ему звонок
            // иначе создаём нового абонента и передаём звонок ему
            if (subscriberCache.getSubscribers().containsKey(tokens[1])) {
                subscriberCache.getSubscribers().get(tokens[1]).addCall(tokens[0], tokens[2], tokens[3]);
            } else {
                Subscriber sub = new Subscriber(tokens[1]);
                sub.addCall(tokens[0], tokens[2], tokens[3]);
                subscriberCache.getSubscribers().put(sub.getNumber(), sub);
            }
            line = reader.readLine();
        }
        reader.close();
    }
}
