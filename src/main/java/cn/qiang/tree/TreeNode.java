package cn.qiang.tree;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TreeNode {
    private Object id;
    private Object parentId;
    private List children;
}