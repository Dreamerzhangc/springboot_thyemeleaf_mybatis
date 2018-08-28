package cn.edu.nyist.springboot07_jsp_mybatis.model;

public class Admin {
private String name;
private String pwd;
private int id;
public Admin() {
	super();
}
public Admin(String name, String pwd, int id) {
	super();
	this.name = name;
	this.pwd = pwd;
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "Admin [name=" + name + ", pwd=" + pwd + ", id=" + id + "]";
}
}
