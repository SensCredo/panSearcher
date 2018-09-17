package com.yz.pssDao;

import java.util.List;

import com.yz.pssBeans.PanResult;

public interface PanDao {

	List<PanResult> selectResult(String searchItem);
	
	String selectItemByLink(String panUrl);
	
	int selectDeepthByItem(String searchItem);
	
	void updateDeepthByItem(int searchDeepth,String searchItem);
	
	void addPanResult(PanResult result);
	
	
}
