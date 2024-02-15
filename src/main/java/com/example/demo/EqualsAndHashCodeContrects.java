package com.example.demo;

public class EqualsAndHashCodeContrects {
	public static void main(String[] args) {
		employee emp1=new employee(1,"mohit");
		employee emp2=new employee(1,"mohit");
		System.out.println(emp1);
		System.out.println(emp2);
		System.out.println(emp1.equals(emp2));
	}
}

class employee{
	private int empId;
	private String empName;
	public employee(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}
	public boolean equals(Object obj) {
		employee emp=(employee) obj;	
		if(emp.empId==this.empId) {
			return true;
		}else {
			return false;
		}
	}
	
	public int hashCode() {
		return empId+this.empName.hashCode();
	}
}