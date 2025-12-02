import java.util.*;

public class priority_prremption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];   // arrival time
        int[] bt = new int[n];   // burst time
        int[] rt = new int[n];   // remaining time
        int[] pr = new int[n];   // priority (lower = higher priority)
        int[] ct = new int[n];   // completion time
        int[] tat = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Arrival time of P" + (i+1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Burst time of P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
            System.out.print("Priority of P" + (i+1) + ": ");
            pr[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        // PriorityQueue sorted by priority first, then index
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]
        );

        int time = 0, completed = 0;
        boolean[] inQ = new boolean[n];

        while (completed < n) {

           for(int i=0 ;i<n ;i++){
            if(!inQ[i]  && at[i] <= time ){
                pq.add(new int [] {pr[i] , i} ) ;
                inQ[i] = true ;
            }
        }

            if(pq.isEmpty()){
                time++;
                continue ;
            }

            int [] cell = pq.remove() ;
            int idx = cell[1] ;
            time++; 
            rt[idx]--;

            if(rt[idx] >0 ){
                pq.add(new int [] {pr[idx] , idx}) ;
            }
            else {
                ct[idx] = time ;
                completed++ ;
            }

           
        }

        // calculate TAT + WT
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // output
        System.out.println("\nP\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] +
                    "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
    }
}
