package binomialcoefficient;

import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author Alejandro
 * @since 7/4/2019
 * @version 1
 */
public class Iterative {

    private BigInteger[][] times;
    private BigInteger promTime = BigInteger.ZERO;

    /**
     * Función main que llama a la función optimizada iterativa del coeficiente
     * binomial
     *
     * @param n
     * @throws IOException
     */
    public void newIterative(BigInteger n) throws IOException {
        times = new BigInteger[n.intValue() + 1][5];
        System.out.println("********************Iterativo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            Long ts = System.nanoTime();
            times[i][0] = n;
            times[i][1] = BigInteger.valueOf(i);
            times[i][2] = betterIterativeBinom(n, BigInteger.valueOf(i));
            times[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            promTime = promTime.add(times[i][3]);
        }
        showInfo(times);
        System.out.println("Promedio de los tiempos de ejecución(Iterativo): " + (promTime.divide(n) + " nanosegundos"));
        new ArchiveManager().createFile(times);
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
        times = new BigInteger[n.intValue() + 1][5];
        System.out.println("********************Iterativo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            Long ts = System.nanoTime();
            times[i][0] = n;
            times[i][1] = BigInteger.valueOf(i);
            times[i][2] = factorial(n).divide(factorial(BigInteger.valueOf(i)).multiply(factorial(n.subtract(BigInteger.valueOf(i)))));
            times[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            promTime = promTime.add(times[i][3]);
        }
        showInfo(times);
        System.out.println("Promedio de los tiempos de ejecución(Iterativo): " + (promTime.divide(n) + " nanosegundos"));
        new ArchiveManager().createFile(times);
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

    /**
     * Muestra la información obtenida de forma recursiva e iterativa
     *
     * @param times, matriz con los resultados de las combinatorias y sus
     * tiempos
     */
    public void showInfo(BigInteger[][] times) {
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("[" + times[i][j] + "]");
            }
            System.out.println(" ");
        }
    }

}
