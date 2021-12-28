package yabao.novel.system.txtsystem.business.sort;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class tree {

    public static class Node {
        public Node left;
        public Node right;
        public int val;

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    public static void CFS(Node root) {

        Queue<Node> queue= new ConcurrentLinkedQueue<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.val);
            if(poll.left!=null) {
                queue.add(poll.left);
            }
            if(poll.right!=null) {
                queue.add(poll.right);
            }
        }
    }

    public static <T extends Collection> void  SoutList(T list ) {
        for (Object o : list) {
            if(o instanceof Node)
            System.out.println(((Node) o).val);
        }
    }

    //序列化
    public static  Queue<Node> CFSSerial(Node root) {

        Queue<Node> queue= new LinkedList<>();
        Queue<Node> serial = new LinkedList<>();
        queue.add(root);
        serial.add(root);
        while(!queue.isEmpty()) {
            Node poll = queue.poll();
//            System.out.println(poll.val);
            if(poll.left!=null) {
                queue.add(poll.left);
                serial.add(poll.left);
            } else {
                serial.add(null);
            }
            if(poll.right!=null) {
                queue.add(poll.right);
                serial.add(poll.right);
            } else {
                serial.add(null);
            }
        }

        SoutList(serial);
        return serial;
    }

    //序列化 的时候是 拿一个节点必然会去添加它的左右节点
    //反序列化的时候是 拿一个节点必然加上它的左右节点
    public static Node CFSDeserial(Queue<Node> serial) {
        Queue<Node> queue= new LinkedList<>();
        Node root = serial.poll();
        queue.add(root);
        Node root1 = root;
        while(!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.val);
            poll.left = serial.poll();
            poll.right = serial.poll();
            if(poll.left!=null) {
                queue.add(poll.left);
            }
            if(poll.right!=null) {
                queue.add(poll.right);
            }
        }
//        SoutList(serial);
        return root1;
    }


    public static void main(String[] args) {
        Node node8 = new Node(null, null, 8);
        Node node9 = new Node(null, null, 9);
        Node node6 = new Node(node8, node9, 6);
        Node node4 = new Node(null, null, 4);
        Node node5 = new Node(null, null, 5);
        Node node7 = new Node(null, null, 7);
        Node node2 = new Node(node4, node5, 2);
        Node node3 = new Node(node6,node7,3);
        Node root = new Node(node2,node3,1);

//        CFS(root);
        Queue<Node> nodes = CFSSerial(root);
        Node node = CFSDeserial(nodes);
        System.out.println(node);
    }



}
