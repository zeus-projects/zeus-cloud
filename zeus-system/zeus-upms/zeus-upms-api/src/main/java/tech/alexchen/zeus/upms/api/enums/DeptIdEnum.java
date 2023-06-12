package tech.alexchen.zeus.upms.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author alexchen
 */
@Getter
@AllArgsConstructor
public enum DeptIdEnum {

    /**
     * 部门的根结点，最高一级的部门 parentId 都是它
     */
    ROOT_DEPT_ID(0L);

    private final Long id;
}
