import java.util.*;

public class SJF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(("Enter the number of processes: "));
        int n = sc.nextInt();
        int [] at = new int [n];
        int [] bt = new int [n];
        int [] rt = new int [n];
         int [] ct = new int [n];
        int [] tat = new int [n];
        int [] wt = new int [n];
        boolean [] vis = new boolean [n];


        for(int i=0 ;i<n ;i++){
            System.out.print("Enter the Arrival time for the process " + (i+1)+": ");
            at[i] = sc.nextInt();
            System.out.print("Enter the Burst time for the process " + (i+1)+": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i] ;
        }

        int time = 0 ;
        int total = 0 ;

        while(total < n){
            int idx = -1 ;
            int minrt = Integer.MAX_VALUE ;

            for(int i=0 ;i<n ;i++){
                if(vis[i]==false && at[i]<=time &&  rt[i] < minrt){
                    minrt = rt[i] ;
                    idx = i ;
                }
            }

            if(idx==-1){
                time++;
                continue ;
            }

            rt[idx]-- ;   // dcrease remaining time by only 1 unit and increase time by 1 unit so 
            time++ ;         //  so that we can check other processes in next time 

            if(rt[idx] == 0){
                vis[idx] = true ;
                ct[idx] = time; 
                tat[idx] = ct[idx] - at[idx] ;
                wt[idx] = tat[idx] - bt[idx] ; // here we require original bt not remaining som make a separate array rt
                total++ ;
                
            }
                          
        }
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for(int i=0 ;i<n ;i++){
            System.out.println((i+1)+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }
    }
}
