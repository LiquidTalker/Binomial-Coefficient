package binomialcoefficient;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Helper {

    static BigInteger deviation;

    /**
     * Muestra la información obtenida de forma recursiva e iterativa
     *
     * @param results, matriz con los resultados de las combinatorias y sus
     * tiempos
     */
    public static void showInfo(BigInteger[][] results) {
        for (int i = 50; i < results.length; i += 50) {
            for (int j = 0; j < 6; j++) {
                System.out.print("[" + results[i][j] + "]");
            }
            System.out.println(" ");
        }
    }

    /**
     * Calcula la desviación estandar
     *
     * @param times
     * @param promTime
     */
    public static void standardDeviation(ArrayList<BigInteger> times, BigInteger promTime) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger temp;
        for (BigInteger time : times) {
            temp = time.subtract(promTime);
            sum = sum.add(temp.pow(2));
        }
        if (times.size() - 1 == 0) {
            Helper.deviation = sum.divide(BigInteger.valueOf(times.size()));
        } else {
            Helper.deviation = sum.divide(BigInteger.valueOf(times.size() - 1));
        }
    }

    public static void showSpecificOne(BigInteger n, BigInteger k, BigInteger result, Long time) {
        System.out.println("[" + n + "][" + k + "][" + result + "][" + time + "]");
    }
}
