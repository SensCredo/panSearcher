package com.yz.pssHandler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.pssBeans.PanResult;
import com.yz.pssService.PanService;

@Controller
@RequestMapping("/handler")
public class PanController {
	//配置自动注入
	@Autowired
	@Qualifier("panService")
	private PanService service;
	
	public void setService(PanService service) {
		this.service = service;
	}
	@RequestMapping("/search.do")
	public ModelAndView doSearch(String searchItem,int searchDeepth,HttpSession session) throws Exception {
		ModelAndView mv=new ModelAndView();
		searchItem=searchItem.trim();
		List<PanResult> results=null;
		List<PanResult> partResults=null;
		int pageSize=5;
		int currentPage=1;
		results = service.searchPan(searchItem, searchDeepth);
		//无结果，跳转至界面noResult.jsp
		if(results.size()==0){
			mv.addObject("searchItem", searchItem);
			mv.setViewName("/WEB-INF/noResult.jsp");
			return mv;
		}
		//正确，进入结果展示界面result.js		
		//每页展示五组数据，若超出则分页展示
		int totalPage=1;
		//若结果数为pageSize倍数，totalPage无需加1
		if((results.size()%pageSize)==0)
			totalPage=results.size()/pageSize;
		else
			totalPage=results.size()/pageSize+1;
		if(totalPage>1)
			partResults=results.subList(0,pageSize);
		else
			partResults=results;
		session.setAttribute("searchItem", searchItem);
		session.setAttribute("results", results);
		session.setAttribute("partResults", partResults);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("totalPage", totalPage);
		mv.setViewName("/WEB-INF/result.jsp");
		return mv;	
	}
	
	@RequestMapping("/show.do")
	public ModelAndView doShow(int currentPage,ModelAndView mv,HttpSession session){
		int pageSize=5;
		List<PanResult> partResults=null;
		List<PanResult> results=(List<PanResult>) session.getAttribute("results");
		int totalPage=results.size()/pageSize+1;
		//取出当前页所要展示的结果
		if(currentPage>=totalPage){
			partResults=results.subList((currentPage-1)*pageSize,results.size());
		}
		else
			partResults=results.subList((currentPage-1)*pageSize,currentPage*pageSize);
		session.setAttribute("partResults", partResults);
		session.setAttribute("currentPage", currentPage);
		mv.setViewName("/WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping("/exit.do")
	public ModelAndView doExit(ModelAndView mv,HttpSession session){
		mv.clear();
		session.invalidate();
		mv.setViewName("redirect:/search.jsp");
		return mv;
	}
}
