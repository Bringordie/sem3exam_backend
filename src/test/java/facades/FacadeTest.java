package facades;

import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import utils.EMF_Creator;


public class FacadeTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    
    private User u1, u2;
    private Role r1, r2;

    
    
    public FacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        //facade = OrderFacade.getOrderFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            
            u1 = new User("test1", "test1");
            u2 = new User("test2", "test2");
            
            r1 = new Role("user");
            r2 = new Role("admin");

            u1.addRole(r1);
            u2.addRole(r2);
            
            em.persist(r1);
            em.persist(r2);
            
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            //TO DO
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    



   


}
