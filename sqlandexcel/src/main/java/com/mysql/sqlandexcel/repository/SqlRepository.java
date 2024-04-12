package com.mysql.sqlandexcel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysql.sqlandexcel.model.SqlModel;
@Repository
public interface SqlRepository extends JpaRepository<SqlModel, Integer> {
	 List<SqlModel> findByAdmission(int admission);
     List<SqlModel> findByName(String name);
}
