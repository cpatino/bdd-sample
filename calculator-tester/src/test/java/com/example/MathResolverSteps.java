package com.example;

import com.example.adapter.outbound.MathResolverWebClient;
import com.example.domain.model.MathOperation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest
public class MathResolverSteps {

    private final MathResolverWebClient mathResolverWebClient;
    private MathOperation mathOperation;

    public MathResolverSteps(MathResolverWebClient mathResolverWebClient) {
        this.mathResolverWebClient = mathResolverWebClient;
    }

    @Given("the math operation {string}")
    public void prepareMathOperation(String requestOperation) {
        mathOperation = new MathOperation(requestOperation);
        assertThat(mathOperation.operation()).isNotBlank();
    }

    @When("resolving the operation")
    public void resolveUsingServiceCall() {
        var response = mathResolverWebClient.resolve(mathOperation.operation());
        mathOperation = new MathOperation(mathOperation.operation(), response);
        assertThat(mathOperation.response()).isNotNull();
    }

    @Then("the result must be {string}")
    public void validateResult(String expectedResult) {
        assertThat(mathOperation.response().getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(mathOperation.response().getBody()).isNotNull();
        assertThat(Double.parseDouble(mathOperation.response().getBody())).isEqualTo(Double.parseDouble(expectedResult));
    }

    @Then("the user receives the client error message {string}")
    public void validate400Errors(String expectedMessage) {
        assertThat(mathOperation.response().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(mathOperation.response().getBody()).isNotNull();
        assertThat(mathOperation.response().getBody()).contains(expectedMessage);
    }
}
