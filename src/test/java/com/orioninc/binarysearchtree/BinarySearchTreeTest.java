package com.orioninc.binarysearchtree;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test com.orioninc.binarysearchtree.BinarySearchTree class")
class BinarySearchTreeTest {
  BinarySearchTree bst = new BinarySearchTree();

  @Test
  @DisplayName("Test search with existing value")
  void checkDoesBSTSearchWorksReturnsTrue() {
    bst.insert("good", 1);
    assertTrue(bst.search("good"));
  }

  @Test
  @DisplayName("Test search with not existing value")
  void checkDoesBSTSearchWorksReturnsFalse() {
    bst.insert("good", 1);
    assertFalse(bst.search("goods"));
  }

  @Test
  @DisplayName("Test search in empty tree")
  void checkDoesBSTSearchFailsInEmptyTreeReturnsFalse() {
    assertFalse(bst.search("goods"));
  }

  @Test
  @DisplayName("Test 1 for duplicates")
  void checkDoesDuplicatesCalcWorksReturnTrue() {
    for (int i = 1; i < 10; i++) {
      bst.insert("test", i);
    }

    assertEquals(9, bst.displayCount("test"));
  }

  @Test
  @DisplayName("Test 2 for duplicates")
  void checkDoesDuplicatesCalcWorksReturnFalse() {

    for (int i = 1; i < 10; i++) {
      bst.insert("test", i);
    }

    assertNotEquals(11, bst.displayCount("test"));
  }

  @Test
  @DisplayName("Test 1 for positions")
  void checkDoesDuplicatesPositionStoreReturnTrue() {

    bst.insert("test1", 1);
    bst.insert("test2", 2);
    bst.insert("test1", 3);
    bst.insert("test2", 4);
    bst.insert("test1", 5);
    bst.insert("test2", 6);

    ArrayList<Integer> testArrayList = new ArrayList<>(Arrays.asList(1,3,5));
    assertArrayEquals(testArrayList.toArray(), bst.displayPositions("test1").toArray());
  }

  @Test
  @DisplayName("Test 2 for positions")
  void checkDoesDuplicatesPositionStoreReturnFalse() {

  bst.insert("test1", 1);
  bst.insert("test2", 2);
  bst.insert("test1", 3);
  bst.insert("test2", 4);
  bst.insert("test1", 5);
  bst.insert("test2", 6);

  ArrayList<Integer> testArrayList = new ArrayList<>(Arrays.asList(2,3,5));

  assertThat(testArrayList, IsNot.not(IsEqual.equalTo(bst.displayPositions("test1").toArray()))); //
  }
}