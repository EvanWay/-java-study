package com.evan.study.lock;

/**
 * 测试死锁
 *
 * @author Evan
 * @date 2022/5/16
 */
public class TestDeadLock {
    public static void main(String[] args) {
        //灰姑娘先拿口红
        Makeup g1 = new Makeup(0, "灰姑娘");
        //白雪公主先拿镜子
        Makeup g2 = new Makeup(1, "白雪公主");
        g1.start();
        g2.start();
    }
}

//口红
class Lipstick {

}

//镜子
class Mirror {

}

//化妆
class Makeup extends Thread {
    static final Lipstick lipstick = new Lipstick();
    static final Mirror mirror = new Mirror();

    int choice;
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            if (choice == 0) {
                synchronized (lipstick) {
                    System.out.println(this.girlName + "获得口红的锁");
                    Thread.sleep(1000);
                    synchronized (mirror) {
                        System.out.println(this.girlName + "获得镜子的锁");
                    }
                }
            } else {
                synchronized (mirror) {
                    System.out.println(this.girlName + "获得镜子的锁");
                    Thread.sleep(2000);
                    synchronized (lipstick) {
                        System.out.println(this.girlName + "获得口红的锁");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}