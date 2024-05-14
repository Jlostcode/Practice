package com.example.practice.melonBot.service;

import com.example.practice.login.dto.UserDto;
import com.example.practice.melonBot.dao.MelonDao;
import com.example.practice.melonBot.dto.MelonDto;
import com.example.practice.melonBot.dto.MelonListDto;
import com.example.practice.melonBot.util.Pagination;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    public List<MelonListDto> melonPagination(HashMap<String, Object> params) {
    	return melonDao.melonPagination(params);
    }
    public Integer melonPaginationCnt(HashMap<String, Object> params) {
    	return melonDao.melonPaginationCnt(params);
    }
    public List<MelonListDto> melonPaginationExcel(HashMap<String, Object> params) {
    	return melonDao.melonPaginationExcel(params);
    }
    public List<MelonListDto> melonListDataTable(HashMap<String, Object> params) {
    	return melonDao.melonListDataTable(params);
    }
    public List<MelonListDto> melonDataTableExcel(HashMap<String, Object> params) {
    	return melonDao.melonDataTableExcel(params);
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
    
    public void melonExcelDown2(HttpServletResponse response, Pagination pagination, UserDto userDto) throws IOException {
    	// 현재 날짜를 포맷팅할 패턴
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd HH시mm분");
        String currentDateTime = fileNameFormat.format(new Date());
       
        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MELON_CHART_TOP100(" + currentDateTime + ")");
		
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userDto", userDto);
        int nowPage = (pagination.getPage() - 1) * 10 ;
        pagination.setNowPage(nowPage);
        params.put("pagination", pagination);
        System.out.println(nowPage);
        List<MelonListDto> dataList = melonPaginationExcel(params);
        // 헤더 작성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"순위", "곡 제목", "아티스트", "좋아요"}; // 열 헤더들
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 작성
        int rowNum = 1;
        for (MelonListDto data : dataList) {
        	System.out.println(data);
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getMelonRank());
            row.createCell(1).setCellValue(data.getMelonTitle());
            row.createCell(2).setCellValue(data.getMelonArtist());
            row.createCell(3).setCellValue(data.getMelonLike());

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
    public void melonDataTableExcel(HttpServletResponse response, Pagination pagination, UserDto userDto) throws IOException {
    	// 현재 날짜를 포맷팅할 패턴
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd HH시mm분");
        String currentDateTime = fileNameFormat.format(new Date());
       
        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MELON_CHART_TOP100(" + currentDateTime + ")");
		
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userDto", userDto);
        int nowPage = (pagination.getNowPage() - 1) * 10 ;
        pagination.setNowPage(nowPage);
        params.put("pagination", pagination);
        System.out.println("pagination" + pagination);
        List<MelonListDto> dataList = melonDataTableExcel(params);
        
        XSSFCellStyle titleStyle = getTitleFont(workbook);
		XSSFCellStyle defaultStyle = getDefaultFont(workbook);
        // 헤더 작성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"순위", "곡 제목", "아티스트", "좋아요"}; // 열 헤더들
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(titleStyle);
        }

        // 데이터 작성
        int rowNum = 1;
        for (MelonListDto data : dataList) {
        	System.out.println(data);
            Row row = sheet.createRow(rowNum++);
          
            row.createCell(0).setCellValue(data.getMelonRank());
            row.getCell(0).setCellStyle(defaultStyle);
            row.createCell(1).setCellValue(data.getMelonTitle());
            row.getCell(1).setCellStyle(defaultStyle);
            row.createCell(2).setCellValue(data.getMelonArtist());
            row.getCell(2).setCellStyle(defaultStyle);
            row.createCell(3).setCellValue(data.getMelonLike());
            row.getCell(3).setCellStyle(defaultStyle);

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
    
    public static XSSFCellStyle getTitleFont(Workbook workbook) {
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		font.setBold(true);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}

	public static XSSFCellStyle getDefaultFont(Workbook workbook) {
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontName("맑은 고딕");
		font.setFontHeight((short)180);
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		return style;
	}
}
