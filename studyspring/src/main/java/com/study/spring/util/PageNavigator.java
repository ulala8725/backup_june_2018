package com.study.spring.util;

/**
 * クラスの説明.
 * <pre>
 * 掲示板のページング処理クラス
 * </pre>
 * @author kim.sunhye
 */
public class PageNavigator {
	//ページ関連情報
	private int countPerPage;		//ページ当たりポスティング数
	private int pagePerGroup;		//グループ当たりページ数
	private int currentPage;		//現在のページ（最新のポスティングは１からはじまる）
	private int totalRecordsCount;	//全体のポスティング数
	private int totalPageCount;		//全体のページ数
	private int currentGroup;		//現在のグループ（最新のグループは0から始まる）
	private int startPageGroup;		//現在のグループの最初ページ
	private int endPageGroup;		//現在のグループの最後ページ
	private int startRecord;		//全体のレコードの中で現在のページの最初ポスティングの位置（0から始まる）
	
	/*
	 * コンストラクタ
	 */
	public PageNavigator(int countPerPage, int pagePerGroup, int currentPage, int totalRecordsCount) {
		//ページ当たりポスティング数、グループ当たりページ数、現在のページ、全体のポスティング数を渡される。
		this.countPerPage = countPerPage;
		this.pagePerGroup = pagePerGroup;
		this.totalRecordsCount = totalRecordsCount;
		
		//全体のページ数
		totalPageCount = (totalRecordsCount + countPerPage - 1) / countPerPage;

		//渡された現在のページが1より小さいなら現在のページを１ページに指定。
		if (currentPage < 1)	currentPage = 1;
		//渡された現在のページが最後のページより大きいなら現在のページを最後のページに指定。
		if (currentPage > totalPageCount)	currentPage = totalPageCount;
		
		this.currentPage = currentPage;

		//現在のグループ
		currentGroup = (currentPage - 1) / pagePerGroup;
		
		//現在のグループの最初ページ
		startPageGroup = currentGroup * pagePerGroup + 1;
		//現在のグループの最初ページが1より小さいなら１に処理。
		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		//現在のグループの最後ページ
		endPageGroup = startPageGroup + pagePerGroup - 1;
		//現在のグループの最後ページが全体ページより小さいなら全体ページを最後に処理。
		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;

		//全体のレコードの中で現在のページの最初ポスティングの位置
		startRecord = (currentPage - 1) * countPerPage;			
	}

	/*
	 * Getters and Setters
	 */
	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public void setPagePerGroup(int pagePerGroup) {
		this.pagePerGroup = pagePerGroup;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(int currentGroup) {
		this.currentGroup = currentGroup;
	}

	public int getStartPageGroup() {
		return startPageGroup;
	}

	public void setStartPageGroup(int startPageGroup) {
		this.startPageGroup = startPageGroup;
	}

	public int getEndPageGroup() {
		return endPageGroup;
	}

	public void setEndPageGroup(int endPageGroup) {
		this.endPageGroup = endPageGroup;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	@Override
	public String toString() {
		return "PageNavigator [countPerPage=" + countPerPage + ", pagePerGroup=" + pagePerGroup + ", currentPage="
				+ currentPage + ", totalRecordsCount=" + totalRecordsCount + ", totalPageCount=" + totalPageCount
				+ ", currentGroup=" + currentGroup + ", startPageGroup=" + startPageGroup + ", endPageGroup="
				+ endPageGroup + ", startRecord=" + startRecord + "]";
	}
	
}

