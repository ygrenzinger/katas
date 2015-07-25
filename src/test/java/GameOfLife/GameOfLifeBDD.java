package GameOfLife;

import net.serenitybdd.jbehave.SerenityStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeBDD extends SerenityStory {

    @Given("there is a cell with two live neighbours dies")
    public void addingCell(int x, int y) {
    }

    @When("the next generation is created")
    public void passingToTheNextGeneration() {
    }

    @Then("this cell is dead")
    public void livingCell(int x, int y) {

    }


}
