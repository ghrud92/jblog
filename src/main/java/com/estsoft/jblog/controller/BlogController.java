package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.annotation.AuthUser;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping("/{id}")
	public String main(
			@PathVariable("id") String id,
			@RequestParam(value="cno", defaultValue="0") Long cno,
			@RequestParam(value="pno", defaultValue="0") Long pno,
			Model model){
		
		BlogVo blogVo =  blogService.get(id);
		
		List<CategoryVo> categoryList = categoryService.getList(blogVo.getBlog_no());
		
		List<PostVo> postList = null;
		if(!categoryList.isEmpty()){
			if(cno == 0){
				CategoryVo categoryVo = categoryList.get(0);
				cno = categoryVo.getNo();
			}
			postList = postService.getList(cno);
		}
		
		if(pno == 0 && !postList.isEmpty()){
			PostVo pVo = postList.get(0);
			pno = pVo.getNo();
		}
		PostVo postVo = postService.get(pno);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/deletepost")
	public String deletePost(
			@PathVariable("id") String id,
			@RequestParam("no") Long no){
		
		postService.delete(no);
		
		return "redirect:/blog/" + id;
	}
	
	@RequestMapping("/{id}/manage")
	public String admin(
			@PathVariable("id") String id,
			Model model){
		
		BlogVo blogVo =  blogService.get(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/category")
	public String category(
			@PathVariable("id") String id,
			Model model){

		BlogVo blogVo =  blogService.get(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/{id}/category/ajax-list")
	@ResponseBody
	public Map<String, Object> ajaxCategoryList(
			@PathVariable("id") String id){

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "fail");
		
		BlogVo blogVo =  blogService.get(id);

		map.replace("result", "success");
		List<CategoryVo> list = categoryService.getList(blogVo.getBlog_no());
		map.put("data", list);
		
		return map;
	}
	
	@RequestMapping("/{id}/category/ajax-insert")
	@ResponseBody
	public Map<String, Object> ajaxInsertCategory(
			@ModelAttribute CategoryVo vo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		vo = categoryService.insert(vo);
		map.put("result", "success");
		map.put("data", vo);
		
		return map;
	}
	
	@RequestMapping("/{id}/category/ajax-delete")
	@ResponseBody
	public Map<String, Object> ajaxDeleteCategory(
			@RequestParam("no") Long no){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "fail");
		
		categoryService.delete(no);
		map.replace("result", "success");
		
		return map;
	}
	
	@RequestMapping("/{id}/writeform")
	public String writeForm(
			@PathVariable("id") String id,
			Model model){

		BlogVo blogVo =  blogService.get(id);
		
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> categoryList = categoryService.getList(blogVo.getBlog_no());
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping("/{id}/write")
	public String write(
			@PathVariable("id") String id,
			@Valid @ModelAttribute PostVo vo,
			BindingResult result,
			Model model){

		BlogVo blogVo =  blogService.get(id);
		
		// Valid 채크
		if (result.hasErrors()) {
			List<CategoryVo> categoryList = categoryService.getList(blogVo.getBlog_no());
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("postVo", vo);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute(result.getModel());
			return "blog/blog-admin-write";
		}
		
		// write 부분
		postService.write(vo);
		
		return "redirect:/blog/"+id;
	}
	
	@RequestMapping("{id}/modifyblog")
	public String modifyBlog(
			@PathVariable("id") String id,
			@ModelAttribute BlogVo vo,
			@RequestParam("imgfile") MultipartFile file){
		
		blogService.updateBlog(vo, file);
		
		return "redirect:/blog/" + id;
	}
	
	@RequestMapping("{id}/ajax-imgmodify")
	public Map<String, Object> ajaxImgModify(
			@RequestParam("imgfile") MultipartFile file){

		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("in ajax " + file);
		
		return map;
	}
}
