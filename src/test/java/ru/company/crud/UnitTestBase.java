package ru.company.crud;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.company.crud.initializer.Postgres;


@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
public abstract class UnitTestBase {

    @BeforeAll
    static void initContainer() {
        Postgres.container.start();
    }

}
