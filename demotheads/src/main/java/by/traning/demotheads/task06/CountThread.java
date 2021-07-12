package by.traning.demotheads.task06;

class CountThread implements Runnable{

    CommonResource res;
    CountThread(CommonResource res){
        this.res=res;
    }
    public void run(){
        res.increment();
    }
}