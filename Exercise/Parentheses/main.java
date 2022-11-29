import java.util.*;

public class main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter in the amount of different parantheses you want");
        int n = sc.nextInt();
        String opcl = "()";
        // String[] para = new String[];
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> perm = new ArrayList<String>();
        temp.add("opcl");
        for (int c = 0; c == n; c++) {
            perm.add(temp.get(c) + opcl);
        }
    }
}
