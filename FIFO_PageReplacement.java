import java.util.*;

public class FIFO_PageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter reference string (e.g., 7012030423): ");
        String ref = sc.nextLine();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < frames; i++) {
            s.append("#");
        }

        int faults = 0;
        Queue<Integer> q = new LinkedList<>(); 
        HashSet<Character> set = new HashSet<>(); 

        for (int i = 0; i < ref.length(); i++) {
            char c = ref.charAt(i);

            if (set.contains(c)) {
                System.out.println(s + " (No Page Fault)");
                continue;
            }

            faults++;

            if (q.size() == frames) {
                
                int index = q.remove();         
                char removedPage = s.charAt(index);
                set.remove(removedPage);

                s.setCharAt(index, c);
                set.add(c);
                q.add(index);

                System.out.println(s + " (Page Fault, replaced " + removedPage + ")");
            } else {
               
                for (int j = 0; j < frames; j++) {
                    if (s.charAt(j) == '#') {
                        s.setCharAt(j, c);
                        set.add(c);
                        q.add(j);  
                        break;
                    }
                }
                System.out.println(s + " (Page Fault)");
            }
        }

        System.out.println("\nTotal Page Faults: " + faults);
    }
}
