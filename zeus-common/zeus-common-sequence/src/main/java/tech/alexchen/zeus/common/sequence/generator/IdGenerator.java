package tech.alexchen.zeus.common.sequence.generator;

/**
 * ID 生成器接口
 *
 * @author alexchen
 */
public interface IdGenerator {

    Long nextId();

    String nextStringId();
}
