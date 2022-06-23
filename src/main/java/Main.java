import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int credit = 999; // сумма для добавления на счёт
        final int debit = 888; // сумма для списания со счёта
        final String FirstName = "Alexander";
        final String LastName = "Pushkin";
        final String PostCode = "141290";
        final String Currency = "Dollar";

        // Указать полный путь к драйверу Google Chrome
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 1000);

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // 1. Создать нового пользователя
        WebElement bankManagerLogin = By.cssSelector("[ng-click='manager()']").findElement(driver);
        bankManagerLogin.click();
        WebElement addCustomer = By.cssSelector("[ng-click='addCust()']").findElement(driver);
        addCustomer.click();
        WebElement firstName = By.cssSelector("[ng-model='fName']").findElement(driver);
        firstName.sendKeys(FirstName);
        WebElement lastName = By.cssSelector("[ng-model='lName']").findElement(driver);
        lastName.sendKeys(LastName);
        WebElement postCode = By.cssSelector("[ng-model='postCd']").findElement(driver);
        postCode.sendKeys(PostCode);
        // Так же можно через нажатие Enter , Keys.ENTER
        WebElement sendCustomer = By.cssSelector(".btn.btn-default").findElement(driver);
        sendCustomer.click();
        Alert alert = wait.until(alertIsPresent());
        boolean a = alert.getText().startsWith("Customer added successfully with customer id :");
        alert.accept();
        Assert.assertTrue(a);

        // 2. Открыть счет для нового пользователя
        WebElement openAccount = By.cssSelector("[ng-click='openAccount()']").findElement(driver);
        openAccount.click();
        Select dropdownCustomer = new Select(driver.findElement(By.id("userSelect")));
        dropdownCustomer.selectByVisibleText(FirstName +" "+ LastName);
        Select dropdownCurrency = new Select(driver.findElement(By.id("currency")));
        dropdownCurrency.selectByVisibleText(Currency);
        WebElement process = By.cssSelector("button[type='submit']").findElement(driver);
        process.click();
        Alert alert1 = wait.until(alertIsPresent());
        boolean b = alert1.getText().startsWith("Account created successfully with account Number :");
        String accountNumber = alert1.getText().substring(50);
        alert1.accept();
        Assert.assertTrue(b);

        // 3. Пополнить счета
        WebElement home = By.cssSelector("[ng-click='home()']").findElement(driver);
        home.click();
        WebElement customerLogin = By.cssSelector("[ng-click='customer()']").findElement(driver);
        customerLogin.click();
        Select dropdownCustomer1 = new Select(driver.findElement(By.id("userSelect")));
        dropdownCustomer1.selectByVisibleText(FirstName +" "+ LastName);
        WebElement login = By.cssSelector("button[type='submit']").findElement(driver);
        login.click();

        // Проверка Фамилии, Имени, номера счёта и валюты в Личном кабинете клиента
        Assert.assertEquals(By.cssSelector("span.fontBig.ng-binding").findElement(driver).getText(),
                FirstName +" "+ LastName);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(3)").findElement(driver).getText(),
                Currency);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(1)").findElement(driver).getText(),
                accountNumber);
        Assert.assertEquals(By.id("accountSelect").findElement(driver).getText(), accountNumber);

        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(2)").findElement(driver).getText(),
                "0");
        WebElement deposit = By.cssSelector("[ng-click='deposit()']").findElement(driver);
        deposit.click();
        WebElement amount = By.cssSelector("[ng-model='amount']").findElement(driver);
        amount.sendKeys(String.valueOf(credit));
        WebElement depositOk = By.cssSelector("button[type='submit']").findElement(driver);
        depositOk.click();
        Assert.assertEquals(By.cssSelector("span.fontBig.ng-binding").findElement(driver).getText(),
                FirstName +" "+ LastName);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(3)").findElement(driver).getText(),
                Currency);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(1)").findElement(driver).getText(),
                accountNumber);
        Assert.assertEquals(By.id("accountSelect").findElement(driver).getText(), accountNumber);
        WebElement message = By.cssSelector("[ng-show='message']").findElement(driver);
        Assert.assertEquals(message.getText(), "Deposit Successful");
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(2)").findElement(driver)
                .getText(), String.valueOf(credit));
        WebElement transactions = By.cssSelector("[ng-click='transactions()']").findElement(driver);
        Thread.sleep(1000);
        transactions.click();
        Assert.assertEquals(By.cssSelector("[ng-repeat='tx in transactions | orderBy:sortType:sortReverse | sDate:startDate:end']")
                .findElements(driver).size(), 1);


        // 4. Снять средств со счета
        WebElement back = By.cssSelector("[ng-click='back()']").findElement(driver);
        back.click();
        Assert.assertEquals(By.cssSelector("span.fontBig.ng-binding").findElement(driver).getText(),
                FirstName +" "+ LastName);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(3)").findElement(driver).getText(),
                Currency);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(1)").findElement(driver).getText(),
                accountNumber);
        Assert.assertEquals(By.id("accountSelect").findElement(driver).getText(), accountNumber);
        WebElement withdrawl = By.cssSelector("[ng-click='withdrawl()']").findElement(driver);
        withdrawl.click();
        Assert.assertEquals(By.cssSelector("span.fontBig.ng-binding").findElement(driver).getText(),
                FirstName +" "+ LastName);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(3)").findElement(driver).getText(),
                Currency);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(1)").findElement(driver).getText(),
                accountNumber);
        Assert.assertEquals(By.id("accountSelect").findElement(driver).getText(), accountNumber);
        WebElement amountWithdrawn = By.cssSelector("[ng-model='amount']").findElement(driver);
        amountWithdrawn.sendKeys(String.valueOf(debit));
        WebElement withdraw = By.cssSelector("button[type='submit']").findElement(driver);
        withdraw.click();
        WebElement messageWithdraw = By.cssSelector("[ng-show='message']").findElement(driver);
        Assert.assertEquals(By.cssSelector("span.fontBig.ng-binding").findElement(driver).getText(),
                FirstName +" "+ LastName);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(3)").findElement(driver).getText(),
                Currency);
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(1)").findElement(driver).getText(),
                accountNumber);
        Assert.assertEquals(messageWithdraw.getText(), "Transaction successful");
        Assert.assertEquals(By.cssSelector("div.center strong.ng-binding:nth-child(2)").findElement(driver)
                .getText(), String.valueOf(credit-debit));
        WebElement transactions1 = By.cssSelector("[ng-click='transactions()']").findElement(driver);
        Thread.sleep(1000);
        transactions1.click();
        Assert.assertEquals(By.cssSelector("[ng-repeat='tx in transactions | orderBy:sortType:sortReverse | sDate:startDate:end']")
                .findElements(driver).size(), 2);

        // 5. Очистить список операций по счету
        WebElement resetTransactions = By.cssSelector("[ng-click='reset()']").findElement(driver);
        resetTransactions.click();
        // проверяем что больше нет строк в таблице
        Assert.assertEquals(By.cssSelector("[ng-repeat='tx in transactions | orderBy:sortType:sortReverse | sDate:startDate:end']")
                .findElements(driver).size(), 0);

        // 6. Удалить созданного пользователя
        home.click();
        WebElement bankManagerLogin1 = By.cssSelector("[ng-click='manager()']").findElement(driver);
        bankManagerLogin1.click();
        WebElement customers = By.cssSelector("[ng-click='showCust()']").findElement(driver);
        customers.click();
        WebElement searchCustomer = By.cssSelector("[ng-model='searchCustomer']").findElement(driver);
        searchCustomer.sendKeys(LastName);
        Assert.assertEquals(By.cssSelector("[ng-repeat='cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer']")
                .findElements(driver).size(), 1);
        WebElement deleteCustomer = By.cssSelector("[ng-click='deleteCust(cust)']").findElement(driver);
        deleteCustomer.click();
        Assert.assertEquals(By.cssSelector("[ng-repeat='cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer']")
                .findElements(driver).size(), 0);

        Thread.sleep(3000);
        driver.quit();
    }
}