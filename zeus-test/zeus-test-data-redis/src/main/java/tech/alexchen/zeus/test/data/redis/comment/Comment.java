package tech.alexchen.zeus.test.data.redis.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    private Long id;

    private String videoId;

    private String content;

    private String username;

    private LocalDateTime time;
}
