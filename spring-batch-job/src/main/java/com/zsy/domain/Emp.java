package com.zsy.domain;

import java.util.Date;

import javax.validation.constraints.Size;

public class Emp {
	
	 private Long empno   ;
	 @Size(max=10,min=2)
	 private String ename   ;
	 private String job     ;
	 private Long mgr      ;
	 private Date hiredate ;
	 private Float sal      ;
	 private Float comm     ;
	 private Long deptno   ;
	public Long getEmpno() {
		return empno;
	}
	public void setEmpno(Long empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Long getMgr() {
		return mgr;
	}
	public void setMgr(Long mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Float getSal() {
		return sal;
	}
	public void setSal(Float sal) {
		this.sal = sal;
	}
	public Float getComm() {
		return comm;
	}
	public void setComm(Float comm) {
		this.comm = comm;
	}
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	 
}
