package binomialcoefficient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class ArchiveManager {

    private File file;

    public void createFile(BigInteger[][] times) throws IOException {
        System.out.print("Digite el nombre del archivo: ");
        String name = new Scanner(System.in).next();
        file = new File("./" + name + ".csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 50; i < times.length; i += 50) {
                for (int j = 0; j < 6; j++) {
                    bw.write(times[i][j] + ";");
                }
                bw.newLine();
            }
        }
    }

    public void saveSpecificOne(BigInteger n, BigInteger k, BigInteger result, Long ts) throws IOException {
        System.out.println("Digite el nombre del archivo: ");
        String name = new Scanner(System.in).next();
        file = new File("./" + name + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(n.toString() + "," + k.toString() + "," + result.toString() + "," + ts.toString() + ",");
        }
    }
}
