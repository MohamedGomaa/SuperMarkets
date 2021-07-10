package com.supermarket.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.catalina.User;

@Entity
public class SuperMarket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long superMarketId;

	@NotNull
	@Pattern(regexp = "/^[A-Za-z]+([\\ A-Za-z]+)*/")
	String englishName;

	@NotNull
	@Pattern(regexp = "^[\\u0621-\\u064A\\u0660-\\u0669 ]+$")
	String arabicName;

	@NotNull
	String superMarketAddress;

	@Lob
	private byte[] superMarketImage;
	
    @OneToOne(mappedBy = "theSuperMarket")
    private User superMarketModerator;

	public SuperMarket() {

	}

	public SuperMarket(String englishName, String arabicName, String superMarketAddress, byte[] superMarketImage) {
		this.englishName = englishName;
		this.arabicName = arabicName;
		this.superMarketAddress = superMarketAddress;
		this.superMarketImage = superMarketImage;
	}

	public Long getSuperMarketId() {
		return superMarketId;
	}

	public void setSuperMarketId(Long superMarketId) {
		this.superMarketId = superMarketId;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getArabicName() {
		return arabicName;
	}

	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}

	public String getSuperMarketAddress() {
		return superMarketAddress;
	}

	public void setSuperMarketAddress(String superMarketAddress) {
		this.superMarketAddress = superMarketAddress;
	}

	public byte[] getSuperMarketImage() {
		return superMarketImage;
	}

	public void setSuperMarketImage(byte[] superMarketImage) {
		this.superMarketImage = superMarketImage;
	}

	public User getSuperMarketModerator() {
		return superMarketModerator;
	}

	public void setSuperMarketModerator(User superMarketModerator) {
		this.superMarketModerator = superMarketModerator;
	}

	
}
