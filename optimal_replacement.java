
import java.util.*;

public class optimal_replacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter reference string (e.g., 7012030): ");
        String ref = sc.nextLine();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        List<Character> frame = new ArrayList<>();
        int faults = 0;

    

        for (int i = 0; i < ref.length(); i++) {
            char c = ref.charAt(i);

            if (frame.contains(c)) {
                System.out.println(frame + " (No Page Fault)");
                continue;
            }

            faults++;

            if (frame.size() < frames) {
                frame.add(c);
            } else {
                // Find the page to replace using optimal strategy
                int farthest = -1;
                int indexToReplace = -1;

                for (int j = 0; j < frame.size(); j++) {
                    char f = frame.get(j);
                    int nextUse = Integer.MAX_VALUE;

                    // Find next use of frame page
                    for (int k = i + 1; k < ref.length(); k++) {
                        if (ref.charAt(k) == f) {
                            nextUse = k;
                            break;
                        }
                    }

                    if (nextUse > farthest) {
                        farthest = nextUse;
                        indexToReplace = j;
                    }
                }

                System.out.println("Replacing " + frame.get(indexToReplace) + " with " + c);
                frame.set(indexToReplace, c);
            }

            System.out.println(frame + " (Page Fault)");
        }

        System.out.println("\nTotal Page Faults = " + faults);
    }
}
