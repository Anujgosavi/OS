import java.util.* ;

public class deadlock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n ;
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();

        int r = 3 ;

        List<int [] > allocation = new ArrayList<>();
        List<int [] > max = new ArrayList<>();
        List<int [] > rem = new ArrayList<>();
        List<Integer> safeseq = new ArrayList<>() ;


    System.out.println("Enter the allocation matrix ");

      for(int i=0 ;i<n ;i++){
        int [] arr = new int [r] ;
        System.out.println("Enter the allocation for process " + (i+1) );
         for(int j=0 ;j<3 ;j++){
            arr[j] = sc.nextInt() ;
         }
         allocation.add(arr) ;
      }
           

      for(int i=0 ;i<n ;i++){
        int [] arr = new int [r] ;
        System.out.println("Enter the max need  for process " + (i+1) );
         for(int j=0 ;j<3 ;j++){
            arr[j] = sc.nextInt() ;
         }
         max.add(arr) ;
      }

      int [] total = new int [r] ;
      int [] avai = new int [r] ;

      System.out.println("Enter the total resources: ");
      for(int i=0 ;i<r ;i++){
        total[i] = sc.nextInt() ;
      }


      //  remainig resources


           for(int i=0 ;i<n ;i++){
            int a = max.get(i)[0] - allocation.get(i)[0] ;
            int b = max.get(i)[1] - allocation.get(i)[1];
            int c = max.get(i)[2] - allocation.get(i)[2];

             rem.add(new int [] { a , b, c}) ;
           }

            
            int sum1 =0;
            int sum2 =0;
            int sum3 = 0;

            for(int i=0 ;i<n ;i++){
                sum1 += allocation.get(i)[0] ;
                sum2 += allocation.get(i)[1] ;
                sum3 += allocation.get(i)[2] ;
            }

            avai[0] = total[0] - sum1 ;
            avai[1] = total[1] - sum2 ;
            avai[2] = total[2] - sum3 ;


           int count = 0 ;

           boolean [] visited = new boolean [n] ;
           while(count < n){
            boolean flag = false ;


            for(int i=0 ;i<n ;i++){
                if(visited[i] == false && rem.get(i)[0] <= avai[0]  && rem.get(i)[1] <= avai[1]  && rem.get(i)[2] <= avai[2] ){
                   avai[0] += allocation.get(i)[0];
                   avai[1] += allocation.get(i)[1];
                   avai[2] += allocation.get(i)[2];
                   visited[i] = true ;
                   count++ ;
                   safeseq.add(i+1) ;
                   flag = true ;
                   break ;
                    
                }
                
            }

             if(flag== false){
                    System.out.println("The system is in deadlock state ") ;
                    return ;
                }
           }

           System.out.println("The system is in safe state ") ;
            System.out.println("Safe sequence is : " +  safeseq) ;
    } 
}
