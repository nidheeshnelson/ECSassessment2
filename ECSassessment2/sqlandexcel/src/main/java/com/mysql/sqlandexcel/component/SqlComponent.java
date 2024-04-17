package com.mysql.sqlandexcel.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.sqlandexcel.model.SqlModel;
import com.mysql.sqlandexcel.repository.SqlRepository;

@Component
public class SqlComponent {
	@Autowired
	private SqlRepository sr;
public int checkAdmission(int admission) {
	SqlModel sm = sr.findByAdmission(admission);
	if(sm!=null) 
		return sm.getId();
	else
		return 0;
}
}
