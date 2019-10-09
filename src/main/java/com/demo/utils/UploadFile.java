package com.demo.utils;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 苏若墨
 */
public class UploadFile {
    public int uploadUser(MultipartFile file, String fileName) throws IOException {
        UserMapper userMapper = null;
        boolean isExcel2003 = true;
        int str = 0;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            //文件上传成功
            str = 1;
        } else {
            //上传失败sheet为空
            str = 0;
        }
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

            //从excel第二行开始获取每个单元格的数据

            String userId = row.getCell(0).getStringCellValue();

            String userName = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String phoneNumber = row.getCell(3).getStringCellValue();
            String sex = row.getCell(4).getStringCellValue();

            User user = new User();
            user.setUserId(userId);
            user.setEmail(email);
            user.setPhonenumber(phoneNumber);
            user.setSex(sex);
            user.setUserName(userName);
            user.setPassword("xMpCOKC5I4INzFCab3WEmw==");
            //保存
            userMapper.insert(user);
        }
        return str;
    }
}
