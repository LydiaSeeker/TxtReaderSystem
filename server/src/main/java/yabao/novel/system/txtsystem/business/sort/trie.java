package yabao.novel.system.txtsystem.business.sort;




public class trie {


    public  static  class Node {
        private int pass;
        private int end;
        private Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            //0  a
            //1  b
            //2  c
            //25 z
            //next[i] == null i方向的路不存在
            //next[i] != null i方向上的路存在
            nexts = new Node[26];
        }
    }


    public static class Tire {
        private Node root;

        public Tire() {
            root = new Node();
        }

        //添加 abc acd bca
        public void insert(String word) {

            if(word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass ++ ;
            int path = 0;
            for(int i = 0 ; i<chars.length;i++) {
                path = chars[i] - 'a';
                if(node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end ++;
        }

        //找有几个abc
        public int search(String word) {
            if(word ==null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path = 0;
            Node currentNode = root;
            for(int i=0;i<chars.length;i++) {
                path = chars[i] - 'a';
                if(currentNode.nexts[path] == null || currentNode.nexts[path].pass == 0) {
                    return 0;
                }
                currentNode = currentNode.nexts[i];
            }
            if(currentNode != null) {
                return currentNode.end;
            }
            return 0;
        }

        public int prefixNumber(String word) {
            if(word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path = 0;
            Node currentNode = root;
            for(int i = 0 ;i < chars.length;i++) {
                path = chars[i]-'a';

                if(currentNode.nexts[i]== null) {
                    return 0;
                }

                currentNode = currentNode.nexts[i];
            }

            return currentNode.pass;
        }

        public void delete(String word) {
            if(search(word) != 0) {
                char[] chars = word.toCharArray();
                Node currentNode = root;
                currentNode.pass--;

                int index = 0;
                for (char aChar : chars) {
                    index = aChar-'a';

                    if(--currentNode.nexts[index].pass == 0) {
                        currentNode.nexts[index] = null;
                        return;
                    }
                    currentNode = currentNode.nexts[index];
                }
                currentNode.end --;
            }
        }


        public static void main(String[] args) {
            Tire tire = new Tire();
            tire.insert("abc");
            tire.insert("bcd");
            tire.insert("abc");
            int abc = tire.search("abc");
            System.out.println(abc);

            tire.delete("abc");
            int abc1 = tire.search("abc");
            System.out.println(abc1);
        }



    }





    public static void main(String[] args) {

        Node root;





    }
}
