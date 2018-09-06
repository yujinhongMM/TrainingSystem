
package com.yjh.practice.utils;
/**
 * 
 * Description ��ֹSQLע�빤����
 * @author YJH
 * @date 2018��6��2��  
 *
 */
public class ValidateUtils {
	/**
	 * <p>Title: validate</p>
	 * <p>Description: ��ֹSQLע��</p>
	 * @param str Ҫ���˵��ַ�����һ��Ϊҳ���ȡ�Ĳ���ֵ
	 * @return ����зǷ��ַ����򷵻�true,û�зǷ��ַ�������ʾfalse
	 */
	public static boolean validate(String pageParameter) {
		boolean flag = false ;
		String inj_str = "|insert|select|delete|update|drop|alter|count|"
				+ "declare|or";
		String pageParameter2 = pageParameter.toLowerCase();
		String inj_stra[] = inj_str.split("\\|"); 
		for(String i : inj_stra) {
			if (pageParameter2.indexOf(i) > 0) {
				flag = true ;
			} 
		}
		return flag ;
	}
}
