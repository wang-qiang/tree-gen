package cn.qiang.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TreeUtilTest {

    @Test
    public void testLongKey() {
        List<Resource> nodeList = new ArrayList<>();
        Resource treeNode1 = new Resource(1L, 0L, "首页", "/home");
        Resource treeNode2 = new Resource(2L, 0L, "产品", "/product");
        Resource treeNode3 = new Resource(3L, 0L, "解决方案", "/solution");
        Resource treeNode4 = new Resource(4L, 0L, "招聘", "/recruit");
        Resource treeNode5 = new Resource(5L, 2L, "产品1", "/product1");
        Resource treeNode6 = new Resource(6L, 0L, "后台", "/admin");
        Resource treeNode7 = new Resource(7L, 6L, "信息管理", "/management");
        Resource treeNode8 = new Resource(8L, 6L, "配置管理", "/configuration");
        Resource treeNode9 = new Resource(9L, 7L, "修改个人信息", "/user/modify");
        Resource treeNode10 = new Resource(10L, 9L, "修改密码", "/user/passwd");
        nodeList.add(treeNode1);
        nodeList.add(treeNode6);
        nodeList.add(treeNode5);
        nodeList.add(treeNode3);
        nodeList.add(treeNode4);
        nodeList.add(treeNode2);
        nodeList.add(treeNode7);
        nodeList.add(treeNode8);
        nodeList.add(treeNode9);
        nodeList.add(treeNode10);

        List<Resource> parseTree = new TreeUtil(nodeList).parseTree();
        Assert.assertEquals(5, parseTree.size());

        for (Resource resource : parseTree) {
            if (resource.getId().equals(6L)) {
                Assert.assertEquals(2, resource.getChildren().size());
            }
        }

        log.info(JSON.toJSONString(new TreeUtil(nodeList).parseTree(), SerializerFeature.WriteMapNullValue));
    }

    @Test
    public void testStringKey() {
        List<Resource2> nodeList = new ArrayList<>();
        Resource2 treeNode1 = new Resource2("1", null, "首页", "/home");
        Resource2 treeNode2 = new Resource2("2", null, "产品", "/product");
        Resource2 treeNode3 = new Resource2("3", null, "解决方案", "/solution");
        Resource2 treeNode4 = new Resource2("4", null, "招聘", "/recruit");
        Resource2 treeNode5 = new Resource2("5", "2", "产品1", "/product1");
        Resource2 treeNode6 = new Resource2("6", "0", "后台", "/admin");
        Resource2 treeNode7 = new Resource2("7", "6", "信息管理", "/management");
        Resource2 treeNode8 = new Resource2("8", "6", "配置管理", "/configuration");
        Resource2 treeNode9 = new Resource2("9", "7", "修改个人信息", "/user/modify");
        Resource2 treeNode10 = new Resource2("10", "9", "修改密码", "/user/passwd");
        nodeList.add(treeNode1);
        nodeList.add(treeNode6);
        nodeList.add(treeNode5);
        nodeList.add(treeNode3);
        nodeList.add(treeNode4);
        nodeList.add(treeNode2);
        nodeList.add(treeNode7);
        nodeList.add(treeNode8);
        nodeList.add(treeNode9);
        nodeList.add(treeNode10);

        List<Resource2> parseTree = new TreeUtil(nodeList).parseTree();
        Assert.assertEquals(5, parseTree.size());

        for (Resource2 resource2 : parseTree) {
            if (resource2.getId().equals("6")) {
                Assert.assertEquals(2, resource2.getChildren().size());
            }
        }

        log.info(JSON.toJSONString(parseTree, SerializerFeature.WriteMapNullValue));
    }


    /**
     * DB数据库返回资源对象
     */
    @Getter
    @Setter
    static class Resource extends TreeNode {
        private String name;
        private String path;

        Resource(Long id, Long parent, String name, String path) {
            super(id, parent, null);
            this.name = name;
            this.path = path;
        }
    }

    /**
     * DB返回资源对象
     */
    @Getter
    @Setter
    static class Resource2 extends TreeNode {
        private String name;
        private String path;

        Resource2(String id, String parent, String name, String path) {
            super(id, parent, null);
            this.name = name;
            this.path = path;
        }
    }
}
