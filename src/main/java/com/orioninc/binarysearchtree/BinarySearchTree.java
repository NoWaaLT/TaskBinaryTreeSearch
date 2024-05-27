package com.orioninc.binarysearchtree;

import java.util.ArrayList;

public class BinarySearchTree {
  TreeNode root;

  BinarySearchTree() {
    root = null;
  }

  public void insert(String word, int position) {
    root = insertHelper(root, word, position);
  }

  private TreeNode insertHelper(TreeNode root, String word, int position) {

    if (root == null) {
      root = new TreeNode(word, position);

      return root;

    } else if (word.compareTo(root.word) < 0) {
      root.left = insertHelper(root.left, word, position);
    } else if (word.compareTo(root.word) > 0) {
      root.right = insertHelper(root.right, word, position);
    } else {
      root.countDuplicates++;
      root.treeNodesPositions.add(position);
    }

    return root;
  }

  public int displayCount(String word) {
    return displayCountHelper(root, word);
  }

  private int displayCountHelper(TreeNode root, String word) {

    if (root == null) {
      return 0;
    } else if (word.compareTo(root.word) < 0) {
      return displayCountHelper(root.left, word);
    } else if (word.compareTo(root.word) > 0) {
      return displayCountHelper(root.right, word);
    } else {
      return root.countDuplicates;
    }
  }

  public ArrayList<Integer> displayPositions(String word) {
    return displayPositionsHelper(root, word);
  }

  private ArrayList<Integer> displayPositionsHelper(TreeNode root, String word) {
    if (root == null) {
      return new ArrayList<>();
    } else if (word.compareTo(root.word) < 0) {
      return displayPositionsHelper(root.left, word);
    } else if (word.compareTo(root.word) > 0) {
      return displayPositionsHelper(root.right, word);
    } else {
      return root.treeNodesPositions;
    }
  }

  public void displayTree() {
    displayTreeHelper(root);
  }

  private void displayTreeHelper(TreeNode root) {

    if (root != null) {
      displayTreeHelper(root.left);
      System.out.println(root.word);
      displayTreeHelper(root.right);
    }
  }

  public boolean search(String word) {
    return searchHelper(root, word);
  }

  private boolean searchHelper(TreeNode root, String word) {

    if (root == null) {
      return false;
    } else if (root.word.equals(word)) {
      return true;
    } else if (root.word.compareTo(word) > 0) {
      return searchHelper(root.left, word);
    } else {
      return searchHelper(root.right, word);
    }
  }
}
