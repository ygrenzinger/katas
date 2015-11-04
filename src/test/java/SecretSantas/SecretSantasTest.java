package SecretSantas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SecretSantasTest {

    @Test
    public void should_return_pairs_of_unique_giver_receiver() {
        List<String> persons = new ArrayList<>();
        persons.add("Saul Goodman");
        persons.add("Jesse Pinkman");

        final Set<String> pairsOfGiverReceiver = SecretSantas.buildPairOfGiverReceiver(persons);
        assertThat(pairsOfGiverReceiver).contains("Saul Goodman -> Jesse Pinkman");
        assertThat(pairsOfGiverReceiver).contains("Jesse Pinkman -> Saul Goodman");
    }

    @Test
    public void should_return_a_unique_pairs_of_giver_receiver_without_giving_to_the_same_family() {
        List<String> persons = new ArrayList<>();
        persons.add("Saul Goodman");
        persons.add("Jesse Pinkman");
        persons.add("Walter White");
        persons.add("Skyler White");

        final Set<String> pairsOfGiverReceiver = SecretSantas.buildPairOfGiverReceiver(persons);
        assertThat(pairsOfGiverReceiver).contains("Walter White -> Jesse Pinkman");
        assertThat(pairsOfGiverReceiver).contains("Skyler White -> Saul Goodman");
        assertThat(pairsOfGiverReceiver).contains("Saul Goodman -> Skyler White");
        assertThat(pairsOfGiverReceiver).contains("Jesse Pinkman -> Walter White");
    }

    @Test
    public void should_return_pairs_of_giver_receiver_with_email() {
        List<String> persons = new ArrayList<>();
        persons.add("Saul Goodman <saul@goodman.com>");
        persons.add("Jesse Pinkman <jesse.pinkman@gmail.com>");
        persons.add("Walter White <walter@caltech.edu>");
        persons.add("Skyler White <skyler@gmail.com>");

        final Set<String> pairsOfGiverReceiver = SecretSantas.buildPairOfGiverReceiver(persons);
        assertThat(pairsOfGiverReceiver).contains("Walter White -> Jesse Pinkman <jesse.pinkman@gmail.com>");
        assertThat(pairsOfGiverReceiver).contains("Skyler White -> Saul Goodman <saul@goodman.com>");
        assertThat(pairsOfGiverReceiver).contains("Saul Goodman -> Skyler White <skyler@gmail.com>");
        assertThat(pairsOfGiverReceiver).contains("Jesse Pinkman -> Walter White <walter@caltech.edu>");
    }

}
