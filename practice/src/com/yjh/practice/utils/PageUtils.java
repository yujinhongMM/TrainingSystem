package com.yjh.practice.utils;

/**
 * 
 * Description ��ҳ������
 * @author YJH
 * @date 2018��6��4��  
 *
 */
public class PageUtils {
	private int pageNow;//��ǰҳ��
	private int pageSize;//ÿҳ��ʾ����
	private int totalPage;//��ҳ��
	private int totalSize;//��¼����
	@SuppressWarnings("unused")
	private boolean hasFirst;//�Ƿ�����ҳ
	@SuppressWarnings("unused")
	private boolean hasPre;//�Ƿ�����һҳ
	@SuppressWarnings("unused")
	private boolean hasNext;//�Ƿ�����һҳ
	@SuppressWarnings("unused")
	private boolean hasLast;//�Ƿ���βҳ
	
	//���ù��췽��Ϊ������ֵ
	public PageUtils(int pageNow, int totalSize){
	this.pageNow=pageNow;
	this.totalSize=totalSize;
	}
	
	//��ǰҳ��
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
	//һҳ�����ʾ��¼��
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//һ������ҳ
	public int getTotalPage() {
		if (getTotalSize() < getPageSize()) {
			return 1;
		}
		else {
			totalPage=getTotalSize()/getPageSize();
			 if(totalSize%pageSize!=0){
				 totalPage++;
			 }
			 return totalPage;
		}
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	

	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	//�����ǰΪ��һҳ����û����ҳ��
	public boolean isHasFirst() {
		if(pageNow==1)
			return false;
		else
			return true;
	}
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	
	//�������ҳ������ǰһҳ��
	public boolean isHasPre() {
		if(this.isHasFirst())
			return true;
		else 
			return false;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	
	//�����βҳ��������һҳ��
	public boolean isHasNext() {
		if(this.isHasLast())
			return true;
		else
			return false;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	//����������һҳ������βҳ��
	public boolean isHasLast() {
		 if(pageNow==this.getTotalPage())
			 return false;
		 else 
			 return true;
	}
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

}
