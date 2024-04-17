package com.mysql.sqlandexcel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.sqlandexcel.model.SqlModel;
import com.mysql.sqlandexcel.service.SqlService;
import jakarta.servlet.http.HttpSession;

@Controller
public class SqlControl {
	@Autowired
	private SqlService ss;
	@GetMapping("/")
	public String show() {
		System.out.println("in /");
		return "forexcel";
	}
	@PostMapping("/excelupload")
	public String excelUpload(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
		System.out.println("in excelupload");
		 if (!file.isEmpty()) {
	         // Process the file
			 System.out.println("in if ");
			List<SqlModel> ls= ss.getData(file);
			 ObjectMapper objectMapper = new ObjectMapper();
		        try {
		            String json = objectMapper.writeValueAsString(ls);
		            System.out.println("JSON format "+json);
		            session.setAttribute("data", json);
//		            model.addAttribute("studentsJson", json);
		        } catch (Exception e) {
		            e.printStackTrace();
		            
		        }
			 System.out.println("near return");
			 return "redirect:/showexcelm";
	     } else {
	    	 return "error";
	     }
	}
	@GetMapping("/showexcelm")
	public String getData(Model model, HttpSession session) {
		model.addAttribute("studentsJson", session.getAttribute("data"));
		System.out.println("showexcelm"+model);
		return "showexcel";
	}
	@PostMapping("/search")
	public String search (@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword, HttpSession session){
		System.out.println(searchType+" in search "+keyword);
		String json = ss.getJson(searchType, keyword);
		System.out.println("In controller json1 is "+json);
		session.setAttribute("fromsql", json);
		return "redirect:/searchresult1";
	}
	@GetMapping("/searchresult1")
	public String getDataSql(Model model, HttpSession session) {
		model.addAttribute("json1", session.getAttribute("fromsql"));
		System.out.println("searchresult1"+model);
		return "searchresult";
	}
}
