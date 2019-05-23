package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Button;
import com.entity.Device;
import com.entity.Button.Buttons;
import com.entity.User;
import com.enums.Status;
import com.mapper.IndexMapping;

@Service
public class IndexService {
	@Autowired
	IndexMapping indexMappring;
	public boolean checkUser(HttpServletRequest request, String username, String password) {
		User user = getUserByUsernameAndPassword(username,password);
		if(null!=user) {
			List<Button> buttons = null;
			int shiro = user.getShiro();
			switch(shiro) {
				case 1 :{
					buttons = getIndexButtonList(1);
					break;
				}
				case 2 :{
					buttons = getIndexButtonList(2);
					break;
				}
				case 3 :{
					buttons = getIndexButtonList(3);
					break;
				}
			}
			request.getSession().setAttribute("user", user);
			request.setAttribute("buttons", buttons);
			return Status.isSuccess(Status.SUCCESS);
		}else {
			return Status.isSuccess(Status.FAIL);
		}
		
	}

	public List<Button> getIndexButtonList(int i) {
		List<Button> list = new ArrayList<Button>();
		List<Map<String,String>> buttonList = indexMappring.getIndexButtonByShiro(i);
		for (Map<String, String> buttonMap : buttonList) {
			Button button = new Button();
			button.setId(buttonMap.get("id"));
			button.setName(buttonMap.get("name"));
			List<Map<String,String>> buttonsListMap = indexMappring.getIndexButtonsByPid(buttonMap.get("id"));
			List<Buttons> buttonsList = new ArrayList<Buttons>();
			for (Map<String, String> buttonsMap : buttonsListMap) {
				Buttons buttons = button.new Buttons(); 
				buttons.setName(buttonsMap.get("name"));
				buttons.setLink(buttonsMap.get("link"));
				buttonsList.add(buttons);
			}
			button.setButtons(buttonsList);
			list.add(button);
		}
		return list;
	}
	public User getUserByUsernameAndPassword(String username, String password) {
		return indexMappring.getUserByUsernameAndPassword(username,password);
	}

	public List<Device> getDeviceList() {
		List<Device> devices = indexMappring.getDeviceList();
		return devices;
	}

	public Integer saveNeed(Device device) {
		String id = device.getId();
		int status = 0;
		if(null==id) {
			id = UUID.randomUUID().toString();
			device.setId(id);
			device.setNumber(id.substring(0, 5));
			status = indexMappring.saveNeed(device);
		}else {
			status = indexMappring.updateNeed(device);
		}
		return status;
	}

	/*public String getCode(String phone) {
		String code = String.valueOf((int)(Math.random()*1000000));
		sms.getRequest2(phone, code);
		return code;
	}*/

	public int addReg(HttpServletRequest request, String username, String code, String phone) {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setNumber(user.getId().substring(0, 5));
		user.setUsername(username);
		user.setPassword(code);
		user.setShiro(3);
		user.setPhone(phone);
		user.setName("管理员");
		int status = indexMappring.addReg(user);
		return status;
	}

	public String fileUpload(MultipartFile file) {
		InputStream in = null;
		OutputStream out = null;
		String path = "";
		try {
			String name = file.getOriginalFilename();
			System.out.println(file.getOriginalFilename());
			in = file.getInputStream();
			path = new Date().getTime()+name.substring(name.indexOf("."), name.length());
			File newFile = new File("d:/webfile/"+path);
			out = new FileOutputStream(newFile);
			byte [] data = new byte[1024];
			int status = 0;
			while((status = in.read(data))!=-1) {
				out.write(data, 0, status);
			}
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	
}
