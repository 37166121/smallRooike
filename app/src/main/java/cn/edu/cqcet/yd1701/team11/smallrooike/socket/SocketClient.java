package cn.edu.cqcet.yd1701.team11.smallrooike.socket;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/24 20:26 星期五
 */
public class SocketClient extends WebSocketClient {
    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    public SocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public SocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public SocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public SocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    /**
     * 链接打开
     * @param handshakedata
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    /**
     * 新消息
     * @param message
     */
    @Override
    public void onMessage(String message) {

    }

    /**
     * 链接关闭
     * @param code
     * @param reason
     * @param remote
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    /**
     * 链接错误
     * @param ex
     */
    @Override
    public void onError(Exception ex) {

    }
}
