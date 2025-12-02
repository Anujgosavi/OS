import java.util.*;

public class SJF_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        int [] at = new int [n];
        int [] bt = new int [n] ;
        int [] ct = new int [n] ;
        int [] tat = new int [n] ;
        int [] wt = new int [n] ;
        boolean [] vis = new boolean [n] ;

        for(int i=0 ;i<n ;i++){
            System.out.print("Enter the Arrival time for the process " + (i+1)+": ");
            at[i] = sc.nextInt();
            System.out.print("Enter the Burst time for the process " + (i+1)+": ");
            bt[i] = sc.nextInt();
        }


        int time = 0;
        int total=  0;

        while(total < n){
            int idx = -1 ;
            int minbt = Integer.MAX_VALUE ;
            for(int i=0 ;i<n ;i++){
                if(vis[i]==false && at[i]<=time && bt[i] < minbt){
                    minbt = bt[i] ;
                    //  don't need to check for tie as we are traversing from 0 to n 
                    idx = i;
                }
            }

            if(idx ==-1){
                time++ ;
                continue ;
            }

            time += bt[idx] ;
            ct[idx] = time ;
            vis[idx] = true ;
            tat[idx] = ct[idx] - at[idx] ;
            wt[idx] = tat[idx] - bt[idx]; 
            total++ ;
        }
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for(int i=0 ;i<n ;i++){
            System.out.println((i+1)+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }

    }
}
