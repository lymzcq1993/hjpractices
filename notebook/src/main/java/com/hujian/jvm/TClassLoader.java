package com.hujian.jvm;

import java.net.URL;

import sun.misc.Launcher;

public class TClassLoader extends ClassLoader{

	public static void main(String[] args) {
		//打印null  String.class在\jre\lib\rt.jar下，JAVA的主类，因为是BootStrapClassLoader，由c++初始化，java无法读取
		System.out.println(String.class.getClassLoader());
		//sun.misc.Launcher$ExtClassLoader@63947c6b   DESKeyFactory 位于\jre\lib\ext目录下，由extClassLoader加载
		System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
		//sun.misc.Launcher$AppClassLoader@18b4aac2   自定义的类由该类加载
		System.out.println(TClassLoader.class.getClassLoader());

		/*
		  此处的双亲委派机制的父亲并非继承关系，而是来自ClassLoader的parent属性
		 */
		ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extClassLoader =appClassLoader.getParent();
		ClassLoader bootstrapLoader = extClassLoader.getParent();

		//系统默认的类加载器，包括自定义的类的parent属性都是sun.misc.Launcher$AppClassLoader@18b4aac2
		System.out.println("appClassLoader.."+appClassLoader);
		//sun.misc.Launcher$AppClassLoader@18b4aac2 的parent属性为  sun.misc.Launcher$ExtClassLoader@63947c6b
		System.out.println("extClassLoader.."+extClassLoader);
		//extClassLoader的parent属性为bootstrapClassLoader，打印出来是null
		System.out.println("bootstrapLoader.."+bootstrapLoader);

		System.out.println("-------------分割线------------------------");

		//加载jre/lib下的所有jar
		System.out.println("bootstrapLoader加载以下文件...");
		URL[] urls = Launcher.getBootstrapClassPath().getURLs();
		for(URL u:urls) {
			System.out.println(u);
		}
		//加载jre\lib\ext下的所有jar
		System.out.println("extClassLoader加载以下文件...");
		System.out.println(System.getProperty("java.ext.dirs"));
		
		System.out.println();
		//加载自定义的所有类
		System.out.println("appClassLoader加载以下文件...");
		System.out.println(System.getProperty("java.class.path"));
 	}
}
