package com.example.demo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChromeApplication {
    public static void main(String[] args) {
        String baseUrl = "https://www.baidu.com";
        System.setProperty("webdriver.chrome.driver", "d:\\developer\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl + "/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement inputBox = driver.findElement(By.id("kw"));
        inputBox.sendKeys("测试");
        WebElement searchButton = driver.findElement(By.id("su"));
        searchButton.click();
        List<WebElement> a = driver.findElements(By.className("t"));
        a.forEach(e->{
            System.err.println(e.getText());
        });
        a.get(5).findElement(By.tagName("a")).click();
//        Assert.assertTrue(inputBox.isDisplayed());
//        inputBox.sendKeys("123");
//        WebElement verify = driver.findElement(By.className("verify-iconfont"));
//        verify.click();
//        WebElement slider = driver.findElement(By.className("yp-riddler-slider-btn"));
//        Actions actions = new Actions(driver);
//        actions.dragAndDropBy(slider, 100, 0).perform();
    }
}
