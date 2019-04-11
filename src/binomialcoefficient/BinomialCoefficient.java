package binomialcoefficient;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author manotasja
 * @since 7/4/2019
 * @version 1
 */
public class BinomialCoefficient {

    public static void main(String[] args) throws IOException {
        System.out.print("Digite n: ");
        BigInteger n = new Scanner(System.in).nextBigInteger();
        /*for (int i = 50; i <= n.intValue(); i += 50) {
            new Iterative().newIterative(BigInteger.valueOf(i));
            new Recursive().newRecursive(BigInteger.valueOf(i));
        }*/
        /*for (int i = 0; i <= n.intValue(); i++) {
            new Iterative().oldIterative(BigInteger.valueOf(i));
            new Recursive().oldRecursive(BigInteger.valueOf(i));
        }*/
        new Iterative().newIterative(n);
        //new Recursive().newRecursive(n);
        //new Iterative().specificIterative();
        //new Recursive().SpecificRecursive();
        new Iterative().oldIterative(n);
        //new Recursive().oldRecursive(n);
    }

}
