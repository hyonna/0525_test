package com.iu.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response);
	
	public ActionFoward select(HttpServletRequest request, HttpServletResponse response); 
	
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response); 
	
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response); 
	
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response); 

}
