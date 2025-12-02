import java.util.concurrent.Semaphore;

class PC{
    int buffer [] = new int [5];
    int in = 0;
    int out  = 0;
    
    Semaphore empty = new Semaphore (5); // producer 
    Semaphore full = new Semaphore(0); // consumer 
    Semaphore mutex = new Semaphore(1); // for mutual exclusion
}


public class producer_consumer{
    public static void main(String[] args) {
        //  most imp  - sleep after releasing semaphore and order empty , full, mutex  acquire  , release  in ,  out , buffer  , runnable  

        PC  p = new PC();

        Runnable producer  = () ->{
           try{
               int item = 1;
               while(true){
                p.empty.acquire();
                p.mutex.acquire();
                p.buffer[p.in] = item ;
                System.out.println(Thread.currentThread().getName() + " produced " + item);
                item++ ;
                p.in = (p.in+1) % 5 ; // circular buffer  

                p.mutex.release();
                p.full.release();
                Thread.sleep(200);
               }

           } catch (Exception e){ }
        }; 


        Runnable consumer   = () ->{
           try{
             
               while(true){
                p.full.acquire();   // wait for full slot 
                p.mutex.acquire();
                int item = p.buffer[p.out] ;

                System.out.println(Thread.currentThread().getName() + " consumed " + item);
            
                p.out = (p.out+1) % 5 ; // circular buffer  

                p.mutex.release();
                p.empty.release();
                Thread.sleep(200);
               }

           } catch (Exception e){ }
        }; 

        new Thread(producer  , "Producer").start();
        new Thread(consumer , "consumer").start() ;
    }
}