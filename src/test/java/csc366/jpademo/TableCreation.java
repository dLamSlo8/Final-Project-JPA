package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

        // uncomment these, and the @ActiveProfiles & @AutoConfig... annotations (below) to connect this test to MySQL
        "spring.jpa.hibernate.ddl-auto=update", //uncomment
        //create-drop
        //"spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/<username>",//uncoment
        //"spring.datasource.username=<username>", //uncomment
        //"spring.datasource.password=366_WinterTwenty20_<empid>",//uncomment
        
	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@ActiveProfiles("junit") // uncomment
@AutoConfigureTestDatabase(replace=Replace.NONE) // uncomment   // without this, @DataJpaTest always uses in-memory H2 database
@TestMethodOrder(OrderAnnotation.class)
public class TableCreation {
    
    private final static Logger log = LoggerFactory.getLogger(TableCreation.class);

    // no Spring repository in this demo, all work done via JPA EntityManager
    // https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html
    @Autowired
    private EntityManager entityManager;  
    
    @BeforeEach
    private void setup() {
        //Customer derek = new Customer("Derek");
        //entityManager.persist(derek);
        entityManager.flush();  // "Synchronize the persistence context to the underlying database"
        entityManager.clear();
    }
     
    @Test
    @Order(1)
    @Rollback(false)
    public void makeTables() {
    }
}
