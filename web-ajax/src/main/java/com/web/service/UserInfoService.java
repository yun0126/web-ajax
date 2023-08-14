package com.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.web.vo.UserInfoVO;

public interface UserInfoService {
	List<UserInfoVO> selectUserInfoList(UserInfoVO userInfo);

	UserInfoVO selectUserInfo(int uiNum);

	int insertUserInfo(UserInfoVO user);

	int updateUserInfo(UserInfoVO user);

	int deleteUserInfo(int uiNum);

	}
