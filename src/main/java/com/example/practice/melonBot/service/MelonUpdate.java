package com.example.practice.melonBot.service;

import com.example.practice.melonBot.dto.Melon100Dto;
import com.example.practice.melonBot.dto.MelonDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MelonUpdate {

    @Autowired
    private MelonService melonService;

    @Scheduled(cron = "0 0 * * * *") // 매 시간 0시 0분 실행 (cron 표현식)
//    @Scheduled(fixedRate = 60000) // 1시간마다 실행 (밀리초 단위) 1초 1000
    public void updateDatabase() {
        // 크롬 드라이버 경로 설정
        System.setProperty("webdriver.chrome.driver", "C:\\Dev\\tools\\chromedriver\\chromedriver.exe" );

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저를 표시하지 않고 실행합니다.
        options.addArguments("--remote-allow-origins=*"); // 특정 원격 호스트에서 브라우저에 접근할 수 있도록 허용
        // 웹 드라이버 생성
        WebDriver driver = new ChromeDriver(options);

        // 멜론 차트 페이지 URL
        String url = "https://www.melon.com/chart/index.htm";

        // 페이지로 이동
        driver.get(url);

        // 2초 대기
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        List<String> ranks = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> artists = new ArrayList<>();
        List<String> likeCounts = new ArrayList<>();
        List<String> titleLinks = new ArrayList<>();
        List<String> artistLinks = new ArrayList<>();

        // **********************************50위까지***********************************
        // 곡 랭크 파싱
        List<WebElement> rank50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[2]/div/span[1]"));
        for (WebElement rankElement : rank50Elements ){
            ranks.add(rankElement.getText());
        }
        // 곡 제목 파싱
        List<WebElement> title50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[6]/div/div/div[1]/span/a"));
        for (WebElement titleElement : title50Elements) {
            titles.add(titleElement.getText());
        }
        // 아티스트 파싱
        List<WebElement> artist50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[6]/div/div/div[2]/a"));
        for (WebElement artistElement : artist50Elements) {
            artists.add(artistElement.getText());
        }
        // 좋아요 수 파싱
        List<WebElement> like50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[8]/div/button/span[2]"));
        for (WebElement likeElement : like50Elements) {
            likeCounts.add(likeElement.getText());
        }
        // 곡 제목 링크
        List<WebElement> titleLink50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[5]/div/a"));
        for (WebElement titleLinkElement : titleLink50Elements) {
        	String titleLink = titleLinkElement.getAttribute("href");
        	// 정규 표현식 패턴 설정
            Pattern pattern = Pattern.compile("\\d+");
            // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
            Matcher matcher = pattern.matcher(titleLink);
            while (matcher.find()) {
            	String st = matcher.group();
            	titleLinks.add(st);
            }	
        }
        // 아티스트 링크
        List<WebElement> artistLink50Elements = driver.findElements(By.xpath("//*[@id=\"lst50\"]/td[6]/div/div/div[2]/a"));
        for (WebElement artistLinkElement : artistLink50Elements) {
        	String artistLink = artistLinkElement.getAttribute("href");
        	// 정규 표현식 패턴 설정
            Pattern pattern = Pattern.compile("\\d+");
            // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
            Matcher matcher = pattern.matcher(artistLink);
            while (matcher.find()) {
            	String st = matcher.group();
            	artistLinks.add(st);
            }	
        }
        
        // **********************************100위까지***********************************
        // 곡 랭크 파싱
        List<WebElement> rank100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[2]/div/span[1]"));
        for (WebElement rankElement : rank100Elements ){
            ranks.add(rankElement.getText());
        }
        // 곡 제목 파싱
        List<WebElement> title100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[6]/div/div/div[1]/span/a"));
        for (WebElement titleElement : title100Elements) {
            titles.add(titleElement.getText());
        }
        // 아티스트 파싱
        List<WebElement> artist100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[6]/div/div/div[2]/a"));
        for (WebElement artistElement : artist100Elements) {
            artists.add(artistElement.getText());
        }
        // 좋아요 수 파싱
        List<WebElement> like100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[8]/div/button/span[2]"));
        for (WebElement likeElement : like100Elements) {
            likeCounts.add(likeElement.getText());
        }
        // 곡 제목 링크
        List<WebElement> titleLink100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[5]/div/a"));
        for (WebElement titleLinkElement : titleLink100Elements) {
        	String titleLink = titleLinkElement.getAttribute("href");
        	// 정규 표현식 패턴 설정
            Pattern pattern = Pattern.compile("\\d+");
            // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
            Matcher matcher = pattern.matcher(titleLink);
            while (matcher.find()) {
            	String st = matcher.group();
            	titleLinks.add(st);
            }	
        }
        // 아티스트 링크
        List<WebElement> artistLink100Elements = driver.findElements(By.xpath("//*[@id=\"lst100\"]/td[6]/div/div/div[2]/a"));
        for (WebElement artistLinkElement : artistLink100Elements) {
        	String artistLink = artistLinkElement.getAttribute("href");
        	// 정규 표현식 패턴 설정
            Pattern pattern = Pattern.compile("\\d+");
            // 정규 표현식을 사용하여 문자열에서 매치되는 부분을 찾음
            Matcher matcher = pattern.matcher(artistLink);
            while (matcher.find()) {
            	String st = matcher.group();
            	artistLinks.add(st);
            }	
        }

        Melon100Dto melon100 = new Melon100Dto(ranks, titles, artists, likeCounts, titleLinks, artistLinks);


        List<MelonDto> melonDtos = melonService.melonAllSelect();

        if (melonDtos == null){
            for(int i = 0; i < melon100.getRanks().size(); i++){

                melonService.melonDelete();

                int rank = Integer.parseInt(melon100.getRanks().get(i));
                melonService.melonSet(rank, melon100.getTitles().get(i), melon100.getArtists().get(i), melon100.getLikes().get(i)
                		, melon100.getTitleLinks().get(i), melon100.getArtistLinks().get(i));
            }
        }else {
        	System.out.println("--------------------------------------------------------멜론업데이트");
            for(int i = 0; i < melon100.getRanks().size(); i++){
                int rank = Integer.parseInt(melon100.getRanks().get(i));
                melonService.melonUpdate(rank, melon100.getTitles().get(i), melon100.getArtists().get(i), melon100.getLikes().get(i)
                		, melon100.getTitleLinks().get(i), melon100.getArtistLinks().get(i));

            }
        }



        // 웹 드라이버 종료
        driver.quit();
    }
}
