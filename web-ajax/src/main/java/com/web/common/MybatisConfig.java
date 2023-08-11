package com.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.web.vo.UserInfoVO;

public class MybatisConfig {

	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";
	static{
		try {
			InputStream  is = Resources.getResourceAsStream(CONFIG_PATH);
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return SSF;
	}
	
	public static void main(String[] args) {
		try(SqlSession session = getSqlSessionFactory().openSession()){
			List<UserInfoVO>list = session.selectList("com.web.mapper.UserInfoMapper.selectUserInfoList");
			for(UserInfoVO userInfo : list) {
				System.out.println(userInfo);
			}
			int uiNum =2 ;
			UserInfoVO user = session.selectOne("com.web.mapper.UserInfoMapper.selectUserInfo", uiNum);
			System.out.println("user : " + user);
			
		}catch(Exception e) {
			
		}
		
		try(SqlSession session = getSqlSessionFactory().openSession()){
			List<UserInfoVO>list = session.selectList("com.web.mapper.UserInfoMapper.insertUserInfoList");
			for(UserInfoVO userInfo : list) {
				System.out.println(userInfo);
			}
			int uiNum =2 ;
			UserInfoVO user = session.selectOne("com.web.mapper.UserInfoMapper.insertUserInfo", uiNum);
			System.out.println("user : " + user);
			
		}catch(Exception e) {
			
		}
	}
}