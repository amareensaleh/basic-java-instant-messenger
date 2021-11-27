package bjim.server;

import static bjim.server.Server.DEFAULT_PORT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bjim.client.Client;
import bjim.client.ClientChatWindow;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.BadLocationException;

public class ServerTest {

    private static final int WAIT_SECS = 100;

    private static final int CUSTOM_PORT = 1234;

    final ServerChatWindow serverChatWindow = mock(ServerChatWindow.class);
    final ClientChatWindow clientChatWindow = mock(ClientChatWindow.class);

    Server server = new Server(serverChatWindow);

    @Before
    public void setUp() throws InterruptedException {
        server = new Server(serverChatWindow);
        server.startRunning();
        Thread.sleep(WAIT_SECS);
    }

    @After
    public void tearDown() throws InterruptedException {
        server.stopRunning();
        Thread.sleep(WAIT_SECS);
    }

    @Test
    public void startServer() {

        // then
        assertTrue(server.isRunning());
    }

    @Test
    public void stopServer() throws InterruptedException {

        // when
        server.stopRunning();

        // then
        Thread.sleep(5000);
        assertFalse(server.isRunning());
    }

    @Test
    public void serverStartsOnDefaultPort() {

        // when..then
        assertEquals(DEFAULT_PORT, server.getPort());
    }

    @Test
    public void serverStartsOnCustomPort() throws InterruptedException {

        // given
        Server customServer = new Server(CUSTOM_PORT, serverChatWindow);

        // before
        assertNotEquals(
                "Precondition violated: `customPort` MUST NOT be equal to DEFAULT_PORT: "
                        + DEFAULT_PORT,
                DEFAULT_PORT,
                CUSTOM_PORT);

        // when
        customServer.startRunning();

        // then
        Thread.sleep(WAIT_SECS);
        assertEquals(CUSTOM_PORT, customServer.getPort());

        // after code
        customServer.stopRunning();
    }

    @Test
    public void numberOfConnectedClientsIsZero() {

        // then
        assertEquals(0, server.numberOfClientsConnected());
    }

    @Test
    public void serverSendsAMessageAndClientReceivesIt() throws InterruptedException, BadLocationException {

        // given
        when(serverChatWindow.getUsername()).thenReturn("Server");
        ClientChatWindow clientChatWindow = mock(ClientChatWindow.class);
        Client client = new Client("127.0.0.1", clientChatWindow);
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when
        server.sendMessage("hi");
        Thread.sleep(500);

        // then
        assertEquals("Server:\n  hi", client.getLastReceivedMessage());

        // after
        client.stopRunning();
    }

    @Test
    public void serverUserMessageVisibleTrue() {

        // given
        when(serverChatWindow.isUserMessageVisible()).thenReturn(true);

        // then
        assertEquals(true, server.isServerMessageVisible());
    }

    @Test
    public void windowIsVisibleDuringWhenStartTheServer() {

        // given
        when(serverChatWindow.isVisible()).thenReturn(true);

        // then
        assertEquals(true, server.isWindowVisible());
    }

    @Test
    public void server_accept_client_request_to_connect() throws InterruptedException, IOException {

        // given

        when(serverChatWindow.isVisible()).thenReturn(true);
        when(clientChatWindow.isVisible()).thenReturn(true);

        // then
        assertTrue(server.serversocketcondition());
    }

    @Test
    public void ServerCannotWriteIfClientIsNotConnected() {

        // given
        when(serverChatWindow.isVisible()).thenReturn(true);

        // then
        assertFalse(server.abletowrite());
    }

    @Test
    public void ServerCannotWriteIfServerappStartsAfterClientappStarts() {

        // given
        when(clientChatWindow.isVisible()).thenReturn(true);
        when(serverChatWindow.isVisible()).thenReturn(true);

        // then
        assertFalse(server.abletowrite());
    }

    // test
    @Test
    public void server_shows_message_after_connection() throws InterruptedException { // given

        when(serverChatWindow.isVisible()).thenReturn(true);

        // then
        Thread.sleep(5000);
        String actual = server.getmessage();
        assertEquals("Waiting for clients to connect!", actual);
    }
}
