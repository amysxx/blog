package com.blog.util.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.LinkedBlockingQueue;

import com.blog.util.filter.HtmlObj;

/**
 * @desc	静态化线程
 * @author	wlh
 */
public class HtmlThread implements Runnable{
	
	private static LinkedBlockingQueue<HtmlObj> htmlQueue = new LinkedBlockingQueue<HtmlObj>();
	
	public void run() {
		while(true){
			try {
				HtmlObj obj = htmlQueue.take();
				File file = new File(obj.getFileUrl());
				if(!file.exists()){
					OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
					os.write(obj.getContent());
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void addHtml(HtmlObj htmlObj) {
		htmlQueue.offer(htmlObj);
	}
	
}
