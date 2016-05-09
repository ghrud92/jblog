package com.estsoft.jblog.vo;

public class CategoryVo {
	private Long no;
	private Long blog_no;
	private String name;
	private String description;
	private Integer posting;
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", blog_no=" + blog_no + ", name=" + name + ", description=" + description
				+ ", posting=" + posting + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(Long blog_no) {
		this.blog_no = blog_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
