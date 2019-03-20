package cn.qiang.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 树结构生成工具类
 *
 * @param <T>
 */
public class TreeUtil<T extends TreeNode> {

    private static final Object[] ROOT_PARENT_CONSTANT = new Object[]{"", "0", 0, 0L, 0F, 0D};

    private List<T> allNodes;

    TreeUtil(List<T> nodes) {
        this.allNodes = nodes;
    }

    /**
     * parse all nodes to a tree
     *
     * @return
     */
    public List<T> parseTree() {
        if (allNodes == null || allNodes.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<T> treeList = getRootNode();
        if (treeList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return treeList;
    }

    private List<T> getRootNode() {
        List<T> treeList = new ArrayList<>();
        for (T node : allNodes) {
            if (isRootParent(node.getParentId())) {
                nodeToTree(treeList, node);
            }
        }

        return treeList;
    }

    private List<T> getChildren(T treeNode) {
        List<T> children = new ArrayList<>();
        for (T node : allNodes) {
            if (treeNode.getId().equals(node.getParentId())) {
                nodeToTree(children, node);
            }
        }

        return children;
    }

    private void nodeToTree(List<T> treeList, T node) {
        node.setChildren(getChildren(node));
        treeList.add(node);
    }

    private boolean isRootParent(Object parentId) {
        if (parentId == null) {
            return true;
        }

        for (Object parentConstant : ROOT_PARENT_CONSTANT) {
            if (parentId.equals(parentConstant)) {
                return true;
            }
        }

        return false;
    }
}
