package lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElementsTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // Инициализируем драйвер
        driver.get("https://ilcarro.web.app"); // Открываем ссылку на домашнюю страницу сайта, который тестируем
        driver.manage().window().setPosition(new Point(2500, 0)); // Подвинуть окно браузера на 2500 пикселей вправо, чтобы он запускался на втором мониторе
        driver.manage().window().maximize(); // На весь экран развернуть браузер
        // Неявное ожидание локатора. Если элемент появится до истечения времени, Selenium сразу продолжит выполнение, не дожидаясь окончания таймера.
        // Устанавливает глобальное ожидание на все элементы, которые вы пытаетесь найти с помощью методов findElement или findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //!*************************************************** 12 урок *************************************************
    @Test
    public void testFindElementsTagName2() {
        //* -------------------- Локатор с тегом <h1> -------------------- *//
        driver.findElement(By.cssSelector("h1")); // Альтернативный способ
        WebElement h1 = driver.findElement(By.tagName("h1"));
        System.out.println("Заголовок h1 имеет текст: " + h1.getText());

        //* -------------------- Локатор с тегом <a> (логотип) -------------------- *//
        WebElement a = driver.findElement(By.tagName("a"));
        System.out.println("Текст элемента с тегом <a>: [" + a.getText() + "] -> текста нет, потому что это - картинка");
        System.out.println("Размер элемента с тегом <a>: " + a.getSize()); //? Элементов несколько, а первый - картинка. Поэтому у неё нет текста, но есть разрешение.

        //* -------------------- Локатор с тегом <img> (изображение внутри <a>) -------------------- *//
        WebElement img = driver.findElement(By.tagName("img"));
        String imgSrc = img.getAttribute("src"); // Получить ссылку на изображение
        System.out.println("Ссылка <src> на изображение <img>: " + imgSrc);

        //* -------------------- Массив элементов с тегом <a> -------------------- *//
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        System.out.println("Размер массива элементов с тегом <a>: [" + elements_a.size() + "]");

        // Проверка, что элементов достаточно
        if (elements_a.size() > 2) {
            WebElement thirdElement = elements_a.get(2); // Получаем 3-й элемент (индекс 2)
            System.out.println("Текст 3-го элемента с тегом <a> в массиве: " + thirdElement.getText());
            System.out.println("Аттрибут <href>: " + thirdElement.getAttribute("href")); // Для получения ссылки объекта
        } else {
            System.out.println("Список содержит менее 3 элементов.");
        }

        //* -------------------- Перебор всех элементов <a> -------------------- *//
        for (WebElement element : elements_a) {
            System.out.println("Элемент [" + element.getText() + "] имеет атрибут <href>: " + element.getAttribute("href")); // Получаем ссылку и текст элемента
        }
    }

    @Test
    public void byID() {
        //! 📌 Стратегия поиска по id - это самая успешная стратегия.
        //? By.id
        //! #value
        //* #city
        driver.findElement(By.id("city"));
        driver.findElement(By.cssSelector("#city"));
        driver.findElement(By.xpath("//*[@id='city']")); // дольше, потому что не указано среди каких именно элементов ищем
        driver.findElement(By.cssSelector("input#city"));
        driver.findElement(By.xpath("//input[@id='city']")); // хоть #city и так уникален, но такое уточнение сделает поиск ещё быстрее
    }

    @Test
    public void byClassName() {
        //? By.className
        //! .value
        //* .telephone
        WebElement telephone1 = driver.findElement(By.className("telephone"));
        WebElement telephone2 = driver.findElement(By.cssSelector(".telephone"));
        System.out.println("telephone1: " + telephone1.getText() + "\ntelephone2: " + telephone2.getText());
    }

    @Test
    public void byLinkPartialLinkText() {
        //? By.linkText
        //! Ищет элементы, у которых точное совпадение текста.
        //* Далее по xPath, потому что CSS-селекторы не поддерживают поиск по тексту внутри элемента.
        WebElement letTheCarWork = driver.findElement(By.linkText("Let the car work"));
        System.out.println("Текст элемента 'Let the car work' в 'header': " + letTheCarWork.getText());

        WebElement search = driver.findElement(By.linkText("Search"));
        System.out.println("Текст элемента 'Search' в 'header': " + search.getText());

        //? By.partialLinkText
        //! Ищет элементы, у которых частичное совпадение текста.
        WebElement partialLinkText = driver.findElement(By.partialLinkText("work"));
        System.out.println("Текст элемента 'By.partialLinkText('work')': " + partialLinkText.getText());
        System.out.println("Размер элемента 'By.partialLinkText('work')': " + partialLinkText.getSize() + " пикселей");

        //? Посчитаем сколько элементов таких на странице в целом
        List<WebElement> work = driver.findElements(By.partialLinkText("work"));
        System.out.println("Количество элементов по частичному совпадению с 'work': " + work.size() + " шт.");
    }

    @Test
    public void chooseFasterMethod() {
        //* Ищем уникальный локатор
        driver.findElement(By.cssSelector(".logo")); //! 5 элементов, не подходит
        driver.findElement(By.cssSelector("div.header .logo")); //* 1 уникальный элемент, 🔥 Лучший выбор
        driver.findElement(By.xpath("//div[@class='header']//a[@class='logo']")); // Далее варианты его же, но с помощью xPath
        driver.findElement(By.xpath("//div[contains(@class, 'header')]//a[contains(@class, 'logo')]"));

        //! 🔥 Второй вариант проигрывает первому, потому что требуется сохранять все найденные элементы в коллекцию и потом обращаться к ним по индексу
        List<WebElement> logo = driver.findElements(By.className("logo"));//* 5 элементов, выбираем нужный
        logo.get(2).click();
    }

    @Test
    public void getAttributesTests() {
        //* 🔍 Получаем аттрибуты искомого элемента
        WebElement city = driver.findElement(By.id("city"));
        System.out.println(city.getSize()); //* Размер инпута (396, 46)
        System.out.println(city.getAttribute("type"));
        System.out.println(city.getAttribute("id"));
        System.out.println(city.getAttribute("formcontrolname"));
        System.out.println(city.getAttribute("ng-reflect-label"));
        System.out.println(city.getAttribute("class"));
        System.out.println(city.getAttribute("autocomplete"));
    }

    //!*************************************************** 13 урок *************************************************

    @Test
    public void xPathToCSConversion() {
        //! если нашли пару ng-reflect-name="city" то делаем из неё cssSelector [ng-reflect-name='city']
        //! [attr="value"] // Среда не любит двойные кавычки
        //* Находит элементы, у которых ng-reflect-name='city'. В нашем случае это input
        WebElement city = driver.findElement(By.cssSelector("[ng-reflect-name='city']"));
        System.out.println("Размер инпута поля 'city': " + city.getSize() + " пикселей");
    }

    @Test
    public void findElementsByExactAndPartialText() {
        //* 🔍 CSS-селекторы
        driver.findElement(By.cssSelector("#city")); // * Эквивалентно By.id("city"), потому что '#' обозначает ID
        driver.findElement(By.cssSelector("input#city")); // * Точный поиск элемента <input> с id='city'
        driver.findElement(By.cssSelector("input[id='city']")); // * Точное совпадение по атрибуту id='city'
        driver.findElement(By.cssSelector("input[id^='cit']")); // * Выбирает <input>, где id начинается с 'cit'
        driver.findElement(By.cssSelector("input[id$='ty']")); // * Выбирает <input>, где id заканчивается на 'ty'
        driver.findElement(By.cssSelector("input[id*='it']")); // * Выбирает <input>, где id содержит 'it' в любом месте

        //* 🔍 XPath-селекторы
        driver.findElement(By.xpath("//a[text()=' Let the car work ']")); //* Полное совпадение текста внутри <a> (учитывая пробелы)
        driver.findElement(By.xpath("//a[normalize-space(text())='Let the car work']")); //* Полное совпадение текста внутри <a> (игнорируя лишние пробелы в начале и в конце)
        //? Если нужен частичный поиск, лучше использовать contains()
        driver.findElement(By.xpath("//a[.=' Let the car work ']")); //* Аналогично предыдущему, '.' вместо text() – сокращенная запись
        driver.findElement(By.xpath("//a[contains(., ' Let the car work ')]")); //* содержит " Let the car work " (учитывает пробелы)
        driver.findElement(By.xpath("//a[contains(text(), ' Let the car work ')]")); //* содержит " Let the car work " (учитывает пробелы)
        driver.findElement(By.xpath("//a[contains(text(), 'work')]")); //* содержит "work" (гибче, чем полное совпадение), но таких совпадений может быть уже несколько
        driver.findElement(By.xpath("(//a[contains(text(), 'work')])[1]")); //* содержащий "work" (если таких элементов несколько)
        driver.findElement(By.xpath("//a[starts-with(text(), ' Let')]")); //* начинается с ' Let'
        driver.findElement(By.xpath("//a[substring(text(), string-length(text()) - string-length('work') + 1) = 'work']")); //* XPath не имеет функции ends-with(), но можно использовать substring() для поиска элемента, который заканчивается на что-то
    }

    @Test
    public void findElementsByXpath() {
        //? By.xpath
        //! //*[@attr='value'] (* значит искать любые элементы, место * поставить div и он будет искать только среди div)
        //! //*[contains(@attr,'value')] --> второй вариант поиска
        //! //*[contains(@class,'value') and contains(text(),'value')] --> третий вариант поиска
        //! //div[@class='someClass'] ищет div элемент с классом 'someClass'
        //! //*[@class='someClass'] ищет любой элемент с классом 'someClass'
        //! h1 -> //h1
        driver.findElement(By.xpath("//h1"));

        //! id -> //*[@id='value']
        //* #city --> //*[@id='city']
        driver.findElement(By.xpath("//*[@id='city']"));

        //! //a[text()=' Let the car work ']
        //! //*[contains(@attr,'value')]
        driver.findElement(By.xpath("//a[text()=' Let the car work ']"));//* точное совпадение
        driver.findElement(By.xpath("//a[.=' Let the car work ']")); //* точное совпадение
        driver.findElement(By.xpath("//a[contains(text(),'work')]")); //* частичное совпадение
        driver.findElement(By.xpath("(//a[contains(text(),'work')])[1]")); //*  частичное совпадение (первый элемент на странице)
        driver.findElement(By.xpath("//a[starts-with(text(),'Let the car')]")); //* начинается с Let the car
    }

    @Test
    public void telephoneTests() {
        //! class -> //*[@class='value']
        //* .telephone -> //*[@class='telephone']
        driver.findElement(By.className("telephone"));
        driver.findElement(By.cssSelector(".telephone"));
        driver.findElement(By.cssSelector("address.address-container .telephone"));
        driver.findElement(By.xpath("//*[@class='telephone']"));
        driver.findElement(By.xpath("//a[.='866-720-5721']"));
        driver.findElement(By.xpath("//a[text()='866-720-5721']"));
        driver.findElement(By.xpath("//a[contains(text(),'-720-')]"));
        driver.findElement(By.xpath("//a[starts-with(text(),'866-72')]"));
        driver.findElement(By.linkText("866-720-5721"));
        driver.findElement(By.partialLinkText("-720-"));
        driver.findElement(By.xpath("//a[@class='telephone' and text()='866-720-5721']"));
        driver.findElement(By.xpath("//a[contains(@class, 'tele') and contains(text(),'-720-')]"));
    }

    //!*************************************************** 14 урок *************************************************

    @Test
    public void siblingsCSSTest() {
        //* ✅ <h1> внутри <app-search>, на любом уровне вложенности.
        driver.findElement(By.cssSelector("app-search h1")); // 1 элемент

        //! ❌ <h1>, которые находятся непосредственно внутри <app-search> (без промежуточных тегов).
        //driver.findElement(By.cssSelector("app-search>h1")); // ❌ элементов, поэтому спускаемся на ступеньку ниже
        driver.findElement(By.cssSelector("div>h1")); // ✅ при прямом пути от <div> мы достучались до <h1>

        //* ✅ <div>, которые находятся внутри <app-search>, на любом уровне вложенности.
        driver.findElement(By.cssSelector("app-search div")); // 23 элементов

        //* ✅ <div>, которые являются непосредственными (прямыми) детьми <app-search>.
        driver.findElement(By.cssSelector("app-search>div")); // 1 <div> элемент
        driver.findElement(By.cssSelector("app-search>div>div>h1")); // 1 <h1> элемент

        //* ✅ <h1>, которые находятся внутри <div>, вложенного в другой <div>.
        driver.findElement(By.cssSelector("div div h1")); // 1 <h1> элемент
        //driver.findElement(By.cssSelector("div div div h1")); // ❌ нет такого пути до <h1> элемента
        driver.findElement(By.cssSelector("app-search div div h1")); // 1 <h1> элемент
    }

    @Test
    public void nthCSSTest() {
        //= div>span ==> //div/span
        //= div span ==> //div//span

        //! ❌ находит 2 элемента, потому что есть 2 <span> 'Affordable prices'/'Guaranteed cars' и 'City'/'Dates'
        driver.findElement(By.cssSelector("form>div")); // 2 элемента
        driver.findElement(By.cssSelector("app-search>app-special-offers>div>div>div>span")); // 2 элемента

        //* ✅ nth-of-type(n) Выбирает n-й элемент среди элементов ОДНОГО ТИПА (например, <span>).
        driver.findElement(By.cssSelector("form>div:nth-of-type(1)")); // 1 элемент
        driver.findElement(By.cssSelector("app-search>app-special-offers>div>div>div>span:nth-of-type(1)")); // 1 элемент

        //* ✅ nth-child(n) Выбирает n-го ребёнка среди всех типов элементов в родителе, неважно, <span> он или нет
        driver.findElement(By.cssSelector("app-search>app-special-offers>div>div>div>span:nth-child(1)")); // 1 элемент
    }

    @Test
    public void siblingsTest() {
        //! parent - родитель
        //* ✅ Находит родительский элемент (parent) для каждого <h1>.
        driver.findElement(By.xpath("//h1/parent::*")); // Прямой родитель
        driver.findElement(By.xpath("//h1/parent::div")); // Прямой родитель представитель <div>
        driver.findElement(By.xpath("//h1/..")); // На шаг вверх

        //! ancestor - предок
        //* ✅ Находит всех предков (ancestor) элемента <h1>, поднимаясь вверх по DOM-дереву до <html>.
        driver.findElement(By.xpath("//h1/ancestor::*")); // Все предки
        driver.findElement(By.xpath("//h1/ancestor::div")); // 2 <div> элемента предка h1
        driver.findElement(By.xpath("//h1/ancestor::div[1]")); // 1 элемент <div> элемент предок h1

        //! preceding - предшествующие, предыдущие
        //* ✅ Находит все элементы, которые расположены перед элементом <a> с текстом " Log in "
        driver.findElement(By.xpath("//a[.=' Log in ']/preceding::*"));
        //* ✅ Находит [1] элемент, который расположен перед элементом <a> с текстом " Log in "
        driver.findElement(By.xpath("//a[.=' Log in ']/preceding::*[1]"));
        //* ✅ Находит элемент <img>, который находится перед элементом <a>, содержащим текст " Let the car work "
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding::img"));

        //! following - следующий
        //* ✅ Находит все элементы, которые находятся после элемента <a> с текстом "Let the car work" в структуре документа, исключая его потомков и включая все элементы на уровне или глубже в иерархии
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/following::*"));

        //! sibling - брат или сестра, элемент с тем же родителем
        //* ✅ Находит первого брата, который находится перед (preceding) или после (following) элемента <a> на том же уровне вложенности (то есть, являющийся его сиблингом — элементом с тем же родителем)
        driver.findElement(By.xpath("//a[text()=' Sign up ']/preceding-sibling::*[1]"));
        driver.findElement(By.xpath("//a[text()=' Terms of use ']/following-sibling::*[2]"));

        driver.findElement(By.xpath("//input[@id='dates']/preceding::*")); // 54 elements matching.
        //* ancestor::* выбирает всех предков (родителей) текущего элемента, поднимаясь по дереву DOM до самого <html>
        driver.findElement(By.xpath("//input[@id='dates']/ancestor::*")); // 9 elements matching.
        //* Найдёт все <h1>, которые находятся перед любым предком текущего элемента
        driver.findElement(By.xpath("//input[@id='dates']/ancestor::*/preceding::h1")); // 1 element matching.
        //* Найдёт все <h1>, которые находятся перед любым предком текущего элемента
        driver.findElement(By.xpath("//input[@id='dates']/ancestor::*/preceding::div")); // 4 elements matching.
        driver.findElement(By.xpath("//input[@id='dates']/preceding::label")); // 2 elements matching.
        driver.findElement(By.xpath("//input[@id='dates']/preceding::label[2]")); // 1 element matching.
    }

    @Test
    public void siblingsTest2() {
        driver.get("https://ticket-service-69443.firebaseapp.com/"); // Открываем страницу "https://ticket-service-69443.firebaseapp.com/"
        // Находит элемент с текстом 'Berlin City Hall | Events and Tickets' и классом 'mt-3'
        driver.findElement(By.xpath("//*[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));
        // Находит элемент, содержащий класс 'mt-3' и текст 'Berlin City Hall | Events and Tickets'
        driver.findElement(By.xpath("//span[contains(@class, 'mt-3') and contains(text(), 'Berlin City Hall | Events and Tickets')]"));
    }

    @Test
    public void siblingTests2() {
        driver.get("https://ticket-service-69443.firebaseapp.com/");
        driver.findElement(By.xpath("//*[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));
        driver.findElement(By.xpath("//span[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));
        driver.findElement(By.xpath("//span[contains(@class,'mt-') and contains(text(),'Berlin City Hall | Events and Tickets')]"));
    }

    //************ https://demowebshop.tricentis.com/ *************************
    //! .header // Header сайта
    //! .header>div // такой локатор найдет все <div> (дети) внутри <header>
    //! .header>div>a // найдет <a> внутри <div> внутри <header>
    //! .header>div>a>img // найдет <img> внутри <a> внутри <div> внутри <header>
    //! a[href="/search"] // найдет <a> с атрибутом href="/search" внизу страницы
    //! ul (16 элементов)
    //! ul .ico-register (1 элемент. Register)

    @Test
    public void findElementsByCssSelector() {
        //! a.logo>img                   [Но таких элементов 3 на странице]
        //! a.logo img                   [Но таких элементов 3 на странице]
        //! a.logo:first-child           [Первый]
        //! a.logo:last-child            [Последний]
        //! //a[@class='logo']/img       [3 elements]
        //* (//a[@class='logo']/img)[1] [поэтому используем такой поиск]
        driver.findElement(By.cssSelector("a.logo > img:first-child")); // Первого ребенка
        driver.findElement(By.cssSelector("div.header:nth-child(1) img:last-child")); // Последнего ребенка
    }

    @AfterMethod(enabled = true) // включение или отключения закрытия браузера после тестов
    public void tearDown() {
        driver.quit();
    }
}
