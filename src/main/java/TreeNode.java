import java.util.ArrayList;

public class TreeNode {

  TreeNode left;
  TreeNode right;
  String word;
  int countDuplicates;
  ArrayList<Integer> treeNodesPositions;

  public TreeNode(String word, int position) {

    this.word = word;
    this.countDuplicates = 1;
    left = right = null;
    treeNodesPositions = new ArrayList<>();
    treeNodesPositions.add(position);
  }
}
