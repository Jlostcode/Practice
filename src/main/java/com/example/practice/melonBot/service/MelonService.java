package com.example.practice.melonBot.service;

import com.example.practice.melonBot.dao.MelonDao;
import com.example.practice.melonBot.dto.MelonDto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Service
public class MelonService {

    @Autowired
    private MelonDao melonDao;

    public List<MelonDto> melonAllSelect(){
        return melonDao.melonAllSelect();
    }
    public void melonSet(int melon_rank, String melon_title, String melon_artist, String melon_like, String melon_titleLink,
    		String melon_artistLink) {
        melonDao.melonSet(melon_rank, melon_title, melon_artist, melon_like, melon_titleLink, melon_artistLink);
    }

    public void melonUpdate(int melon_rank, String melon_title, String melon_artist, String melon_like, String melon_titleLink,
    		String melon_artistLink){
        melonDao.melonUpdate(melon_rank, melon_title, melon_artist, melon_like, melon_titleLink, melon_artistLink);
    }

    public void melonDelete(){
        melonDao.melonDelete();
    }
    public void melonExcelDown(HttpServletResponse response) throws IOException {
    	// 현재 날짜를 포맷팅할 패턴
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd HH시mm분");
        String currentDateTime = fileNameFormat.format(new Date());
       
        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MELON_CHART_TOP100(" + currentDateTime + ")");
		
        List<MelonDto> dataList = melonAllSelect();
        // 헤더 작성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"순위", "곡 제목", "아티스트", "좋아요"}; // 열 헤더들
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 작성
        int rowNum = 1;
        for (MelonDto data : dataList) {
        	System.out.println(data);
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getMelon_rank());
            row.createCell(1).setCellValue(data.getMelon_title());
            row.createCell(2).setCellValue(data.getMelon_artist());
            row.createCell(3).setCellValue(data.getMelon_like());

            // 필요한 만큼 열 추가
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        // 첫 번째 열의 너비를 15로 설정
        sheet.setColumnWidth(0, 15 * 256); // 256은 Excel의 셀 단위(1/256th of a character width)
        // 두 번째 열의 너비를 20으로 설정
        sheet.setColumnWidth(1, 40 * 256);
        sheet.setColumnWidth(2, 25 * 256);
        sheet.setColumnWidth(3, 16 * 256);

        
        // HTTP 응답으로 엑셀 파일 보내기
        response.setContentType("application/vnd.ms-excel");
        // currentDateTime이 UTF-8로 인코딩되어 Content-Disposition 헤더에 설정됨
        String encodedFileName = URLEncoder.encode("MELON_CHART_TOP100(" + currentDateTime + ").xlsx", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
