package Week4;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.StringWriter;
import java.util.Random;

public class MailClientTest {

    private MailClient client;

    public MailClientTest() {
        client = new MailClient();
    }

    @Before
    public void setupTest() {
        client = new MailClient();
    }

    @After
    public void tearDownTest() {
        client = null;
    }

    // Legitimate values in all fields
    @Test
    public void successTest() {
        client.outgoingServerAddress = "mail.sutd.edu.sg";
        client.mailServerAddress = "imap.sutd.edu.sg";
        client.emailAddress = "me@sutd.edu.sg";
        client.subject = "Hello World";
        assertEquals(true, client.send());
    }

    // Problematic outgoingServerAddress - invalid chars
    @Test
    public void failureTest1() {
        client.outgoingServerAddress = ".x2jq9r2-923sad0c";
        client.mailServerAddress = "imap.sutd.edu.sg";
        client.emailAddress = "me@sutd.edu.sg";
        client.subject = "Hello World";
        assertEquals(true, client.send());
    }

    // Problematic mailServerAddress - double period
    @Test
    public void failureTest2() {
        client.outgoingServerAddress = "mail.sutd.edu.sg";
        client.mailServerAddress = "imap.sutd.edu..sg";
        client.emailAddress = "me@sutd.edu.sg";
        client.subject = "Hello World";
        assertEquals(true, client.send());
    }

    // Problematic emailAddress - double @
    @Test
    public void failureTest3() {
        client.outgoingServerAddress = "mail.sutd.edu.sg";
        client.mailServerAddress = "imap.sutd.edu.sg";
        client.emailAddress = "me@@sutd.edu.sg";
        client.subject = "Hello World";
        assertEquals(true, client.send());
    }

    // Subject size over 1MB
    @Test
    public void failureTest4() {

        Random r = new Random();
        String hugeString;
        StringWriter writer = new StringWriter();

        for (int i = 0; i < 200000; i++) {
            writer.append(Integer.toString(r.nextInt()));
        }

        hugeString = writer.toString();

        client.outgoingServerAddress = "mail.sutd.edu.sg";
        client.mailServerAddress = "imap.sutd.edu.sg";
        client.emailAddress = "me@sutd.edu.sg";
        client.subject = hugeString;
        assertEquals(true, client.send());
    }

    // Problematic emailAddress - mailServerAddress does not point to a mail server
    @Test
    public void failureTest5() {
        client.outgoingServerAddress = "mail.sutd.edu.sg";
        client.mailServerAddress = "en.wikipedia.org";
        client.emailAddress = "me@sutd.edu.sg";
        client.subject = "Hello World";
        assertEquals(true, client.send());
    }
}

// Simulation of black box mail client
class MailClient {
    public String outgoingServerAddress;
    public String mailServerAddress;
    public String emailAddress;
    public String subject;
    boolean send() {
        return true;
    }
}