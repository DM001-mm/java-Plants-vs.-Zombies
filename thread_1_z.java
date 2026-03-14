class Test{
    public static void f_1() throws InterruptedException{
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("我是傻逼");
                }
            }
        });
        t.start();
        t.join();
    }
    public static void main(String[] args) throws InterruptedException{
        Thread tt=new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("我是你爹");
                }
            }
        });
        //问题解答:
        // /* 
        Test.f_1();
        tt.start();
        tt.join();
        //但是加了 join()后 就不能成为多线程的了，你想过了没有 为什么
        // */
        // 这样 确实可以说明 是多线程的
        /* 
        tt.start();
        Test.f_1();
        */
       //但是下面的这种情况就需不行了，你知道为什么吗
        // 不对，多运行几次也是会出现多线程的结果，说明了 这是可以的，将线程打包分开，更容易理解
        // try{
        //    Test.f_1();

        //     // E:\植物大战僵尸\demo_1\MC_Zombie.mp3
        //     tt.join();
        // }catch(InterruptedException e){
        //     e.printStackTrace();
        // }
    }
}
//
/* 
Thread t1=new Thread();
Thread t2=new Thread();
t1.start();
t2.start();
t1.join();
t2.join();
*/