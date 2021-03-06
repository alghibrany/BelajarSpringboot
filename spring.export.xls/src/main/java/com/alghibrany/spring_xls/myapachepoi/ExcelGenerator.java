package com.alghibrany.spring_xls.myapachepoi;

import com.alghibrany.spring_xls.dao.SiswaDao;
import com.alghibrany.spring_xls.model.Siswa;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;

import java.io.*;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelGenerator {

    @Autowired
    private SiswaDao siswaDao;

    /* export */
    public ByteArrayInputStream exportExcel(List<Siswa> siswas) throws Exception{
        String[] columns = {"Id", "Name", "Kelas", "Jurusan"};
        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data Siswa");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
             //FileInputStream obtains input bytes from the image file
            InputStream inputStream = new FileInputStream("D:/Project/BelajarSpringboot/spring.export.xls/src/main/resources/templates/itachi.png");   
            byte[] bytes = IOUtils.toByteArray(inputStream);
            //Adds a picture to the workbook
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            //close the input stream
            inputStream.close();        
            CreationHelper helper = workbook.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            //Row ofor Header
            Row headerRow = sheet.createRow(0);

                        
            //create an anchor with upper left cell _and_ bottom right cell
            anchor.setCol1(1); //Column B
            anchor.setRow1(1); //Row 3
            anchor.setCol2(2); //Column C
            anchor.setRow2(3); //Row 4

            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);

            //Reset the image to the original size
            //pict.resize(); //don't do that. Let the anchor resize the image!
  //Create the Cell B3
  Cell cell = sheet.createRow(0).createCell(3);
            //Header
            // for(int i=0;i<columns.length;i++) {
            //     Cell cell = headerRow.createCell(i);
            //     cell.setCellValue(columns[i]);
            //     cell.setCellStyle(headerCellStyle);
            // }

            // int rowIdx = 1;
            // for(Siswa siswa : siswas) {
            //     Row row = sheet.createRow(rowIdx);

            //     row.createCell(0).setCellValue(siswa.getId());
            //     row.createCell(1).setCellValue(siswa.getName());
            //     row.createCell(2).setCellValue(siswa.getKelas());
            //     row.createCell(3).setCellValue(siswa.getJurusan());
            //     rowIdx++;
            // }

            workbook.write(out);
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());
        }catch(Exception e) {

        }
        return null;
    }

    /* Import */
    public void importExcel(MultipartFile file) throws Exception{

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for(int i=0;i<(CoutRowExcel(sheet.rowIterator()));i++) {
            if(i == 0) {
                continue;
            }

            Row row = sheet.getRow(i);

            String nama = row.getCell(1).getStringCellValue();
            String kelas = row.getCell(2).getStringCellValue();
            String jurusan = row.getCell(3).getStringCellValue();

            siswaDao.save(new Siswa(0, nama, kelas, jurusan));
        }

    }

    /* Cout Row of Excel Table */
    public static int CoutRowExcel(Iterator<Row> iterator) {
        int size = 0;
        while(iterator.hasNext()) {
            Row row = iterator.next();
            size++;
        }
        return size;
    }
}
