import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class round_robin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        int [] at = new int [n];
        int [] bt = new int [n];
        int [] rt = new int [n];
        int [] ct = new int [n];
        int [] tat = new int [n];
        int [] wt = new int [n];
        boolean [] intheq = new boolean [n];


        for(int i=0; i<n; i++) {
            System.out.println("Enter the arrival time of process " + (i+1) + ": ");
            at[i] = sc.nextInt();
            System.out.println("Enter the burst time of process " + (i+1) + ": ");
            bt[i] = sc.nextInt();
           rt[i] = bt[i] ;
        }

        System.out.println("Enter the time quantum: ");
        int tq = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();

        for(int i=0 ;i<n ;i++){
            if(at[i]==0){
                q.add(i);
                intheq[i] = true ;
            }
        }

        int time = 0;
        int total = 0;

        while(total < n){
            if(q.isEmpty()){
                time++ ;

                for(int i=0 ;i<n ;i++){
                    if( intheq[i]== false && at[i]<=time){
                        q.add(i);
                        intheq[i] = true ;
                    }
                }
               
            }else {
        
                int p = q.remove();
                int mint = Math.min(tq , rt[p]);
                rt[p] -= mint ;
                time += mint ;

                 for(int i=0 ;i<n ;i++){
                    if( intheq[i]==false && at[i]<=time){
                        q.add(i);
                        intheq[i] = true ;
                    }
                }


                if(rt[p] > 0){
                    q.add(p) ;
                }
                else {
                    ct[p] = time ;
                    total++;
                    tat[p] = ct[p] - at[p];
                    wt[p] = tat[p] - bt[p] ;
                }

            }
        }

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for(int i=0 ;i<n ;i++){
            System.out.println((i+1)+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }

    }
}
