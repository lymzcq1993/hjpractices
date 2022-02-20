package com.hujian.jvm;

import cn.hutool.core.lang.reflect.MethodHandleUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import sun.misc.PerfCounter;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 * 注意：此处如果在项目中放入了hujian.jvm.classloaderTest.MyClassLoaderTest这个类
 * 由于自定义的类加载的parent会默认指定为sun.misc.Launcher$AppClassLoader，ClassLoader初始化会默认指定该类为parent
 * 所以如果项目中有该类，还是会优先加载项目中的类而不是自定义加载器的类
 */
public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("F:/programes/hjtest");
//        Class<?> aClass = myClassLoader.loadClass();
//        System.out.println(aClass);
//        Object o = aClass.newInstance();
        Class<?> aClass = ClassLoaderUtil.loadClass("com.hujian.jvm.MyClassLoaderTest", myClassLoader, true);
        MethodHandleUtil.invokeSpecial(aClass.newInstance(),"say",new Object[]{"找寻自定义加载器加载的类~!"});
        System.out.println(aClass.getClassLoader().getClass().getName());
    }

    private String basePath;

    public MyClassLoader(String basePath) {
        this.basePath = basePath;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];
        try {
            bytes = loadClassByte(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 打破双亲委派，重写ClassLoader的loadClass方法
     * @param name
     * @param resolve
     * @return
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) {
//        return super.loadClass(name, resolve);
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                //此处是双亲委派的原代码
//                try {
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }
                //注意此处，因为java对象的父类都是Object类，此处如果不加载的会报错java\lang\Object.class (系统找不到指定的路径。)
                //如果把Object/class对象拷贝到目录中，则会报错   Prohibited package name: java.lang（禁止自定义加载该路径下的类），即沙箱机制，防止恶意注入代码
                if (StrUtil.startWith(name,"com.hujian")){
                    c = findClass(name);
                }
                else{
                    try {
                        c = getParent().loadClass(name);
                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
                    }
                }
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private byte[] loadClassByte(String name) throws IOException {
        //将.转换成/
        String path = basePath+"/"+ StrUtil.replace(name,".","/")+".class";
        try (FileInputStream fileInputStream = new FileInputStream(path)){
            int length = fileInputStream.available();
            byte[] data = new byte[length];
            fileInputStream.read(data);
            return data;
        }
    }
}
