package com.yjh.practice.model;

import java.sql.Date;

/**
 * 
 * Description
 * @author YJH
 * @date 2018��6��4��  
 *
 */

public class ProjectSelect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectSelectId id;//ProjectSelectId ������ѧ��Id����ĿId
	private String selReason;//ѡ������
	private Date companySelDate;//��ҵѡ������
	private String score;//�ɼ�
	private String companyName;//��˾����

	// Constructors

	/** default constructor */
	public ProjectSelect() {
	}

	/** minimal constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companyName = companyName;
	}

	/** full constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			Date companySelDate, String score, String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companySelDate = companySelDate;
		this.score = score;
		this.companyName = companyName;
	}

	// Property accessors

	public ProjectSelectId getId() {
		return this.id;
	}

	public void setId(ProjectSelectId id) {
		this.id = id;
	}

	public String getSelReason() {
		return this.selReason;
	}

	public void setSelReason(String selReason) {
		this.selReason = selReason;
	}

	public Date getCompanySelDate() {
		return this.companySelDate;
	}

	public void setCompanySelDate(Date companySelDate) {
		this.companySelDate = companySelDate;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}