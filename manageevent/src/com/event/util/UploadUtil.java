package com.event.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class UploadUtil {
	
	
	
	public static StringBuffer Upload(File file,StringBuffer sb) throws IOException{
		
		ServletContext sc=ServletActionContext.getServletContext();
		String directory=sc.getRealPath("/images");
		File filepath=new File(directory);
		if(!filepath.exists()){
			filepath.mkdirs();
		}
		
		String time=String.valueOf(System.currentTimeMillis());
		String filename=time.substring(time.length()-5,time.length())+new Random().nextInt(1000)+".jpg";
		File target=new File(directory,filename);
		FileUtils.copyFile(file, target);
		System.out.println("file : "+filepath+"/"+filename);
		sb.append(filename+",");
		return sb;
	}

}
