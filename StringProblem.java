package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringProblem {
	public static void main(String[] args) {
	}
	/**
	 * 判断target串是否为source串的字串
	 */
	public static int strStr(String source, String target) {
		if (target == null || target.length() == 0 || source == null || source.length() == 0
				|| source.length() < target.length())
			return -1;
		for (int i = 0; i <= source.length() - target.length(); i++) {
			int j = i;
			for (; j < target.length(); j++) {
				if (target.charAt(j) != source.charAt(i + j))
					break;
			}
			if (j == target.length())
				return i;
		}
		return -1;
	}
	/**
	 * 判断两个字符串是否互为异构
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean anagram(String s,String t){
		if(s==null||t==null||s.length()!=t.length())
			return false;
		final int ChARNUM=256;
		int[] cnt=new int[ChARNUM];
		for(int i=0;i<s.length();i++){
			cnt[s.charAt(i)]++;
			cnt[t.charAt(i)]--;
		}
		for(int i=0;i<ChARNUM;i++){
			if(cnt[i]!=0)
				return false;
		}
		return true;
	}
	/**
	 * 判断s是否包含t的所有字符，两者均只含有大写字母
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean compareString(String s,String t){
		if(t==null||t.length()==0) return true;
		if(s==null||s.length()==0||s.length()<t.length())
			 return false;
		final int UPPER_NUM=26;
		int[] cnt=new int [UPPER_NUM];
		for(int i=0;i<s.length();i++){
			cnt[s.charAt(i)-'A']++;
		}
		for(int i=0;i<t.length();i++){
			cnt[t.charAt(i)-'A']--;
			if(cnt[t.charAt(i)-'A']<0)
				return false;
		}
		return true;
	}
	/**
	 * 字符串分组
	 * @param strs
	 * @return
	 */
	public static List<List<String>> groupAnagrams(String[] strs){
		List<List<String>> res=new ArrayList<List<String>>();
		Map<String, List<String>> map=new HashMap<String,List<String>>();
		for(int i=0;i<strs.length;i++){
			char[] cs=strs[i].toCharArray();
			Arrays.sort(cs);
			String s=String.valueOf(cs);
			if(map.containsKey(s)){
				map.get(s).add(strs[i]);
			}else{
				List<String> list=new ArrayList<String>();
				list.add(strs[i]);
				map.put(s, list);
			}
		}
		for( List<String> li:map.values()){
			Collections.sort(li);
			res.add(li);
		}
		return res;
	}
	/**
	 * 最长公共子串
	 */
	public static int longestCommonSubstring(String s,String t){
		if(s==null||s.length()==0||t==null||t.length()==0) return 0;
		int res=0;
		for(int i=0;i<s.length();i++){
			for(int j=0;j<t.length();j++){
				int k=0;
				while(i+k<s.length()&&j+k<t.length()&&(s.charAt(i+k)==t.charAt(j+k))){
					k++;
				}
				res=Math.max(k, res);
			}
		}
		return res;
	}
	/**
	 *移位字符串
	 */
	public static void rotateString(char[] cs,int offset){
		offset%=cs.length;
		reverse(cs,0,cs.length-offset-1);
		reverse(cs,cs.length-offset,cs.length-1);
		reverse(cs,0,cs.length-1);
	}
	private static void reverse(char[] cs, int l, int r) {
		while(l<r){
			char c=cs[l];
			cs[l]=cs[r];
			cs[r]=c;
			l++;
			r--;
		}
	}
	/**
	 * 翻转字符串
	 */
	public static String reverseWords(String s){
		if(s==null||s.length()==0)
			return s;
		StringBuilder res=new StringBuilder();
		s=s.trim();
		String[] strs=s.split(" ");
		for(int j=strs.length-1;j>=0;j--){
			if(strs[j].trim().length()!=0){
				res.append(strs[j]).append(" ");
			}
		}
		return res.toString().substring(0, res.length()-1);
	}
	
	/**
	 * 是否是有效的回文串
	 */
	public boolean isPalindrom(String s){
		   // Write your code here
		int l=0;
		int r=s.length()-1;
		while(l<r){
			if(!Character.isLetterOrDigit(s.charAt(l))){
				l++;
				continue;
			}
			if(!Character.isLetterOrDigit(s.charAt(r))){
				r--;
				continue;
			}
			if(Character.toLowerCase(s.charAt(l))!=Character.toLowerCase(s.charAt(r))){
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
	/**
	 * 最长回文串
	 */
	public String longestPalindrom(String s){
		String res=new String();
		if(s==null||s.length()==0) return res;
		for(int i=0;i<s.length();i++){
			String s1=isP(s,i,i);
			if(s1.length()>res.length()){
				res=s1;
			}
			String s2=isP(s,i,i+1);
			if(s2.length()>res.length()){
				res=s2;
			}
			
		}
		return res;
	}
	private String isP(String s, int l, int r) {
			while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
				l--;
				r++;
			}
		return s.substring(l+1,r);
	}
	/**
	 * 空格替换
	 */
	public static int replaceBlank(char[] string, int length) {
        // Write your code here
		int cnt=0;
		for(int i=0;i<length;i++){
			if(string[i]==' ')
				cnt++;
		}
		int r=cnt*2+length-1;
		for(int i=length-1;i>=0;i--){
			if(string[i]!=' '){
				string[r--]=string[i];
			}else{
				string[r--]='0';
				string[r--]='2';
				string[r--]='%';
			}
		}
		return length+2*cnt;
    }
	/**
	 * 字符串对应匹配
	 */
	public static boolean isMatch(String s,String p){
		if((s==null||s.length()==0)&&(p==null||p.length()==0))
			return true;
		int i=0,j=0,ss=0,js=0;
		while(i<s.length()){
			if(j<p.length()&&(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')){
				i++;
				j++;
				continue;
			}
			if(j<p.length()&&p.charAt(j)=='*'){
				ss=i;
				js=++j;
				continue;
			}
			if(js!=0){
				i=++ss;
				j=js;
				continue;
			}
			return false;
		}
		while(j<p.length()&&p.charAt(j)=='*')
			j++;
		return j==p.length();
	}
	public static boolean isMatch1(String s,String p){
		int i=0,j=0,ss=0,js=0;
		while(i<s.length()){
			if(j<p.length()&&(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')){
				i++;
				j++;
				continue;
			}
			if(j<p.length()&&p.charAt(j)=='*'){
				js=++j;
				ss=i;
				continue;
			}
			if(js!=0){
				i=++ss;
				j=js;
				continue;
			}
			return false;
		}
		while(j<p.length()&&p.charAt(j)=='*')
			j++;
		return j==p.length();
	}
	/**
	 * 最后一个单词的长度
	 */
	public static int lastLength(String s){
		if(s==null||s.length()==0)
			return 0;
		 int cnt=0;
		 for(int i=s.length()-1;i>=0;i--){
			 if(s.charAt(i)==' '){
				 if(cnt==0)
					 continue;
				 else{
					 return cnt;
				 }
			 }else{
				 cnt++;
			 }
		 }
		return cnt;
		
		
	}
	/**
	 * contAndSay--递归
	 */
	public static String countAndSay(int n){
		if(n<=0) return "";
		if(n==1) return "1";
		String pre=countAndSay(n-1);
		StringBuilder res=new StringBuilder();
		char p='1';
		int cnt=0;
		for(char c:pre.toCharArray()){
			if(cnt==0){
				p=c;
				cnt++;
			}else{
				if(p==c){
					cnt++;
				}else{
					res.append(cnt+""+p);
					cnt=1;
					p=c;
				}
			}
		}
		res.append(cnt+""+p);
		return res.toString();
	}
	/**
	 * countAndSay-迭代
	 */
	public static String countAndSay1(int n){
		if(n<=0)
			return "";
		if(n==1)
			return "1";
		String s="1";
		for(int i=1;i<n;i++){
			String next=getNest(s);
			s=next;
		}
		return s;
	}
	private static String getNest(String s) {
		char p='1';
		int cnt=0;
		StringBuilder res=new StringBuilder();
		for(char c:s.toCharArray()){
			if(cnt==0){
				cnt++;
				p=c;
			}else{
				if(c==p){
					cnt++;
				}else{
					res.append(cnt+""+p);
					cnt=1;
					p=c;
				}
			}
		}
		res.append(cnt+""+p);
		return res.toString();
	}
}
