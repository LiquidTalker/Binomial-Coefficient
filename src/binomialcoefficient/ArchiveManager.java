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
        file = new File("./" + name+".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < times.length; i++) {
                for (int j = 0; j < 4; j++) {
                    bw.write(times[i][j] + ",");
                }
                bw.newLine();
            }
        }
    }
}
