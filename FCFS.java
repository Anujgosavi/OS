import java .util.*;

public class FCFS{
    public static void main(String[] args) {
        int n ;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();

        int [] at = new int[n];
        int [] bt = new int[n];
        int [] ct = new int[n];
        int [] tat = new int[n];
        int [] wt = new int[n];

        for(int i=0 ;i<n ;i++){
            System.out.print("Enter the Arrival time for the process " + (i+1)+": ");
            at[i] = sc.nextInt();
            System.out.print("Enter the Burst time for the process " + (i+1)+": ");
            bt[i] = sc.nextInt(); 
        }

        ct[0] = at[0] + bt[0] ;

        for(int i=1 ;i<n ;i++){
            if(at[i] > ct[i-1]){
                ct[i] = at[i] + bt[i] ;
            }
            else {
                ct[i] = ct[i-1] + bt[i] ;
            }
        }

        for(int i=0 ;i<n ;i++){
            tat[i] = ct[i] - at[i] ;
            wt[i] = tat[i] - bt[i] ;
        }
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for(int i=0 ;i<n ;i++){
            System.out.println((i+1)+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }

        
    }
}