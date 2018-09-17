package com.yz.pssBeans;

public class PanResult {

	private Integer id;
	private String searchItem;
	private int searchDeepth;
	private String sourceUrl;
	private String panUrl;
	private String password;
	
	public PanResult(String searchItem, int searchDeepth, String sourceUrl, String panUrl,
			String password) {
		this.searchItem = searchItem;
		this.searchDeepth=searchDeepth;
		this.sourceUrl = sourceUrl;
		this.panUrl = panUrl;
		this.password = password;
	}
	public PanResult() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSearchItem() {
		return searchItem;
	}
	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}
	public int getSearchDeepth() {
		return searchDeepth;
	}
	public void setSearchDeepth(int searchDeepth) {
		this.searchDeepth = searchDeepth;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getPanUrl() {
		return panUrl;
	}
	public void setPanUrl(String panUrl) {
		this.panUrl = panUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "PanResult [id=" + id + ", searchItem=" + searchItem
				+ ", searchDeepth=" + searchDeepth + ", sourceUrl=" + sourceUrl
				+ ", panUrl=" + panUrl + ", password=" + password + "]";
	}
}
