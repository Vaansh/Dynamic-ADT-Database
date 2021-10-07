import java.io.*;
import java.util.Scanner;

/**
 * Main class.
 */
public class Main {

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try (FileWriter f = new FileWriter("results.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);
             Scanner scan = new Scanner(System.in);) {
            int lines = 82;
            for (int i = 0; i < lines; i++) {
                System.out.print("▁");
                p.print("▁");
            }
            System.out.print("\n");
            p.print("\n");

            System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░ SORTED LIST/AVL TREE DATABASE ░░░░░░░░░░░░░░░░░░░░░░░░░░");
            p.println("░░░░░░░░░░░░░░░░░░░░░░░░░ SORTED LIST/AVL TREE DATABASE ░░░░░░░░░░░░░░░░░░░░░░░░░░");
            for (int i = 0; i < lines; i++) {
                System.out.print("‾");
                p.print("‾");
            }
            System.out.print("\n");
            p.print("\n");

            while (true) {
                double startTime, endTime, elapsed;
                System.out.print("Enter the number of IDs you want to test\n" + "(Less than 1000 for SortedList/Greater for AVL Tree): ");
                p.print("Enter the number of IDs you want to test\n" + "(Less than 1000 for SortedList/Greater for AVL Tree): ");
                int num = scan.nextInt();
                p.println(" " + num);
                startTime = System.currentTimeMillis();
                IntelligentSIDC sidc = new IntelligentSIDC(num);
                endTime = System.currentTimeMillis();
                elapsed = endTime - startTime;
                System.out.println(num + " IDs were randomly generated and added to the list; Time Taken: " + elapsed + "ms.");
                p.println(num + " IDs were randomly generated and added to the list; Time Taken: " + elapsed + "ms.");
                for (int i = 0; i < lines; i++) {
                    System.out.print("~");
                    p.print("~");
                }
                System.out.print("\n");
                p.print("\n");
                System.out.print("Want to test again? (Y/Yes for yes, anything else for no): ");
                p.print("Want to test again? (Y/Yes for yes, anything else for no): ");
                String continueLoop = scan.next();
                if (!continueLoop.equalsIgnoreCase("y") && !continueLoop.equalsIgnoreCase("yes")) {
                    break;
                }
                for (int i = 0; i < lines; i++) {
                    System.out.print("~");
                    p.print("~");
                }
                System.out.print("\n");
                p.print("\n");
            }
            for (int i = 0; i < lines; i++) {
                System.out.print("=");
                p.print("=");
                ;
            }
            System.out.print("\n");
            p.print("\n");

            int lines1 = 0, lines2 = 0, lines3 = 0;
            double startTime1, endTime1, elapsed1 = 0, startTime2, endTime2, elapsed2 = 0, startTime3, endTime3, elapsed3 = 0;

            try (BufferedReader buffer = new BufferedReader(new FileReader("src/Benchmark/CSTA_test_file1.txt"))) {
                String line;
                startTime1 = System.currentTimeMillis();
                IntelligentSIDC test1 = new IntelligentSIDC();
                while ((line = buffer.readLine()) != null) {
                    lines1++;
                    test1.add(Integer.parseInt(line), lines1 % 2 == 0 ? "Alan Turing" : "Nikola Tesla");
                }
                endTime1 = System.currentTimeMillis();
                elapsed1 = endTime1 - startTime1;
                System.out.println("Size of structure for test file 1: " + test1.getSize());
                p.println("Size of structure for test file 1: " + test1.getSize());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (BufferedReader buffer = new BufferedReader(new FileReader("src/Benchmark/CSTA_test_file2.txt"))) {
                String line;
                startTime2 = System.currentTimeMillis();
                IntelligentSIDC test2 = new IntelligentSIDC();
                while ((line = buffer.readLine()) != null) {
                    lines2++;
                    test2.add(Integer.parseInt(line), lines2 % 2 == 0 ? "Alan Turing" : "Nikola Tesla");
                }
                endTime2 = System.currentTimeMillis();
                elapsed2 = endTime2 - startTime2;
                System.out.println("Size of structure for test file 2: " + test2.getSize());
                p.println("Size of structure for test file 2: " + test2.getSize());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (BufferedReader buffer = new BufferedReader(new FileReader("src/Benchmark/CSTA_test_file3.txt"))) {
                String line;
                startTime3 = System.currentTimeMillis();
                IntelligentSIDC test3 = new IntelligentSIDC();
                while ((line = buffer.readLine()) != null) {
                    lines3++;
                    test3.add(Integer.parseInt(line), lines3 % 2 == 0 ? "Alan Turing" : "Nikola Tesla");
                }
                endTime3 = System.currentTimeMillis();
                elapsed3 = endTime3 - startTime3;
                System.out.println("Size of structure for test file 3: " + test3.getSize());
                p.println("Size of structure for test file 3: " + test3.getSize());
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < lines; i++) {
                System.out.print("=");
                p.print("=");
            }
            System.out.print("\n");
            p.print("\n");

            System.out.println("Time taken for test file 1: " + elapsed1 + "ms.\n" +
                    "Time taken for test file 2: " + elapsed2 + "ms.\n" +
                    "Time taken for test file 3: " + elapsed3 + "ms.\n");
            p.println("Time taken for test file 1: " + elapsed1 + "ms.\n" +
                    "Time taken for test file 2: " + elapsed2 + "ms.\n" +
                    "Time taken for test file 3: " + elapsed3 + "ms.\n");
            for (int i = 0; i < lines; i++) {
                System.out.print("=");
                p.print("=");
            }
            System.out.print("\n");
            p.print("\n");
            for (int i = 0; i < lines; i++) {
                System.out.print("▁");
                p.print("▁");
            }
            System.out.println("\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ END ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
            p.println("\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ END ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
            for (int i = 0; i < lines; i++) {
                System.out.print("‾");
                p.print("‾");
            }
            System.out.print("\n");
            p.print("\n");
            p.flush();
        } catch (IOException i) {    //In case there is some error with the file handling.
            System.out.println("Error in file handling.");
            System.out.println("Try resolving the issue and come again, bye! :(");
            System.exit(0);
        }
    }
}
