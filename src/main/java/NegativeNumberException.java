import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yannickgrenzinger on 28/06/2015.
 */
public class NegativeNumberException extends Exception {
    public NegativeNumberException(List<String> negativeNumbers) {
        super("Negative numbers: " + negativeNumbers.stream().collect(Collectors.joining(" ")));
    }
}
