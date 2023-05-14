package com.sangeng;
/*看参数快捷键Ctrl+p*/
public class Lambda01 {
    public static void main(String[] args) {
       /* new Thread(new Runnable() {
            @Override//匿名内部类写法
            public void run() {
                System.out.println("新线程中run方法执行");
            }
        }).start();*/
        new Thread(()-> {
                System.out.println("新线程中run方法执行");}).start();
    }
}
//lambda函数式编程不关注类名和方法名，只关注参数和方法体。其他的可以选择省略
/*一个lambda表达式就是一个函数式接口的实例
* 什么是函数式接口，只有一个抽象方法的接口
* 接口不能new，这里的new 接口(){}相当于实现类，这不过这个实现类没有名字（匿名）*/
