package com.estsoft.jblog.vo;

public class BlogVo {
	private Long blog_no;
	private String user_id;
	private String blog_name;
	private String img;
	private Long category_no;
	private String category_name;
	private String description;
	private Integer posting;
	private Long no;	// post no
	private String title;
	private String content;
	private String reg_date;
	@Override
	public String toString() {
		return "BlogVo [blog_no=" + blog_no + ", user_id=" + user_id + ", blog_name=" + blog_name + ", img=" + img
				+ ", category_no=" + category_no + ", category_name=" + category_name + ", description=" + description
				+ ", posting=" + posting + ", no=" + no + ", title=" + title + ", content=" + content + ", reg_date="
				+ reg_date + "]";
	}
	public Long getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(Long blog_no) {
		this.blog_no = blog_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBlog_name() {
		return blog_name;
	}
	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPosting() {
		return posting;
	}
	public void setPosting(Integer posting) {
		this.posting = posting;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}
