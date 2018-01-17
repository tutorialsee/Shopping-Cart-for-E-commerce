package com.tutorialsee.databaseforandroid.database;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tutorialsee.databaseforandroid.cart.Saleablenew;

@SuppressWarnings("serial")
public class ItemDetails implements Saleablenew, Serializable {

	private long nid;
	private int qty;
	private String title;
	private String price;
	private CharSequence cost_price;
	private String body;
	private String care;
	private String size;
	private String size_chart;
	private String thumb_images;
	private String image;
	private String fullimages;
	private String imagePath;
	private String size1;
	private String email;
	private String size_stock;
	private String imagePath1;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ItemDetails(long nid, int qty, String email, String title,
			String price, String image, CharSequence cost_price, String body,
			String care, String size, String size_stock, String size_chart,
			String thumb_images, String fullimages, String imagePath,
			String size1, String imagePath1) {

		this.nid = nid;
		this.title = title;
		this.price = price;
		this.cost_price = cost_price;
		this.body = body;
		this.care = care;
		this.size = size;
		this.image = image;
		this.size_chart = size_chart;
		this.thumb_images = thumb_images;
		this.fullimages = fullimages;
		this.imagePath = imagePath;
		this.size1 = size1;
		this.email = email;
		this.size_stock = size_stock;
		this.qty = qty;
		this.imagePath1 = imagePath1;

	}

	public String getSizeselected() {
		return size1;
	}

	public void setSizeselected(String size) {
		this.size1 = size;
	}

	public String getCare() {
		return care;
	}

	public void setCare(String care) {
		this.care = care;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public ItemDetails() {
	}

	public long getNid() {
		return nid;
	}

	public void setNid(long nid) {
		this.nid = nid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CharSequence getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public CharSequence getCost_Price() {
		return cost_price;
	}

	public void setCost_Price(CharSequence cost_price) {
		this.cost_price = cost_price;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize_Stock() {
		return size_stock;
	}

	public void setSize_Stock(String size_stock) {
		this.size_stock = size_stock;
	}

	public String getSize_Chart() {
		return size_chart;
	}

	public void setSize_Chart(String size_chart) {
		this.size_chart = size_chart;
	}

	public String getThumb_Images() {
		return thumb_images;
	}

	public void setThumb_Images(String thumb_images) {
		this.thumb_images = thumb_images;
	}

	public String getFullImages() {
		return fullimages;
	}

	public void setFullImages(String fullimages) {
		this.fullimages = fullimages;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public BigDecimal getPrice1() {
		BigDecimal sPrice = new BigDecimal(price);
		return (BigDecimal) sPrice;
	}

	@Override
	public String getName1() {
		return title;
	}

	private String max;

	public String getmax() {
		return max;
	}

	public void setmax(String string) {
		this.max = string;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath) {
		this.imagePath1 = imagePath;
	}

}