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
public class Iterative {

    private BigInteger[][] results;
    private ArrayList<BigInteger> times = new ArrayList<>();
    private BigInteger promTime = BigInteger.ZERO;

    /**
     * Función main que llama a la función optimizada iterativa del coeficiente
     * binomial
     *
     * @param n
     * @throws IOException
     */
    public void newIterative(BigInteger n) throws IOException {
        results = new BigInteger[n.intValue() + 1][6];
        System.out.println("********************Iterativo********************");
        for (int i = 50; i <= n.intValue(); i += 50) {
            Long ts = System.nanoTime();
            results[i][0] = n;
            results[i][1] = BigInteger.valueOf(i);
            results[i][2] = betterIterativeBinom(n, BigInteger.valueOf(i));
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
        System.out.println("Promedio de los tiempos de ejecución(Iterativo): " + promTime + " nanosegundos");
        System.out.println("La desviación estandar para n = " + n + ": " + Helper.deviation);
        new ArchiveManager().createFile(results);
        System.out.println("*************************************************");
    }

    public void specificIterative() throws IOException {
        System.out.println("********************Iterativo********************");
        System.out.println("Digite el n: ");
        BigInteger n = new Scanner(System.in).nextBigInteger();
        System.out.println("Digite el k: ");
        BigInteger k = new Scanner(System.in).nextBigInteger();
        Long ts = System.nanoTime();
        BigInteger result = betterIterativeBinom(n, k);
        Helper.showSpecificOne(n, k, result, (System.nanoTime() - ts));
        new ArchiveManager().saveSpecificOne(n, k, result, (System.nanoTime() - ts));
        System.out.println("*************************************************");
    }

    /**
     * Función main que llama a la función no optima del coeficiente binomial
     * iterativo
     *
     * @param n
     * @throws IOException
     */
    public void oldIterative(BigInteger n) throws IOException {
        results = new BigInteger[n.intValue() + 1][6];
        System.out.println("********************Iterativo********************");
        for (int i = 50; i <= n.intValue(); i += 50) {
            Long ts = System.nanoTime();
            results[i][0] = n;
            results[i][1] = BigInteger.valueOf(i);
            results[i][2] = factorial(n).divide(factorial(BigInteger.valueOf(i)).multiply(factorial(n.subtract(BigInteger.valueOf(i)))));
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
        System.out.println("Promedio de los tiempos de ejecución(Iterativo): " + promTime + " nanosegundos");
        System.out.println("La desviación estandar para n = " + n + ": " + Helper.deviation);
        new ArchiveManager().createFile(results);
        System.out.println("*************************************************");
    }

    /**
     * Se calculaba los coeficientes binomailes con la forma de los factoriales
     * n! / k! (n-k)!, sin embargo, no es tan optimo.
     *
     * @deprecated
     * @param n
     * @return factorial de número n
     */
    private BigInteger factorial(BigInteger n) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n.intValue(); i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    /**
     * Forma mejorada del coeficiente binomial, se redujo en gran medida la
     * complejidad del algoritmo.
     *
     * @param n
     * @param k
     * @return coeificiente binomial de un número n en k
     */
    private BigInteger betterIterativeBinom(BigInteger n, BigInteger k) {
        if (k.compareTo(n.subtract(k)) > 0) {
            k = n.subtract(k);
        }
        BigInteger binom = BigInteger.ONE;
        for (int i = 1; i <= k.intValue(); i++) {
            binom = binom.multiply(n.subtract(BigInteger.valueOf(i)).add(BigInteger.ONE)).divide(BigInteger.valueOf(i));
        }
        return binom;
    }

}
