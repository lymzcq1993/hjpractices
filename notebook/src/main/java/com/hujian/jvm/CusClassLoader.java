package com.hujian.jvm;

import java.io.FileInputStream;

public class CusClassLoader{
	
	static class ExtParents extends ClassLoader{
		public String classPath;
		
		public ExtParents(String classPath) {
			this.classPath = classPath;
		}
		
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
	        try {
	            byte[] data = loadByte(name);
	            //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
	            return defineClass(name, data, 0, data.length);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ClassNotFoundException();
	        }
		}
		
		//defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
	    private byte[] loadByte(String name) throws Exception {
	        name = name.replaceAll("\\.", "/");
	        FileInputStream fis = new FileInputStream(classPath + "/" + name
	                + ".class");
	        int len = fis.available();
	        byte[] data = new byte[len];
	        fis.read(data);
	        fis.close();
	        return data;
	    }
	}
	/**
	 * 打破双亲委派机制
	 * @author 35918
	 *
	 */
	static class NonExtParents extends ExtParents{
		public NonExtParents(String classPath) {
			super(classPath);
		}
		
		@Override
		protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                //这里重写的机制很简单，不再去寻找双亲的加载器
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

	}	

	
	public static void main(String[] args) throws ClassNotFoundException {
		//继承双亲委派
		ExtParents l = new ExtParents("F:/test");
		Class cla = l.loadClass("com.hujian.jvm.TClassLoader");
		System.out.println(cla.getClassLoader());
		
		//打破双亲委派，由自己去加载底层String类
		NonExtParents m = new NonExtParents("F:/test");
		Class classz = m.loadClass("java.lang.String");
		System.out.println(cla.getClassLoader());
	}
}
