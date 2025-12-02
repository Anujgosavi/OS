

class RW{
    int data = 0 ;
    int rc = 0;
    boolean writing = false ;


    synchronized void Rstart() throws InterruptedException{
           while(writing){
            wait();
           }

           rc++ ;
    }

    synchronized void REnd() throws InterruptedException{
           rc--;
           if(rc==0) notifyAll();
    }

    synchronized void WStart() throws InterruptedException{
        while(writing || rc >0){
            wait();
        }
        writing = true ;
    }
     synchronized void WEnd() throws InterruptedException{
       
        writing = false ;
        notifyAll() ;
    }

}

public class reader_writer {
    public static void main(String[] args) {
        RW r = new RW();

        Runnable Reader =  () -> {
            try{
                r.Rstart();
                System.out.println(Thread.currentThread().getName() + "Read " + r.data);
                Thread.sleep(200);
                r.REnd();
            } catch (Exception e ){ }
        } ;

        Runnable Writer  =  () -> {
            try{
                r.WStart();
                r.data++ ;
                System.out.println(Thread.currentThread().getName() + "wrote " + r.data);
                Thread.sleep(200);
                r.WEnd();
            } catch (Exception e ){ }
        } ;
          
          new Thread(Writer , "W1").start() ;
          new Thread(Reader , "R1").start() ;
          new Thread(Reader , "R2").start() ;
          new Thread(Writer , "W2").start() ;
          new Thread(Writer , "W3").start() ;
          new Thread(Reader , "R3").start() ;
    }
}