package hex.cryptocurrencyexchange;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "hex.cryptocurrencyexchange..")
public class AppStructureTest {

    @ArchTest
    public void Services_should_only_be_accessed_by_Controllers(JavaClasses classes) {
        noClasses().that().resideInAPackage("hex.cryptocurrencyexchange.domain")
                .should().dependOnClassesThat().resideInAPackage("hex.cryptocurrencyexchange.adapter")
                .check(classes);
    }
}
