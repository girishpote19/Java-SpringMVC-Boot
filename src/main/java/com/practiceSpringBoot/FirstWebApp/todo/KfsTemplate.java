package com.practiceSpringBoot.FirstWebApp.todo;

public class KfsTemplate {

	private String productName;
	private String selectedLanguage;
	private String kfsTextContent;

	// Getters and Setters

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSelectedLanguage() {
		return selectedLanguage;
	}

	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}

	public String getKfsTextContent() {
		return kfsTextContent;
	}

	public void setKfsTextContent(String kfsTextContent) {
		this.kfsTextContent = kfsTextContent;
	}
}