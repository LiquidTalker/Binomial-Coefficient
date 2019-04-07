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
        new Iterative().iterative(n);
        new Recursive().recursive(n);
    }

}
