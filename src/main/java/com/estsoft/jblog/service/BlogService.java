package com.estsoft.jblog.service;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	private final String SAVE_PATH = "/Users/hokyung/temp";
	
	public Long defaultInsert(String id){
		// 새로운 블로그 생성
		
		BlogVo vo = new BlogVo();
		vo.setUser_id(id);
		vo.setBlog_name("");
		vo.setImg("/jblog/assets/images/spring-logo.jpg");
		
		Long no = blogDao.insert(vo);
		
		return no;
	}
	
	public BlogVo get(String id){
		BlogVo vo = new BlogVo();
		vo.setUser_id(id);
		vo = blogDao.get(vo);
		return vo;
	}
	
	public String updateBlog(BlogVo vo, MultipartFile file){
		
		// 파일이 비어있는 경우
		if(file.isEmpty()){
			vo.setImg("");
		}
		// 파일이 있는 경우
		else{
			String fileOriginalName = file.getOriginalFilename();
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length());
			String saveFileName = genSaveFileName(extName);
			
			writeFile(file, SAVE_PATH, saveFileName);
			
			vo.setImg("/jblog/product-images/" + saveFileName);
		}
		
		blogDao.update(vo);
		return vo.getImg();
	}
	
	private void writeFile(MultipartFile file, String path, String fileName){
		FileOutputStream fos = null;
		try{
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + "/" + fileName);
			fos.write(fileData);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(fos != null){
				try{
					fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private String genSaveFileName(String extName){
		Calendar calendar = Calendar.getInstance();
		String fileName = "";
		
		fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
		
		return fileName;
	}
}
