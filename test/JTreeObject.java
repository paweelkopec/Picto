/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import pictodisplayer.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTree.*;
import java.awt.BorderLayout;
import java.util.*;

public class JTreeObject extends javax.swing.JTree{
  String[][] Data = {
  {"Amar"}, {"BCA", "Address","rohini","Delhi"},
  {"Vinod"}, {"BCA", "Software", "Rohini", "Delhi"},
  {"Chandan"}, {"MCA", "Software", "Programer", "Rohini", "Delhi"}, 
  {"Suman"}, {"MCA", "Deginer", "Web", "Delhi"},
  {"Ravi"},{"MCA","Software","programer"}};

//  public JTreeObject(){
//  super("JTreeobject frame");
//  setSize(300, 250);
//  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  }

  public void init(){
              javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("JsfsdfsTree");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockefgxvcadrsxgvdsxgvsdy");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        this.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

//  Hashtable hash = new Hashtable();
//  for (int i = 0; i < Data.length; i+=2){
//  hash.put(Data[i][0], Data[i + 1]);
//  }
//  JTree tree = new JTree(hash);
//  getContentPane().add(tree, BorderLayout.CENTER);
  }
  public static void main(String args[]){
  JTreeObject object = new JTreeObject();
  object.init();
  object.setVisible(true);
  }
}