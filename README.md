# Calls_CDR_Plus

## База данных - RomashkaDB

---

Для работы приложения необходима база данных, для её локального восстановления я приложил бэкапы [в этой папке](https://github.com/jbisss/Calls_CDR_Plus/tree/master/RomashkaBackups).

База данных для данной программы представлена тремя сущностями: clients, tariff, call_cost. [Здесь](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/entity) соответствующие этим сущностям Java-классы.

Структура таблиц следующая:

**clients:**

- id (PK) - integer - id клиента
- phone_number - character varying(11) - номер телефона
- balance - numeric(7,1) - баланс
- benefit_minutes_left - integer - остаток льготных минут
- tariff_id (FK) - character varying(2) - тариф клиента

**tariff:**

- tariff_id (PK) - character varying(2) - id тарифа
- tariff_name - character varying(20) - название тарифа
- subscription_fee - integer - абонентская плата
- benefit_minutes - smallint - льготные минуты
- in_call_cost_id - smallint - тип входящего звонка
- out_call_cost_id - smallint - тип исходящего звонка

**call_cost:**

- id (PK) - smallint - id типа звонка
- default_minute_cost - numeric(3,1) - стоимость обычной минуты
- benefit_minute_cost - numeric(3,1) - стоимость льготной минуты

Пользуясь такого рода связями можно добавлять новые тарифы со своими условиями, подходящими по таким параметрам.

**Отсутствие льготных минут/абонентской платы помечается в базе данных как 0, а не null.**

---

## Java-программа

---

Пакетная структура программы:

- [ru.learnup.nexigntask.callscdrplus](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus)

  - [aspect](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/aspect)
  
    - [*class TimeMeasurer.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/aspect/TimeMeasurer.java)

  - [cache](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/cache)

    - [*class SubscriberCache.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/cache/SubscriberCache.java)

  - [configs](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/configs)

    - [*class FileConfig.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/configs/FileConfig.java)

  - [controllers](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/controllers)

    - [*class NumberController.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/controllers/NumberController.java)

  - [dao](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dao)

    - [*class NumberTariff.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dao/NumberTariff.java)

  - [dto](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto)

    - [addbalance](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/addbalance)

      - [*class AddBalanceRequestDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/addbalance/AddBalanceRequestDto.java)
      - [*class AddBalanceResponseDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/addbalance/AddBalanceResponseDto.java)

    - [changeTariff](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/changetariff)

      - [*class ChangeTariffRequestDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/changetariff/ChangeTariffRequestDto.java)
      - [*class ChangeTariffResponseDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/changetariff/ChangeTariffResponseDto.java)

    - [charge](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/charge)

      - [*class ChargeNumberBalanceDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/charge/ChargeNumberBalanceDto.java)
      - [*class ChargeRequestDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/charge/ChargeRequestDto.java)
      - [*class ChargeResponseDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/charge/ChargeResponseDto.java)

    - [getnumberdetails](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/getnumberdetails)

      - [*class Call.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/getnumberdetails/Call.java)
      - [*class Subscriber.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/getnumberdetails/Subscriber.java)

    - [newabonent](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/newabonent)

      - [*class NewAbonentRequestResponseDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/newabonent/NewAbonentRequestResponseDto.java)

    - [numberdetail](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/numberdetail)

      - [*class NumberDetailResponseDto.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/dto/numberdetail/NumberDetailResponseDto.java)

  - [entity](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/entity)
  
    - [*class CallCost.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/entity/CallCost.java)
    - [*class Client.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/entity/Client.java)
    - [*class Tariff.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/entity/Tariff.java)

  - [enums](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/enums)

    - [*enum CallCode.java*](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/enums)

  - [parsers](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/parsers)

    - [contracts](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/parsers/contracts)

      - [*interface Parser.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/parsers/contracts/Parser.java)
  
    - [*class CdrParser.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/parsers/CdrParser.java)
    - [*class CdrPlusParser.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/parsers/CdrPlusParser.java)

  - [repository](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/repository)

    - [*interface ClientRepository.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/repository/ClientRepository.java)
    - [*interface TariffRepository.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/repository/TariffRepository.java)

  - [security](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/security)

    - [*class SecurityConfig.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/security/SecurityConfig.java)

  - [service](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service)

    - [mainserveces](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices)

      - [*class BillingServie.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices/BillingService.java)
      - [*class BrtService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices/BrtService.java)
      - [*class CacheService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices/CacheService.java)
      - [*class CdrService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices/CdrService.java)
      - [*class HrsService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/mainservices/HrsService.java)

    - [repservices](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/repservices)

      - [*class ClientService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/repservices/ClientService.java)
      - [*class TariffService.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/service/repservices/TariffService.java)

  - [*class CallsCdrPlusApplication.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/CallsCdrPlusApplication.java)

---
