package com.yz.pssService;

import java.util.List;

import com.yz.pssBeans.PanResult;

public interface PanService {
	List<PanResult> searchPan(String searchItem,int searchDeepth) throws Exception;
}
