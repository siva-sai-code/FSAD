package com.bookexchange.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;
	@Column(name = "book_title", nullable = false)
	private String title;
	@Column(name = "book_author", nullable = false)
	private String author;
	@Column(name = "book_genre")
	private String genre;
	@Column(name = "user_id")
	private long userId;
	private String available;
	private String location;
	private String bookCondition;
	// @Column(name = "user_id")
	// private long userId;

	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "id") private User user;
	 */

	public Book() {
		super();
	}

	public Book(Long id, String title, String author, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public Book(Long id, String title, String author, String genre, long userId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.userId = userId;
	}

	public Book(Long id, String title, String author, String genre, long userId, String available, String location,
			String bookCondition) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.userId = userId;
		this.available = available;
		this.location = location;
		this.bookCondition = bookCondition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the bookCondition
	 */
	public String getBookCondition() {
		return bookCondition;
	}

	/**
	 * @param bookCondition the bookCondition to set
	 */
	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", userId=" + userId
				+ ", available=" + available + ", location=" + location + ", bookCondition=" + bookCondition + "]";
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 * 
	 * @Override public String toString() { return "Book [id=" + id + ", title=" +
	 * title + ", author=" + author + ", genre=" + genre + ", user=" + user + "]"; }
	 */

}
