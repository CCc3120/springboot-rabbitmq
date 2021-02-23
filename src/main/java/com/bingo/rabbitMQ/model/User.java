package com.bingo.rabbitMQ.model;

import java.io.Serializable;

public class User implements Serializable {
	private Integer id;
	private String username;
	private String age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
	}

	public User(Integer id, String username, String age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public User() {
		super();
	}

}
