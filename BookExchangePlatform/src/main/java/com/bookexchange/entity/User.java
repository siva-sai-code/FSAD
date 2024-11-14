package com.bookexchange.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	private String password;
	private String fullname;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private List<Book> booklist = new ArrayList<>();

	public User() {
		super();
	}

	public User(String email, String password, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}

	public User(Long id, String email, String password, String fullname, List<Book> booklist) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.booklist = booklist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullname=" + fullname
				+ ", booklist=" + booklist + "]";
	}

}
