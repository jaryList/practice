package com.list.jvm.classloader;

import sun.misc.Launcher;

import java.net.URL;

public class ClassA {

    static {
        System.out.println("static classA");
    }

    public ClassA() {
        System.out.println("construct classA");
    }

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public static void a(){
        System.out.println("static method classA");
    }

    /**
     * main方法也是一个方法、一个静态方法
     * 要执行方法，肯定也要先加载类
     */
    /*public static void main(String[] args) {
        System.out.println("classA main");
    }*/

    public static void main(String[] args) {
        ClassLoader classLoader_A_by_class = ClassA.class.getClassLoader();
        System.out.println("classLoader_A_by_class = " + classLoader_A_by_class);
        ClassA classA = new ClassA();
        ClassLoader classLoader_A_by_instance = classA.getClass().getClassLoader();
        System.out.println("classLoader_A_by_class == classLoader_A_by_instance is " + (classLoader_A_by_class == classLoader_A_by_instance));
        ClassB classB = new ClassB();
        ClassLoader classLoader_B_by_instance = classB.getClass().getClassLoader();
        System.out.println("classLoader_A_by_instance == classLoader_B_by_instance is " + (classLoader_A_by_instance == classLoader_B_by_instance));
        System.out.println("classLoader_A_by_class's parent = " + classLoader_A_by_class.getParent());
        /**
         * 结合Launcher,AppClassLoader的parent是ExtClassLoader
         * 而ExtClassLoader的parent是null,也根本没有BootStrapClassLoader类,有BootStrapLoader的操作，包括BootClassPathHolder
         * 结合ClassLoader#loadClass源码
         * if (parent != null) {
         *     c = parent.loadClass(name, false);
         *  } else {
         *    c = findBootstrapClassOrNull(name);
         *  }
         *  ExtClassLoader加载不了,且没有parent会走native方法加载
         */
        System.out.println("classLoader_A_by_class's grandparent = " + classLoader_A_by_class.getParent().getParent());
        System.out.println("Launcher classLoader = " + Launcher.getLauncher().getClassLoader());
        for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
            System.out.println(url.getPath());
        }
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        //Launcher本身也是在rt.jar中
        System.out.println("Launcher classLoader = " + Launcher.class.getClassLoader());
        System.out.println("String classLoader = " + String.class.getClassLoader());
    }
}
