package com.yz.pssService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yz.pssBeans.PanResult;
import com.yz.pssDao.PanDao;

public class PanServiceImpl implements PanService {
	
	private PanDao dao;
	private List<String> panReg=new ArrayList<String>();
	private List<Pattern> panPat=new ArrayList<Pattern>();
	private Pattern passwordPat;
	private Pattern partPanPat1;
	private Pattern partPanPat2;
	
	public PanServiceImpl() {
	}
	
	public void setDao(PanDao dao) {
		this.dao = dao;
	}
	

	@Override
	public List<PanResult> searchPan(String searchItem, int searchDeepth) throws Exception {
		// 首先从数据库查找，若无结果，从网络中进行搜索
		List<PanResult> results=dao.selectResult(searchItem);
		int lastSearcchDeepth=0;
		if(results.size()!=0)
			//读取数据库中该搜索项的搜索深度,若搜索深度小于该次搜索深度，则从网络中进行搜索
			 lastSearcchDeepth=dao.selectDeepthByItem(searchItem);
		if((results.size()==0)||(searchDeepth>lastSearcchDeepth)){
			  PatternInit();
			  Set<String> searchLink=searchLinkSelector(searchItem,searchDeepth);
			  //页数正则匹配
			  String pageNumReg="class=\"red\">\\d+</span>";
			  Pattern pageNumPat=Pattern.compile(pageNumReg);
			  int pageDeepth=1;
			  for (String link : searchLink) {
				  URL url=new URL(link);
				  HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				  BufferedReader context = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				  String line;
				  while((line=context.readLine())!=null){
					  	byte[] charArray = line.getBytes("GBK");
						line=new String(charArray,"utf-8");
						Matcher pageNumMat=pageNumPat.matcher(line);
						if(pageNumMat.find()){
							//页数超过10时取最大页面搜索数为10
							if(pageNumMat.group().matches("\\d\\d+"))
								pageDeepth=10;
							else
								pageDeepth=Integer.parseInt(pageNumMat.group().substring(12, 13));
						}
						PanResult result=searchPanlink(line,link);
						if(result!=null){
							result.setSearchItem(searchItem);
							result.setSearchDeepth(searchDeepth);
							//若该结果在数据库中不存在，则进行数据持久化处理（存储入数据库）
							String searchItemInDB=dao.selectItemByLink(result.getPanUrl());
							if((searchItemInDB==null)||(!searchItemInDB.equals(searchItem))){
								dao.addPanResult(result);
								results.add(result);
							}		
						}		
					}
				  if(pageDeepth>1){
					  for (int i = 2; i <= pageDeepth; i++) {
						  //若不止一页，继续搜索剩余页面
						  link=link+"?pn="+i;
						  URL deepUrl=new URL(link);
						  HttpURLConnection deepConn=(HttpURLConnection) deepUrl.openConnection();
						  BufferedReader deepContext = new BufferedReader(new InputStreamReader(deepConn.getInputStream()));
						  while((line=deepContext.readLine())!=null){
							  byte[] charArray = line.getBytes("GBK");
							  line=new String(charArray,"utf-8");
							  PanResult result=searchPanlink(line,link);
							  if(result!=null){
									result.setSearchItem(searchItem);
									result.setSearchDeepth(searchDeepth);
									String searchItemInDB=dao.selectItemByLink(result.getPanUrl());
									if((searchItemInDB==null)||(!searchItemInDB.equals(searchItem))){
										dao.addPanResult(result);
										results.add(result);
									}
								}	
						  }
					}
				  }
			}
			 if(lastSearcchDeepth!=0){
				//数据库中searchDeepth项记录历史最大搜索深度
					dao.updateDeepthByItem(searchDeepth,searchItem);
			 }			
		}
		return results;
	}
	private void PatternInit() {
		 //标准格式链接
		  panReg.add("pan.baidu.com/s/\\w{8}\\W");
		  panReg.add("pan.baidu.com/s/[\\w-]{23}");
		  panReg.add("share.weiyun.com/\\w{7}");
		  //部分格式链接
		  String partPanReg1="/s/\\w{8}\\W";
		  String partPanReg2="/s/[\\w-]{23}";
		  //密码正则匹配
		  String passwordReg="密码[:：]\\s?\\w{4}";
		  for (String reg : panReg) {
				panPat.add(Pattern.compile(reg));
			}
		  partPanPat1=Pattern.compile(partPanReg1);
		  partPanPat2=Pattern.compile(partPanReg2);
		  passwordPat=Pattern.compile(passwordReg);		  
	}
	
	private Set<String> searchLinkSelector(String searchItem,int searchDeepth) throws Exception{
		Set<String> searchLink=new HashSet<String>();
		searchItem=URLEncoder.encode(searchItem,"utf-8");
		String line;
		for(int i=1;i<=searchDeepth;i++){
			//按深度对搜索得到的网页依次进行查找
			URL url=new URL("http://tieba.baidu.com/f/search/res?ie=utf-8&qw="+searchItem+"&rn=10&un=&only_thread=0&sm=1&sd=&ed=&pn="+i);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			BufferedReader context = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String linkReg="/p/\\d+\\?pid=\\d+&cid=\\d+#\\d+";
			Pattern linkPat=Pattern.compile(linkReg);
			while((line=context.readLine())!=null){
				Matcher linkMat=linkPat.matcher(line);
				while(linkMat.find()){
					//按百度网盘格式补全网页链接后放入searchLink（使用set防止获取重复链接）
					searchLink.add("https://tieba.baidu.com"+linkMat.group().substring(0, 13));
				}
			}
		}
		return searchLink;  
	  }
	
	private PanResult searchPanlink(String line,String link) {
		 boolean findPart=true;
		 PanResult result=null;
		 List<Matcher> panMat=new ArrayList<Matcher>();
		  for (Pattern pat : panPat) {
			panMat.add(pat.matcher(line));
		}
		  for (Matcher panMatcher : panMat) {
			  while(panMatcher.find()){
				  	result=new PanResult();
				  	result.setSourceUrl(link);
				  	if(panMatcher.group().matches("pan.baidu.com/s/\\w{8}\\W"))
				  		result.setPanUrl(panMatcher.group().substring(0,24));
				  	else
				  		result.setPanUrl(panMatcher.group());
					Matcher passwordMat=passwordPat.matcher(line);
					if(passwordMat.find()){
						result.setPassword(passwordMat.group().substring(3));
					}
					findPart=false;
				}
		}
		  if(findPart){	  
				Matcher partPanMat1=partPanPat1.matcher(line);
				Matcher partPanMat2=partPanPat2.matcher(line);
				while(partPanMat1.find()){	
					if(!partPanMat1.group().contains("download")){
						result=new PanResult();
						result.setSourceUrl(link);
						result.setPanUrl("pan.baidu.com"+partPanMat1.group().substring(0,11));
						Matcher passwordMat=passwordPat.matcher(line);
						if(passwordMat.find()){
							result.setPassword(passwordMat.group().substring(3));
						}
					}
				}
				while(partPanMat2.find()){
					result=new PanResult();
					result.setSourceUrl(link);
					result.setPanUrl("pan.baidu.com"+partPanMat2.group());
					Matcher passwordMat=passwordPat.matcher(line);
					if(passwordMat.find()){
						result.setPassword(passwordMat.group().substring(3));
					}
				}
		  }
		return result;  
	}

}
