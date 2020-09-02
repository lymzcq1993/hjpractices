package hujian.jvm.classloaderTest;

import java.net.URL;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

public class TClassLoader extends ClassLoader{

	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());
		System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
		System.out.println(TClassLoader.class.getClassLoader());
		
		ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extClassLoader =appClassLoader.getParent();
		ClassLoader bootstrapLoader = extClassLoader.getParent();
		System.out.println("appClassLoader.."+appClassLoader);
		System.out.println("extClassLoader.."+extClassLoader);
		System.out.println("bootstrapLoader.."+bootstrapLoader);
		
		System.out.println();
		
		System.out.println("bootstrapLoader加载以下文件...");
		URL[] urls = Launcher.getBootstrapClassPath().getURLs();
		for(URL u:urls) {
			System.out.println(u);
		}
		
		System.out.println();
		
		System.out.println("extClassLoader加载以下文件...");
		System.out.println(System.getProperty("java.ext.dirs"));
		
		System.out.println();
		
		System.out.println("appClassLoader加载以下文件...");
		System.out.println(System.getProperty("java.class.path"));
 	}
}
