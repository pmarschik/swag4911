import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import swag49.model.BuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

public class TroopActionAspectTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    private void createTestdata() {
        SimpleJdbcTemplate jt = new SimpleJdbcTemplate(dataSource);
        SimpleJdbcTestUtils.executeSqlScript(jt, new ClassPathResource("testdata_statistics.sql"), false);
    }

    @Test
    public void test() throws Exception {
        BuildAction action = new BuildAction();
        action.setConcerns();
    }
}
