package com.example.demo;


//single locking system
public class SinglonClass {
	
	private static SinglonClass obj;
	private	SinglonClass(){
		
	}
	 
	public static synchronized SinglonClass getInstance() {
		if(obj==null) {
			obj=new SinglonClass();
		}
		return obj;
	}
}


//double locking system
 class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    // Double-checked locking for better performance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
