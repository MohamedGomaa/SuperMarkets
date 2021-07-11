package com.supermarket.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;


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
    private Users superMarketModerator;

    @OneToMany(mappedBy = "theMarket")
	private Set<Item> theMarketMenu;

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

	public Users getSuperMarketModerator() {
		return superMarketModerator;
	}

	public void setSuperMarketModerator(Users superMarketModerator) {
		this.superMarketModerator = superMarketModerator;
	}

	public Set<Item> getTheMarketMenu() {
		return theMarketMenu;
	}

	public void setTheMarketMenu(Set<Item> theMarketMenu) {
		this.theMarketMenu = theMarketMenu;
	}
}
