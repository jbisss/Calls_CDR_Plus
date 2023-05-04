# Calls_CDR_Plus

## База данных - RomashkaDB

---

Контейнер с сервером на DockerHub [тут](https://hub.docker.com/repository/docker/jbisss/calls-cdr-plus/general).

Swagger-документация в формате [yaml](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/resources/swagger.yaml). Для её отображения можно использовать [Swagger Editor](https://editor.swagger.io/).

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

**Пакетная структура программы:**

- [ru.learnup.nexigntask.callscdrplus](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus)

  - [aspect](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/aspect)
  
    - [*class TimeMeasurer.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/aspect/TimeMeasurer.java)

  - [cache](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/cache)

    - [*class SubscriberCache.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/cache/SubscriberCache.java)

  - [configs](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/configs)

    - [*class FileConfig.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/configs/FileConfig.java)

  - [controllers](https://github.com/jbisss/Calls_CDR_Plus/tree/master/src/main/java/ru/learnup/nexigntask/callscdrplus/controllers)

    - [*class CrmController.java*](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/java/ru/learnup/nexigntask/callscdrplus/controllers/CrmController.java)

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

**Работа программы:**

Примеры [cdr](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/resources/static/cdr.txt) и [cdr+](https://github.com/jbisss/Calls_CDR_Plus/blob/master/src/main/resources/static/cdrplus.txt) файлов.

Основные сервисы в программе - это CdrService, BrtService, HrsService и BillingService. Последний при запуске запускает три первые (cdr, brt, hrs).

CdrService - генерирует файл cdr с типом звонка номером телефона, датой начала и окончания звонка. Принципы генерации описаны в java-файле этого сервиса.

BrtService - создаёт cdrPlus-файл на основе cdr и данных из БД. Также сохраняет данные в БД после того, как Hrs произведёт тарификацию.

HrsService - тарифицирует абонентов на основе cdrPlus-файла, создаваемого Brt. После окончания работы Brt сервис заносит данные о клиентах в БД (снимает деньги на сумму, на которую пользователь наговорил за отчётный период).

**http-запросы к серверу:**

Запросы к серверу я производил с помощью Postman'a, поэтому в настройках безопасности у меня отключена форма для входа.

Аунтефикация производилась в постмане через спецаильную вкладку, предназначенную для этого.

В программе присутствуют две роли *ABONENT* и *MANAGER*, **'логин'** **'пароль'** для этих ролей - **'abonent' 'abonent'** и **'manager' 'manager'** соответственно.

Примеры запросов обрабатываемых контроллером:

**Add abonent:**

POST - запрос - <http://localhost:7777/manager/abonent>

Request body

```JSON
{
    "numberPhone": "71231233214",
    "tariffId": "11",
    "balance": 10000
}
```

Response body

```JSON
{
    "numberPhone": "71231233214",
    "tariffId": "11",
    "balance": 10000
}
```

**Billing:**

PATCH - запрос - <http://localhost:7777/manager/billing>

Request body

```JSON
{
    "action": "run"
}
```

Response body

```JSON
{
    "numbers": [
        {
            "phoneNumber": "76942768614",
            "balance": 7900.0
        },
        {
            "phoneNumber": "79747692391",
            "balance": 6396.0
        },
        {
            "phoneNumber": "79759828487",
            "balance": 7216.0
        },
        {
            "phoneNumber": "79812441851",
            "balance": 8271.5
        },
        {
            "phoneNumber": "71137382151",
            "balance": 6163.0
        },
        {
            "phoneNumber": "79583781116",
            "balance": 6004.0
        },
        {
            "phoneNumber": "79621217217",
            "balance": 7433.0
        },
        ...

```

**Add balance:**

PATCH - запрос - <http://localhost:7777/abonent/pay>

Request body

```JSON
{
    "phoneNumber": "75055053709",
    "money": 4000
}
```

Response body

```JSON
{
    "id": 425,
    "numberPhone": "75055053709",
    "money": 7213.0
}
```

**Change tariff:**

PATCH - запрос - <http://localhost:7777/manager/changeTariff>

Request body

```JSON
{
    "phoneNumber": "79255453629",
    "tariffId": "06"
}
```

Response body

```JSON
{
    "id": 179,
    "numberPhone": "79255453629",
    "tariffId": "06"
}
```

**Number detail:**

GET - запрос - <http://localhost:7777/abonent/report/{number}>

Response body

```JSON
{
    "number": "79333867081",
    "tariff": "06",
    "calls": [
        {
            "callCode": "CALL_IN",
            "startTime": "2022-12-18 15:58:48",
            "endTime": "2022-12-18 16:23:51",
            "duration": "00:25:03",
            "cost": 0.0
        },
        {
            "callCode": "CALL_OUT",
            "startTime": "2023-01-14 00:04:33",
            "endTime": "2023-01-14 00:44:34",
            "duration": "00:40:01",
            "cost": 4.0
        },
        {
            "callCode": "CALL_IN",
            "startTime": "2023-01-25 13:15:04",
            "endTime": "2023-01-25 13:56:41",
            "duration": "00:41:37",
            "cost": 42.0
        },
        {
            "callCode": "CALL_IN",
            "startTime": "2023-03-05 11:44:07",
            "endTime": "2023-03-05 12:20:35",
            "duration": "00:36:28",
            "cost": 37.0
        },
        {
            "callCode": "CALL_IN",
            "startTime": "2023-03-13 06:57:47",
            "endTime": "2023-03-13 07:33:56",
            "duration": "00:36:09",
            "cost": 37.0
        },
        {
            "callCode": "CALL_OUT",
            "startTime": "2023-03-18 04:07:42",
            "endTime": "2023-03-18 04:08:31",
            "duration": "00:00:49",
            "cost": 1.0
        }
    ],
    "totalCost": 221.0
}
```

---
