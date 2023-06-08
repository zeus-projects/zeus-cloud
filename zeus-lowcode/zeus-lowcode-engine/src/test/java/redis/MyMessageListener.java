package redis;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author alexchen
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(@NotNull Message message, byte[] pattern) {
        System.out.println("收到 PatternTopic 消息：");
        System.out.println("线程编号：" + Thread.currentThread().getName());
        System.out.println("message：" + message);
        System.out.println("pattern：" + new String(pattern));
    }
}
