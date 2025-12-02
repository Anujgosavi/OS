import java.util.*;

public class priority_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n];  // Arrival time
        int[] bt = new int[n];  // Burst time
        int[] pr = new int[n];  // Priority (lower = higher priority)
        int[] ct = new int[n];  // Completion time
        int[] tat = new int[n]; // Turnaround time
        int[] wt = new int[n];  // Waiting time
        boolean[] done = new boolean[n];
        boolean[] added = new boolean[n];  // SINGLE LINE ADDED

        // Input
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of P" + (i+1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter burst time of P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
            System.out.print("Enter priority of P" + (i+1) + " (lower = higher): ");
            pr[i] = sc.nextInt();
        }

        int completed = 0;
        int time = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0] // sort by priority ascending
            );

        while (completed < n) {
            // FIXED: add only if not already added
            for (int i = 0; i < n; i++) {
                if (!done[i] && !added[i] && at[i] <= time) {  // CHANGED: added !added[i]
                    pq.add(new int[]{pr[i], i}); // {priority, index}
                    added[i] = true;             // NEW LINE
                }
            }

            if (pq.isEmpty()) {
                time++;
                continue;
            }

            int idx = pq.poll()[1]; // get index of process with highest priority

            time += bt[idx];
            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            done[idx] = true;
            completed++;
        }

        // Output
        System.out.println("\nP\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t" + at[i] + "\t" + bt[i] + "\t" +
                                   pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
    }
}
