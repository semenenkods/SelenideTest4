import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPageTest {
    @Test
    void successfulSearchTest() {

        Configuration.browser="edge";


        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();

        $$("div.markdown-body ul li a").shouldHave(itemWithText("Soft assertions"));

        $(byText("Soft assertions")).click();
        $("#user-content-3-using-junit5-extend-test-class").$("a").shouldHave(text("3. Using JUnit5 extend test class:"));

        $("#user-content-3-using-junit5-extend-test-class").sibling(2).shouldHave(text("SoftAssertsExtension"));
        $("#user-content-3-using-junit5-extend-test-class").sibling(0).shouldHave(exactText("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

        $("#user-content-3-using-junit5-extend-test-class").sibling(2).shouldHave(exactText("class Tests {\n" +
                "  @RegisterExtension \n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }
}

