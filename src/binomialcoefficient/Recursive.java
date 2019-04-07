package binomialcoefficient;

import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author Alejandro
 * @since 7/4/2019
 * @version 1
 */
public class Recursive extends Iterative {

    private BigInteger[][] times;
    private BigInteger promTime = BigInteger.ZERO;

    /**
     * Función main que llama a la función recursiva optimizada del coeficiente
     * binomial
     *
     * @param n
     * @throws IOException
     */
    public void newRecursive(BigInteger n) throws IOException {
        times = new BigInteger[n.intValue() + 1][5];
        System.out.println("********************Recursivo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            times[i][0] = n;
            times[i][1] = BigInteger.valueOf(i);
            Long ts = System.nanoTime();
            times[i][2] = betterRecursiveBinom(n, BigInteger.valueOf(i));
            times[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            promTime = promTime.add(times[i][3]);
        }
        showInfo();
        System.out.println("Promedio de los tiempos de ejecución(Recursivo): " + (promTime.divide(n)) + " nanosegundos");
        new ArchiveManager().createFile(times);
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
        times = new BigInteger[n.intValue() + 1][5];
        System.out.println("********************Recursivo********************");
        for (int i = 0; i <= n.intValue(); i++) {
            times[i][0] = n;
            times[i][1] = BigInteger.valueOf(i);
            Long ts = System.nanoTime();
            times[i][2] = recursiveBinom(n, BigInteger.valueOf(i));
            times[i][3] = BigInteger.valueOf(System.nanoTime() - ts);
            promTime = promTime.add(times[i][3]);
        }
        showInfo();
        System.out.println("Promedio de los tiempos de ejecución(Recursivo): " + (promTime.divide(n)) + " nanosegundos");
        new ArchiveManager().createFile(times);
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

    /**
     * Muestra la información obtenida
     */
    private void showInfo() {
        super.showInfo(times);
    }
}
