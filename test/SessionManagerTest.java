
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import sunrisedentalclinic.utils.SessionManager;

public class SessionManagerTest {
    @After
    public void tearDown() {
        SessionManager.getInstance().clearSession();
    }
    @Test
    public void testSingletonReturnsSameInstance() {
        SessionManager first = SessionManager.getInstance();
        SessionManager second = SessionManager.getInstance();
        assertSame("getInstance() must return the same object", first, second);
    }
    @Test
    public void testNewSessionIsNotLoggedIn() {
        SessionManager sm = SessionManager.getInstance();
        sm.clearSession();
        assertFalse("Fresh session should not be logged in", sm.isLoggedin());
        assertNull("Username should be null", sm.getCurrentUser());
        assertNull("Role should be null", sm.getCurrentRole());
    }
    @Test
    public void testCreateSessionSetsValues() {
        SessionManager sm = SessionManager.getInstance();
        sm.createSession("admin", "Admin");
        assertTrue("Should be logged in after createSession", sm.isLoggedin());
        assertEquals("admin", sm.getCurrentUser());
        assertEquals("Admin", sm.getCurrentRole());
    }
    @Test
    public void testClearSessionResetsValues() {
        SessionManager sm = SessionManager.getInstance();
        sm.createSession("admin", "Admin");
        sm.clearSession();
        assertFalse("Should not be logged in after clearSession", sm.isLoggedin());
        assertNull("Username should be null after clear", sm.getCurrentUser());
        assertNull("Role should be null after clear", sm.getCurrentRole());
    }
}
