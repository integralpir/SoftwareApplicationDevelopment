# Занятие №4 11.11.2024
## Паттерны, принципы проектирования

Для обеспечения надежности и предсказуемости разработки ПО, на протяжении всей истории компьютерных технологий,
различные видные деятели индустрии или само сообщество выделяли лучшие, универсальные практики разработки.
Предлагаю вам рассмотреть некоторые, на мой взгляд основные принципы и паттерны разработки, которые должен знать каждый разработчик ПО.
Освоив их в совершенстве, вы будете пользоваться ими как бы... на уровне инстинктов.

### Принципы SOLID

Начнем с принципов проектирования. Несмотря на наличие системного аналитика, архитектора в подавляющем большинстве команд и компаний,
перед началом разработки, разработчик так или иначе проводит более низкоуровневое проектирование ИС.
Мысленно отделяет слои логики от работы с данными, раскидывает классы по пакетам, пытается сделать так, чтобы код который он напишет был читаемым, масштабируемым, надежным и прочие-прочие приятные эпитеты.

В 2000 году Роберт Мартин (он же дядя Боб) представил общественности результат своих наблюдений в виде 5 принципов, а Майкл Физерс объединил их в акрониме ***SOLID***.

Итак, зачем же нужно знание этих принципов:
- Во-первых, для того чтобы их применять. Эти принципы позволят вам писать качественный код и разрабатывать хорошие ООП приложения.
- Во-вторых, для того чтобы пройти собеседование, т.к. их любят спрашивать.

Давайте расшифруем этот акроним:
- S. **Single Responsibility Principle**.
- O. **Open-closed Principle**.
- L. **Liskov Substitution Principle**.
- I. **Interface Segregation Principle**.
- D. **Dependency Inversion Principle**.

#### Single Responsibility Principle

Принцип единственной ответственности говорит нам о том, что класс должен выполнять лишь одну задачу в рамках логики всего приложения.
Рассмотрим на примере реализацию и последствия несоблюдение этого принципа.

В приложении которое мы рассматривали в качестве референса для работы с БД, логика класс для работы с базой данных и классы представляющие эти данные, были разделены.

```java
@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ApplicationRepositoryImpl(JdbcTemplate jdbcTemplate,
                                     NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertGameSeries(GameSeries gameSeries) {
        jdbcTemplate.update("INSERT INTO game_series VALUES (?) ON CONFLICT DO NOTHING", gameSeries.getTitle());
    }

    //Различные операции с БД
}
```

```java
public class Amiibo {
    private int id;
    private String amiiboSeriesTitle;
    private String gameSeriesTitle;
    private String character;
    private String imageLink;
    private String name;
    private String type;

    //Конструкторы и аксессоры
}
```

```java
public class AmiiboSeries {
    private String title;
    private String gameSeriesTitle;

    //Конструкторы и аксессоры
}
```

```java
public class GameSeries {
    private String title;

    //Конструкторы и аксессоры
}
```

Но давайте представим, что мы не знали, как делать правильно и по неопытности записали логику CRUD операций с БД в классы сущностей которые мы планируем в ней хранить.
Руководствоваться мы будем тем, что раз конструктор и аксессоры отвечают за создание и предоставление разных полей экземпляра в *программном коде*, 
то создание и предоставление различных полей из БД мы также опишем в классе.

```java
public class GameSeries {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    
    private String title;

    //Конструкторы и аксессоры

    @Override
    public void insertGameSeries(GameSeries gameSeries) {
        jdbcTemplate.update("INSERT INTO game_series VALUES (?) ON CONFLICT DO NOTHING", gameSeries.getTitle());
    }
    
    //Другие операции в БД
}
```

В двух других классах естественно описываем то же самое с поправкой на специфику самого класса.
В случае такой "архитектуры", при изменении работы с библиотекой или изменении самой библиотеки, или еще каком-либо другом изменении,
нам придется описывать изменение во всех остальных классах. А если экстраполировать это на большее количество классов, то вообще мрак...

#### Open-closed Principle

Этот принцип емко описывают так: программные сущности (классы, модули, функции и т.п.) должны быть открыты для расширения, но закрыты для изменения.

Грубо говоря этот принцип обязует нас использовать наследование, каждый раз когда мы хотим реализовать новый функционал поверх существующего.

Например, есть какой-то класс...

```java
public class OrderProcessor {
    public void process(Order order){

        Repository repository = new Repository();
        MailSender mailSender = new MailSender();

        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
    }
}
```

Если согласно нашей бизнес логике, в какой-то новой версии нам требуется условно добавить процесс обработки заказа до и после отправки,
то мы должны унаследоваться от данного класса и расширить его ...

```java
public class OrderProcessorWithPreAndPostProcessing extends OrderProcessor {

    @Override
    public void process(Order order) {
        beforeProcessing();
        super.process(order);
        afterProcessing();
    }

    private void beforeProcessing() {
        // Осуществим некоторые действия перед обработкой заказа
    }

    private void afterProcessing() {
        // Осуществим некоторые действия после обработки заказа
    }
}
```

Мы только на втором принципе, а уже хочется спросить что-то вроде: "А зачем?..."

#### Liskov Substitution Principle

... или по-другому "Принцип подстановки Барбары Лисков".

Это частный случай прошлого принципа, ну или его уточнение тут как вам больше нравится.

Данный принцип предписывает разработчику в случае расширения метода или класса, не изменять ожидаемое от него поведение.
Рассмотрим на примере...

Предположим у нас есть класс, который отвечает за валидацию заказа и проверяет, все ли из товаров заказа находятся на складе. 
У данного класса есть метод ```isValid``` который возвращает ```true``` или ```false```:

```java
public class OrderStockValidator {

    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (! item.isInStock()) {
                return false;
            }
        }

        return true;
    }
}
```

Рассмотрим применение этого принципа от противного, иными словами сделаем плохо...

```java
public class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if ( !item.isInStock() || !item.isPacked() ){
                throw new IllegalStateException(
                     String.format("Order %d is not valid!", order.getId())
                );
            }
        }

        return true;
    }
}
```

В вышеописанном классе мы нарушили принцип LSP. Клиенты, пользующиеся методом ```isValid```, не ожидают и не обрабатывают исключение, что несомненно приведет к ошибкам.

#### Interface Segregation Principle

Принцип заключается в декомпозиции интерфейсов. Он очень похож на самый первый принцип - единственной ответственности.
Разве что говорит скорее о поведении классов.
Например, есть условный класс для работы с отчетом и он реализует интерфейс ```ReportPrinter```...

```java
public class Report implements ReportPrinter {
    
    @Override
    public void printInExcel() {
        // Какая-то логика
    }
    
    @Override
    public void printInXML() {
        // Какая-то логика
    }
    
}
```

Но вдруг, приходит заказчик и говорит, что ему нужен какой-то другой класс отчета, но выводить он его планирует только в Excel, а XML ему не нужен. 
И нам приходится реализовывать интерфейс в котором один из методов нам будет совершенно не нужен.

Об этом и говорит нам данный принцип - разделяй интерфейсы!

#### Dependency Inversion Principle

Принцип инверсии зависимостей говорит о том, что зависимости внутри системы строятся на основе абстракций. 
Модули верхнего уровня не зависят от модулей нижнего уровня. 
Абстракции не должны зависеть от деталей. 
Детали должны зависеть от абстракций.

Этот принцип реализовывался в мой в примерах к моделям данных и в примере к этому занятию.
Например...

```java
@RestController
@RequestMapping("/relational_data_app/api/")
public class ApplicationController {

    private final ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping(value = "test")
    public ResponseEntity<Void> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
```

Контроллер в данном случае содержит ссылку не на реализацию класса, а на абстракцию. Он работает с контрактом (абстракцией), 
а реализацию (детали) мы строим вокруг этого контракта. 

На таком простеньком примере довольно сложно рассмотреть данный принцип, а еще сложнее придумать реализацию не на кошках и собаках.

Все данные принципы рождались во время серьезной разработки и именно в серьезной разработке вы поймете когда и где они нужны.

#### Итого

Вокруг данных принципов ведется огромное количество споров и т.д. и т.п.
Опытные разработчики подвергают их критике, иногда безосновательно, иногда нет.
Тем не менее данные принципы, при правильном использовании (или не использовании) сделают ваши проекты лучше.
Главное не переборщить)

### Паттерны проектирования

Перейдем к более прикладной части. Поговорим о паттернах проектирования.
Если рассматриваемый выше вопрос находился на уровне классов, то вопрос, рассматриваемый в этом блоке спускается на уровень методов.
Так уж вышло, к сожалению или к счастью, что разработчики подавляющую часть времени решают типовые задачи и сталкиваются с типовыми проблемами.
И так уж вышло, что есть неравнодушные люди которые зафиксировали и продолжают фиксировать решения этих проблем.
Безусловно, вы можете писать уникальные решения под каждую проблему, т.к. бизнес-задачи так или иначе, хотя бы немного, но отличаются друг от друга,
но если вы поймете, когда стоит пожертвовать уникальностью, то ваш код станет более универсальным, читаемым и конечно же более крутым)

Перейдем к паттернам, выделяют три группы:

- порождающие - описывают создание (instantiate) объекта или группы связанных объектов.;
- структурные - отвечают на вопрос: "Как сущности могут друг друга использовать?";
- поведенческие - очерчивают шаблоны передачи данных, обеспечения взаимодействия.

В общем, глубоко мной уважаемая "банда четырех" выделяет больше 20 паттернов, давайте рассмотрим по одному из каждой группы.

#### Порождающие. Singleton - одиночка.

Шаблон позволяет удостовериться, что создаваемый объект — единственный в своём классе.

Данный паттерн широко используется в фреймворках. Например, в spring framework, при реализации IoC, каждый созданный бин по-умолчанию является Singleton'ом (это можно изменить).
Это говорит о том, что в контексте работы сервиса, существует и будет существовать только один объект этого класса.

Давайте рассмотрим реализацию...

```java
public class Singleton {
  private static Singleton INSTANCE;

  private Singleton() {}

  public static Singleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Singleton();
    }
    return INSTANCE;
  }
}
```

Вообще, существует много видов реализаций, но чтобы понять суть, этой будет достаточно.

#### Структурные. Adapter - адаптер.

Адаптер может помочь вам совместить несовместимое. Реализуя этот паттерн, мы реализуем переходник между двумя объектами. 
Это очень похоже на переходники с различных физических интерфейсов.

Рассмотрим на примере...

```java
public interface USB {

   void connectWithUsbCable();
}
```

```java
public class MemoryCard {

   public void insert() {
       System.out.println("Карта памяти успешно вставлена!");
   }

   public void copyData() {
       System.out.println("Данные скопированы на компьютер!");
   }
}
```

```java
public class CardReader implements USB {

   private MemoryCard memoryCard;

   public CardReader(MemoryCard memoryCard) {
       this.memoryCard = memoryCard;
   }

   @Override
   public void connectWithUsbCable() {
       this.memoryCard.insert();
       this.memoryCard.copyData();
   }
}
```

У компьютера есть разъем USB, но карту памяти в него не вставить.
Карту невозможно вставить в компьютер, из-за чего мы не сможем сохранить наши фотографии, видео и другие данные.
Кардридер является адаптером, решающим данную проблему.

#### Поведенческие. Strategy - стратегия.

Шаблон «Стратегия» позволяет менять поведение программы в зависимости от ситуации.

```java
public interface FillStrategy {
   void fill();
}
```

```java
public class HybridFillStrategy implements FillStrategy {

   @Override
   public void fill() {
       System.out.println("Заправляем бензином или электричеством на выбор!");
   }
}

public class F1PitstopStrategy implements FillStrategy {

   @Override
   public void fill() {
       System.out.println("Заправляем бензин только после всех остальных процедур пит-стопа!");
   }
}

public class StandartFillStrategy implements FillStrategy {
   @Override
   public void fill() {
       System.out.println("Просто заправляем бензин!");
   }
}
```

```java
public class Auto {

   FillStrategy fillStrategy;

   public void fill() {
       fillStrategy.fill();
   }

   public void gas() {
       System.out.println("Едем вперед");
   }

   public void stop() {
       System.out.println("Тормозим!");
   }
}
```

```java
public class F1Car extends Auto {

   public F1Car() {
       this.fillStrategy = new F1PitstopStrategy();
   }
}

public class HybridAuto extends Auto {

   public HybridAuto() {
       this.fillStrategy = new HybridFillStrategy();
   }
}

public class Sedan extends Auto {

   public Sedan() {
       this.fillStrategy = new StandartFillStrategy();
   }
}
```

В рассмотренных выше примерах, мы описали разную стратегию заправки для наших условных автомобилей.
Вариант стратегии определяется на этапе создания объекта и он предопределен, что жестко фиксирует дальнейшее поведение.

### Итог 

Все вышесказанное не является правилом, оно является лишь рекомендацией. Если вы потратите время и силы, чтобы освоить эти советы,
то через несколько тысяч строк кода вы сможете на уровне инстинкта писать качественный код (или нет, все мы разные :D ).

___
## Полезные ссылки

1. Паттерны объектно ориентированного программирования банды четырех - это маст-хэв, настольная книга разработчика, всегда должна лежать в ящике.
2. Чистый код, чистая архитектура Роберта Мартина - как говорится, это база.
3. https://javarush.com/groups/posts/osnovnye-principy-dizajna-klassov-solid-v-java - немного про SOLID.
4. https://habr.com/ru/companies/vk/articles/325492/ - немного про шаблоны.