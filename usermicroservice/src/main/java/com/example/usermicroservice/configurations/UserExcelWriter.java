package com.example.usermicroservice.configurations;

import com.example.usermicroservice.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class UserExcelWriter implements ItemWriter<User> {

//    WRITE TO EXCEL
    @Override
    public void write(Chunk<?extends User>chunk)throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

//        HEADER ROW
        Row headerRow = sheet.createRow(0);
        String[] columns = {"User_ID", "Username", "Email", "Password", "Phone Number"};
        for (int i = 0; i< columns.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

//        WRITING THE DATA
        int rowNum = 1;
        for (User user: chunk){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getUser_id());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPassword_hash());
            row.createCell(4).setCellValue(user.getPhone_number());
        }

//        CREATING THE EXCEL FILE
        try(FileOutputStream fileOut = new FileOutputStream("users_service.xlsx")){
            workbook.write(fileOut);
        }

        workbook.close();
        System.out.print("Excel file created: users_service.xlsx");
    }
}
