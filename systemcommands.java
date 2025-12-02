import java.io.*;

public class systemcommands {
    public static void main(String[] args) {
       try{
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");

        Process child = pb.start();
        System.out.println(("Parent -  child started "));
        child.waitFor();
        System.out.println("Parent - child ended ");
       



       FileInputStream fin = new FileInputStream("input.txt");
       FileOutputStream fout = new FileOutputStream("output.txt");

       int a ;
       while((a= fin.read())!=-1){
        fout.write(a) ;
       }

       fin.close();
       fout.close();


        } catch (Exception e){ }
    }
}
