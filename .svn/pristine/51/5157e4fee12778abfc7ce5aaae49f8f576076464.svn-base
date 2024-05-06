package com.example.practice.melonBot.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MelonBot {
    public static void main(String[] args) throws InterruptedException {
        // 크롬 드라이버 경로 설정
        System.setProperty("webdriver.chrome.driver", "C:\\Dev\\tools\\chromedriver\\chromedriver.exe" );

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저를 표시하지 않고 실행합니다.
        options.addArguments("--remote-allow-origins=*");
        // 웹 드라이버 생성
        WebDriver driver = new ChromeDriver(options);

        // 멜론 차트 페이지 URL
        String url = "https://www.melon.com/chart/index.htm";

        // 페이지로 이동
        driver.get(url);

        // 2초 대기
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 곡 제목 파싱
        WebElement element = driver.findElement(By.xpath("//*[@id=\"lst50\"]/td[6]/div/div/div[1]/span/a"));
        String title = element.getAttribute("title");

        // 좋아요 수 파싱
        WebElement likeElement = driver.findElement(By.xpath("//*[@id=\"lst50\"]/td[8]/div/button/span[2]"));
        String cntLike = likeElement.getText();

//        for (WebElement element : elements) {
//            System.out.println(element.getText());
//        }
        WebElement titleLink50Elements = driver.findElement(By.xpath("//*[@id=\"lst50\"]/td[5]/div/a"));
        
        	
        	String str = titleLink50Elements.getAttribute("href");
        	String intStr = "";
        	for (int i = 0; i < str.length(); i++) {
        	    char ch = str.charAt(i);
        	    if (48 <= ch && ch <= 57) {
        	        intStr += ch;
        	    }
        	
        	System.out.println(intStr);
            
            System.out.println("qwerqwer" + intStr);	
        
        }
//        // 아티스트 링크
//        List<WebElement> artistLink50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[5]/div/a"));
//        for (WebElement artistLinkElement : artistLink50Elements) {
//        	String artistLink = artistLinkElement.getText();
//        	// 정규 표현식 패턴 설정
//            Pattern pattern = Pattern.compile("\\d+");
//            // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
//            System.out.println("artistLink" + artistLink);
//
//        }
//        // 정규 표현식 패턴 설정
//        Pattern pattern = Pattern.compile("\\d+");
//        // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
//        Matcher matcher = pattern.matcher();
        System.out.println("1위 노래는 [" + title + "]입니다.");
        System.out.println("좋아요 수는 [" + cntLike + "]입니다.");

        // 웹 드라이버 종료
        driver.quit();
    }
}
