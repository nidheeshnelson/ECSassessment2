package com.mysql.sqlandexcel.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.sqlandexcel.model.SqlModel;
import com.mysql.sqlandexcel.repository.SqlRepository;

@Service
public class SqlService {

	@Autowired
	private SqlRepository sr;
	
	public List<SqlModel> getData(MultipartFile file)
	{
		System.out.println("in getdata");
		List<SqlModel> rowdata = new ArrayList<>();
        // Create a Workbook from the Excel file
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) 
        {
        	System.out.println("in try");
            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowIndex = sheet.getLastRowNum();
            for (int i=1;i <= lastRowIndex;i++) {
                Row row = sheet.getRow(i);
                if(row != null) {
                	SqlModel sm = new SqlModel();
                	System.out.println("in validated if");
                sm.setName(row.getCell(0).toString());
                int a = Integer.parseInt(row.getCell(1).toString().split("\\.")[0]);
                System.out.println("admission is "+a);
                sm.setAdmission(a);
                sm.setPhysics(row.getCell(2).getNumericCellValue());
                sm.setChemistry(row.getCell(3).getNumericCellValue());
                sm.setMaths(row.getCell(4).getNumericCellValue());
                System.out.println(sm);
                rowdata.add(sm);
                sr.save(sm);
                }
                
            }
            System.out.println(rowdata);
        }
            catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return rowdata;
    }
	public String getJson(String type, String word) {
		String json="";
		List<SqlModel> lsm = new ArrayList<SqlModel>();
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("In getJson");
		if(type.equals("name")) {
			lsm=sr.findByName(word);
			System.out.println("In name if"+lsm);
			try {
				json=objectMapper.writeValueAsString(lsm);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type.equals("admissionNumber")) {
			int a = Integer.parseInt(word);
			lsm=sr.findByAdmission(a);
			System.out.println("In admissionNumber else if"+lsm);
			try {
				json=objectMapper.writeValueAsString(lsm);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Not defined");
		}
		System.out.println("json is"+json);
		return json;
	}
}
