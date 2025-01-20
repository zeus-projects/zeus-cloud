package tech.alexchen.zeus.test.data.redis.rank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {

    private String userId;
    private String username;
    private Double score;
}
