package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.movie.MovieDTO;
import entities.Movie;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.minidev.json.JSONObject;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class MovieResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/";
    private EntityManager em;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Movie m1, m2, m3;
    private static User u1, u2;
    private static Role r1, r2;
    private static String securityToken;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;

        //Create 2 dummy users
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();

            m1 = new Movie("Title 1", 2020, "plot", "Directors", "genres", "castname1, castname2", "poster");
            m2 = new Movie("Title2", 2010, "plot", "Directors", "genres", "castname1, castname3", "poster");
            m3 = new Movie("Title3", 2010, "plot", "Directors", "genres", "castname1", "poster");

            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            u1 = new User("user", "test");
            u2 = new User("admin", "test");

            Role r1 = new Role("user");
            Role r2 = new Role("admin");

            u1.addRole(r1);
            u2.addRole(r2);

            em.persist(r1);
            em.persist(r2);

            em.persist(u1);
            em.persist(u2);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/login")
                .then()
                .extract().path("token");
        System.out.println("TOKEN ---> " + securityToken);
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @Test
    public void testMovieV2Resource() {
        MovieDTO[] result
                = with()
                        .contentType("application/json")
                        .when().request("GET", "/movie-info-all-v2/Title2").then() //get REQUEST
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(MovieDTO[].class);

        assertEquals("Title2", result[0].getTitle());
        assertEquals(2010, result[0].getYear());
    }

    @Test
    public void testMovieCountResource() {
        login("admin", "test");
        
        MovieDTO[] result
                = with()
                        .contentType("application/json")
                        .accept(ContentType.JSON)
                        .header("x-access-token", securityToken)
                        .when().request("GET", "/movie-count/Title_1").then() //get REQUEST
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(MovieDTO[].class);

        assertEquals("Title 1", result[0].getTitle());
        assertEquals(2020, result[0].getYear());
        assertEquals(2, result[0].getNumberOfSearches());
    }

}
