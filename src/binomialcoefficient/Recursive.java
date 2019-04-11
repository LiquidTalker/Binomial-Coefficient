package binomialcoefficient;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 * @since 7/4/2019
 * @version 1
 */
public class Recursive {

    private ArrayList<BigInteger> times = new ArrayList<>();
    private BigInteger[][] results;
    private BigInteger promTime = BigInteger.ZERO;

    /**
     * Función main que llama a la función recursiva optimizada del coeficiente
     * binomial
     *
     * @param n
     * @throws IOException
     */
    public void newRecursive(BigInteger n) throws IOException {
        results = new BigInteger[n.intValue() + 1][6];
        System.out.println("********************Recursivo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            results[i][0] = n;
            results[i][1] = BigInteger.valueOf(i);
            Long ts = System.nanoTime();
            results[i][2] = betterRecursiveBinom(n, BigInteger.valueOf(i));
            results[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            times.add(results[i][3]);
            promTime = promTime.add(results[i][3]);
        }
        promTime = promTime.divide(n);
        Helper.standardDeviation(times, promTime);
        for (int i = 0; i <= n.intValue(); i++) {
            results[i][4] = promTime;
            results[i][5] = Helper.deviation;
        }
        Helper.showInfo(results);
        System.out.println("Promedio de los tiempos de ejecución(Recursivo): " + promTime + " nanosegundos");
        System.out.println("La desviación estandar para n = " + n + ": " + Helper.deviation);
        new ArchiveManager().createFile(results);
        System.out.println("*************************************************");
    }

    public void SpecificRecursive() throws IOException {
        System.out.println("********************Recursivo********************");
        System.out.println("Digite el n: ");
        BigInteger n = new Scanner(System.in).nextBigInteger();
        System.out.println("Digite el k: ");
        BigInteger k = new Scanner(System.in).nextBigInteger();
        Long ts = System.nanoTime();
        BigInteger result = betterRecursiveBinom(n, k);
        Helper.showSpecificOne(n, k, result, (System.nanoTime() - ts));
        new ArchiveManager().saveSpecificOne(n, k, result, (System.nanoTime() - ts));
        System.out.println("*************************************************");
    }

    /**
     * Función main que llama a la función no optima recursiva del coeficiente
     * binomial
     *
     * @param n
     * @throws IOException
     */
    public void oldRecursive(BigInteger n) throws IOException {
        results = new BigInteger[n.intValue() + 1][6];
        System.out.println("********************Recursivo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            results[i][0] = n;
            results[i][1] = BigInteger.valueOf(i);
            Long ts = System.nanoTime();
            results[i][2] = recursiveBinom(n, BigInteger.valueOf(i));
            results[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            times.add(results[i][3]);
            promTime = promTime.add(results[i][3]);
        }
        promTime = promTime.divide(n);
        Helper.standardDeviation(times, promTime);
        for (int i = 0; i <= n.intValue(); i++) {
            results[i][4] = promTime;
            results[i][5] = Helper.deviation;
        }
        Helper.showInfo(results);
        System.out.println("Promedio de los tiempos de ejecución(Recursivo): " + promTime + " nanosegundos");
        System.out.println("La desviación estandar para n = " + n + ": " + Helper.deviation);
        new ArchiveManager().createFile(results);
        System.out.println("*************************************************");
    }

    /**
     * Coeficiente binomial por definición, muy lento cuando se da un valor
     * mayor a 35 para un valor de 50 demora más de 1h y media.
     *
     * @deprecated
     * @param n
     * @param k
     * @return combinatoria de n en k
     */
    private BigInteger recursiveBinom(BigInteger n, BigInteger k) {
        if (k.equals(BigInteger.ZERO) || k.equals(n)) {
            return BigInteger.ONE;
        } else {
            return recursiveBinom(n.subtract(BigInteger.ONE), k.subtract(BigInteger.ONE)).add(recursiveBinom(n.subtract(BigInteger.ONE), k));
        }
    }

    /**
     * Coeficiente binomial recursivo mucho más optimo
     *
     * @param n
     * @param k
     * @return combinatoria de n en k
     */
    private BigInteger betterRecursiveBinom(BigInteger n, BigInteger k) {
        if (k.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        } else {
            if (k.compareTo(n.subtract(k)) > 0) {
                return betterRecursiveBinom(n, n.subtract(k));
            } else {
                return (betterRecursiveBinom(n.subtract(BigInteger.ONE), k.subtract(BigInteger.ONE))).multiply(n).divide(k);
            }
        }
    }

}
