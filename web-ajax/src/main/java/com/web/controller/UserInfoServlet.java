package com.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.web.common.CommonView;
import com.web.service.UserInfoService;
import com.web.service.impl.UserInfoServiceImpl;
import com.web.vo.UserInfoVO;
import com.google.gson.Gson;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserInfoService userSevice = new UserInfoServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = CommonView.getcmd(request);
		String json = "";
		if (cmd.equals("list")) {
			json = gson.toJson(userSevice.selectUserInfoList(null));
		} else if (cmd.equals("view") || cmd.equals("update")) {
			String uiNum = request.getParameter("uiNum");
			json = gson.toJson(userSevice.selectUserInfo(uiNum));
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		UserInfoVO vo = new UserInfoVO();
		String cmd = CommonView.getcmd(request);
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine()) != null) {
			sb.append(str);
		}
		System.out.println(sb.toString());
		
		if(cmd.equals("delete")) {
			vo.setUiNum(Integer.parseInt(sb.toString()));
			result = userSevice.deleteUserInfo(uiNum) ;
		}else if(cmd.equals("insert")) {
			vo = gson.fromJson(sb.toString(), UserInfoVO.class);
			result = userSevice.insertUserInfo(vo);
		}else if(cmd.equals("update")) {
			vo = gson.fromJson(sb.toString(), UserInfoVO.class);
			result = userSevice.updateUserInfo(vo);
		}

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
