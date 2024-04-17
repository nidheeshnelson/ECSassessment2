package com.mysql.sqlandexcel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class SqlModel {
@Id
@GeneratedValue
private int id;
private String name;
private int admission;
private double physics;
private double chemistry;
private double maths;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAdmission() {
	return admission;
}
public void setAdmission(int admission) {
	this.admission = admission;
}
public double getPhysics() {
	return physics;
}
public void setPhysics(double physics) {
	this.physics = physics;
}
public double getChemistry() {
	return chemistry;
}
public void setChemistry(double chemistry) {
	this.chemistry = chemistry;
}
public double getMaths() {
	return maths;
}
public void setMaths(double maths) {
	this.maths = maths;
}
@Override
public String toString() {
	return "SqlModel [id=" + id + ", name=" + name + ", Admission=" + admission + ", physics=" + physics
			+ ", chemistry=" + chemistry + ", maths=" + maths + "]";
}

}
