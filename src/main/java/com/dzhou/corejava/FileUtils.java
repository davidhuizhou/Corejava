package com.dzhou.corejava;

import com.dzhou.util.Queue;

import java.io.File;

/**
 * Created by huizhou on 7/12/14.
 */
public class FileUtils {

    public void getFiles(Queue<Node> queue, File file, int indent){
        Node n = new Node(file.getName(), indent);
        queue.enqueue(n);

        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                getFiles(queue, f, indent + 1);
            }
        } else {
            return;
        }

    }

    public void listFiles(File file){
        if(file == null)
            return;

        Queue<Node> queue = new Queue();
        getFiles(queue, file, 0);

        for(Node n : queue){
            System.out.println(n.toString());
        }
    }

    private class Node {
        String name;
        int indent;

        Node(String name, int indent){
            this.name = name;
            this.indent = indent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndent() {
            return indent;
        }

        public void setIndent(int indent) {
            this.indent = indent;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < indent; i++)
                sb.append("\t");
            sb.append(name);
            return sb.toString();
        }
    }

    public static void main(String[] args){
        FileUtils util = new FileUtils();

        String path = "/Users/huizhou/Documents/DavidZhou/Private";
        File f = new File(path);
        util.listFiles(f);
    }

}
