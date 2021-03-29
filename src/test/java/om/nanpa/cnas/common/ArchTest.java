package om.nanpa.cnas.common;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("om.nanpa.cnas.common");

        noClasses()
            .that()
            .resideInAnyPackage("om.nanpa.cnas.common.service..")
            .or()
            .resideInAnyPackage("om.nanpa.cnas.common.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..om.nanpa.cnas.common.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
