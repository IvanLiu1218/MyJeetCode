package com.ivanliu.jeetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



import java.util.Set;

import com.ivanliu.jeetcode.Solution.ListNode;
import com.ivanliu.jeetcode.Solution.TreeLinkNode;
import com.ivanliu.jeetcode.Solution.TreeNode;

public class MyMain {

	public static void main(String[] args) {
		
		runJettCode();
		//runInterview();

	}
	
	@SuppressWarnings("serial")
	public static void runJettCode() {
		
		Solution sol = new Solution();
		
		String input0001 = "My name is Ivan  Liu";
		String output0001 = sol.reverseWords(input0001);
		System.out.println(output0001);
		
		// ["2", "1", "+", "3", "*"] 9
		// ["4", "13", "5", "/", "+"]
		String[] input0002 = new String[] {"4", "13", "5", "/", "+"};
		int output0002 = sol.evalRPN(input0002);
		System.out.println(output0002);
		
		TreeNode node1 = new TreeNode(2);
		node1.left = new TreeNode(3);
		node1.right = null;
		TreeNode root1 = new TreeNode(1);
		root1.left = null;
		root1.right = node1;
		List<Integer> output0003 = sol.postorderTraversal(root1);
		for (int i = 0 ; i < output0003.size(); ++i) {
			
			System.out.print(output0003.get(i) + " ");
		}
		System.out.println("");
		
		List<Integer> output0004 = sol.preorderTraversal(root1);
		for (int i = 0 ; i < output0004.size(); ++i) {
			
			System.out.print(output0004.get(i) + " ");
		}
		System.out.println("");
		
		//ListNode list1 = buildListNode(new int[]{3,4,7,10,21});
		//printListNode(list1);
		//ListNode list2 = buildListNode(new int[]{0,1,4,7});
		//printListNode(list2);
		//ListNode head = sol.mergeListNode(list1, list2);
		ListNode list = buildListNode(new int[] {10,3,7,21,4,7,3,4,0});
		//Solution.printListNode(list);
		Utility.printListNode(list);
		ListNode head = sol.sortList(list);
		Utility.printListNode(head);
		
		list = buildListNode(new int[] {10,3,7,21,4,7,3,4,0});
		head = sol.insertionSortList(list);
		//Solution.printListNode(head);
		Utility.printListNode(head);
		
		ListNode n0 = new ListNode(3);
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(0);
		ListNode n3 = new ListNode(-4);
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n1;
		
		System.out.println(sol.hasCycle(n0));
		
		ListNode r = sol.detectCycle(n0);
		System.out.println(r.val);
		
		//String s = "leetcode";
		//Set<String> dict = new HashSet<String>();
		//dict.add("leet");
		//dict.add("code");
		/*
		String s = "anavebaron";
		Set<String> dict = buildSet(new String[] {"a","an","ave","bar","baron","nave","on"});
		//System.out.println(sol.wordBreak(s, dict));
		
		s = "catsanddog";
		dict = buildSet(new String[]{"cat", "cats", "and", "sand", "dog"});
		List<String> rr = sol.wordBreak(s, dict);
		for (int i = 0; i < rr.size(); ++i) {
			
			System.out.println(rr.get(i));
		}*/
		
		ListNode list2 = buildListNode(new int[]{1,2,3,4});
		sol.reorderList(list2);
		//Solution.printListNode(list2);
		Utility.printListNode(list2);
		
		int[] gas = new int[] {2,4};
		int[] cost = new int[] {3,4};
		System.out.println(sol.canCompleteCircuit(gas, cost));
		
		// Palindrome Partitioning
		//String input2 = "eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj";
		//List<List<String>> resList = sol.partition(input2);
		//Solution.printListII(resList);
		
		// Sum Root to Leaf Numbers
		//TreeNode root = buildTree();
		//System.out.println(sol.sumNumbers(root));
		
		// Longest Consecutive Sequence
		//System.out.println(sol.longestConsecutive(new int[] {2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645}));
		
		
		// Best Time to Buy and Sell Stock
		//int[] prices = new int[] {};
		//int profit = sol.maxProfit(prices);
		//System.out.println(profit);
		/*
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> tlist = new ArrayList<Integer>();
		tlist.add(2);
		triangle.add(tlist);
		tlist = new ArrayList<Integer>();
		tlist.add(3);
		tlist.add(4);
		triangle.add(tlist);
		tlist = new ArrayList<Integer>();
		tlist.add(6);
		tlist.add(5);
		tlist.add(7);
		triangle.add(tlist);
		tlist = new ArrayList<Integer>();
		tlist.add(4);
		tlist.add(1);
		tlist.add(8);
		tlist.add(3);
		triangle.add(tlist);
		*/
		/*
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> tlist = new ArrayList<Integer>();
		tlist.add(-1);
		triangle.add(tlist);
		tlist = new ArrayList<Integer>();
		tlist.add(2);
		tlist.add(3);
		triangle.add(tlist);
		tlist = new ArrayList<Integer>();
		tlist.add(1);
		tlist.add(-1);
		tlist.add(-3);
		triangle.add(tlist);
		*/
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> tlist = new ArrayList<Integer>();
		tlist.add(-1);
		triangle.add(tlist);
		System.out.println(sol.minimumTotal(triangle));
		
		// Pascal's Triangle 
		List<List<Integer>> llist = sol.generate(1);
		sol.printListIntegerII(llist);
		
		// Pascal's Triangle II
		System.out.println("Pascal's Triangle II");
		Utility.printList(sol.getRow(0));
		//sol.printListIntegerI(sol.getRow(0));
		
		// Path Sum
		System.out.println("Path Sum");
		TreeNode root = buildTree01();
		System.out.println(sol.hasPathSum(root, 22));
		
		// Path Sum II
		System.out.println("Path Sum II");
		root = buildTree02();
		List<List<Integer>> rlist = sol.pathSum(root, 22);
		sol.printListIntegerII(rlist);
		
		// Populating Next Right Pointers in Each Node
		System.out.println("Populating Next Right Pointers in Each Node");
		TreeLinkNode treeLink = buildTreeLinkI();
		sol.connectI(treeLink);
		Utility.printTreeLinkNodeNext(treeLink);
		
		// Populating Next Right Pointers in Each Node II
		System.out.println("Populating Next Right Pointers in Each Node II");
		treeLink = buildTreeLinkII();
		sol.connect(treeLink);
		Utility.printTreeLinkNodeNext(treeLink);
		
		// Flatten Binary Tree to Linked List
		System.out.println("Flatten Binary Tree to Linked List");
		TreeNode root01 = buildTreeNode01();
		sol.flatten(root01);
		Utility.printTreeNodeRight(root01);
		
		// Minimum Depth of Binary Tree
		System.out.println("Minimum Depth of Binary Tree");
		TreeNode root02 = buildTreeNode02();
		System.out.println(sol.minDepth(root02));
		
		// Maximum Depth of Binary Tree
		System.out.println("Maximum Depth of Binary Tree");
		TreeNode root03 = buildTreeNode02();
		System.out.println(sol.maxDepth(root03));
		
		// Construct Binary Tree from Preorder and Inorder Traversal
		System.out.println("Construct Binary Tree from Preorder and Inorder Traversal");
		int[] preorder = new int[]{1, 2, 4, 5, 3, 6, 7};
		int[] inorder  = new int[]{4, 2, 5, 1, 6, 3, 7};
		TreeNode root04 = sol.buildTreeI(preorder, inorder);
		Utility.printTreeNode(root04);
		
		// Construct Binary Tree from Inorder and Postorder Traversal 
		System.out.println("Construct Binary Tree from Inorder and Postorder Traversal");
		int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};
		inorder  = new int[]{4, 2, 5, 1, 6, 3, 7};
		TreeNode root05 = sol.buildTree(inorder, postorder);
		Utility.printTreeNode(root05);
		
		// Binary Tree Level Order Traversal
		System.out.println("Binary Tree Level Order Traversal");
		TreeNode root06 = buildTreeNode06();
		List<List<Integer>> rlist06 = sol.levelOrder(root06);
		Utility.printListList(rlist06);
		
		// Binary Tree Zigzag Level Order Traversal
		System.out.println("Binary Tree Zigzag Level Order Traversal");
		TreeNode root07 = buildTreeNode07();
		List<List<Integer>> rlist07 = sol.zigzagLevelOrder(root07);
		Utility.printListList(rlist07);
		
		// Symmetric Tree
		System.out.println("Symmetric Tree");
		root = Utility.buildTreeNode(new String[] {"4", "-57", "-57", "#", "67", "67", "#", "#", "-97", "-97"});
		System.out.println(sol.isSymmetric(root));
		
		// Same Tree
		System.out.println("Same Tree");
		String[] treeArray = new String[] {"4", "-57", "-57", "#", "67", "67", "#", "#", "-97", "-97"};
		TreeNode p = Utility.buildTreeNode(treeArray);
		TreeNode q = Utility.buildTreeNode(treeArray);
		System.out.println(sol.isSameTree(p, q));
		
		// Binary Tree Inorder Traversal
		System.out.println("Binary Tree Inorder Traversal");
		//root = Utility.buildTreeNode(new String[] {"3", "2", "#", "#", "4", "1"});
		root = Utility.buildTreeNode(new String[] {"1", "#", "2", "3"});
		//Utility.printTreeNode(root);
		List<Integer> ilist = sol.inorderTraversal(root);
		Utility.printList(ilist);
		
		//Reverse Linked List II
		System.out.println("Reverse Linked List II");
		int[] values = new int[] {1, 2};
		ListNode lhead = Utility.buildListNode(values);
		ListNode result = sol.reverseBetween(lhead, 1, 2);
		Utility.printListNode(result);
		
		// Gray Code
		System.out.println("Gray Code");
		Utility.printList(sol.grayCode(3));
		
		// Merge Sorted Array
		System.out.println("Merge Sorted Array");
		int[] A = new int[]{2,6,9,10,12,0,0,0,0};
		int[] B = new int[]{1,4,5,11};
		sol.merge(A, 5, B, 4);
		Utility.printArray(A);
		
		// Remove Duplicates from Sorted List 
		System.out.println("emove Duplicates from Sorted List");
		head = Utility.buildListNode(new int[]{1,1,2,2,2,2,3,7,7});
		list = sol.deleteDuplicatesI(head);
		Utility.printListNode(list);
		
		// Remove Duplicates from Sorted List II
		System.out.println("Remove Duplicates from Sorted List II");
		//head = Utility.buildListNode(new int[]{0,1,1,1,2,2,2,3,4,4,4,4,5});
		head = Utility.buildListNode(new int[]{});
		list = sol.deleteDuplicates(head);
		Utility.printListNode(list);
		
		// Search in Rotated Sorted Array
		System.out.println("Search in Rotated Sorted Array");
		int[] input = new int[]{4,5,6,7,0,1,2,3};
		System.out.println(sol.searchI(input, 0));
		
		// Search in Rotated Sorted Array II
		System.out.println("Search in Rotated Sorted Array");
		input = new int[]{4,5,6,7,7,0,0,1,2,3};
		System.out.println(sol.search(input, 9));
		
		// Remove Duplicates from Sorted Array
		System.out.println("Remove Duplicates from Sorted Array");
		//input = new int[]{1,2,3,3,3,4,5,6};
		//input = new int[]{1};
		//input = new int[]{};
		//input = new int[]{1,2,3,3,3,4,5,6,6,6,6};
		//input = new int[]{1,1,2,2};
		input = new int[]{1,2,2,2,2,3,3};
		int length = sol.removeDuplicatesI(input);
		System.out.println(length);
		Utility.printArray(input, length);
		
		// Remove Duplicates from Sorted Array II
		System.out.println("Remove Duplicates from Sorted Array II");
		//input = new int[]{1,2,2,2,3,4,4,4,4,4,4};
		//input = new int[]{};
		input = new int[]{1};
		length = sol.removeDuplicates(input);
		System.out.println(length);
		Utility.printArray(input, length);
		
		// Combinations
		System.out.println("Combinations");
		List<List<Integer>> reslist = sol.combine(10, 7);
		Utility.printListList(reslist);
		
		// Sort Colors
		System.out.println("Sort Colors");
		int[] iArray = new int[]{2,1,1,1,0,0,2,0,0};
		sol.sortColors(iArray);
		Utility.printArray(iArray);
		
		// Search a 2D Matrix
		System.out.println("Search a 2D Matrix");
		//int[][] iiArray = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		//int[][] iiArray = new int[][]{{1,3,5,7}};
		//int[][] iiArray = new int[][]{{1},{3},{5},{7}};
		int[][] iiArray = null;
		System.out.println(sol.searchMatrix(iiArray, 1));
		
		// Set Matrix Zeroes
		System.out.println("Set Matrix Zeroes");
		//int[][] matrix = {{2,3,4,2},{3,2,3,2},{1,3,1,3},{3,4,5,0}};
		//int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
		int[][] matrix = {{1,1,1,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
		Utility.printMatirx(matrix);
		sol.setZeroes(matrix);
		Utility.printMatirx(matrix);
		
		// Climbing Stairs
		System.out.println("Climbing Stairs");
		int n = 44;
		//System.out.println(sol.climbStairsSlow(n));
		System.out.println(sol.climbStairs(n));
		n = 45;
		System.out.println(sol.climbStairs(n));
		n = 46;
		System.out.println(sol.climbStairs(n));
		
		// Plus One
		System.out.println("Plus One");
		int[] digits = new int[]{9,9,9,9,9};
		Utility.printArray(sol.plusOne(digits));
		
		// Merge Two Sorted Lists
		System.out.println("Merge Two Sorted Lists");
		ListNode l1 = Utility.buildListNode(new int[]{3,5,7,8,12,15,60});
		//ListNode l2 = Utility.buildListNode(new int[]{1,4,9,12,61});
		ListNode l2 = null;
		ListNode l = sol.mergeTwoLists(l1, l2);
		Utility.printListNode(l);
		
		// Single Number
		System.out.println("Single Number");
		iArray = new int[]{4,3,1,2,3,1,2};
		System.out.println(sol.singleNumberI(iArray));
		
		// Single Number II
		System.out.println("Single Number II");
		iArray = new int[]{2,3,4,4,3,1,2,3,2,4};
		System.out.println(sol.singleNumber(iArray));
		
		// Minimum Path Sum
		System.out.println("Minimum Path Sum");
		//iiArray = new int[][]{{2,5,7,8},{5,5,6,1},{7,1,7,1},{8,1,1,1}};
		iiArray = new int[][]{{2,2,2,2},{2,1,1,2},{2,2,2,2},{2,2,2,2}};
		System.out.println(sol.minPathSum(iiArray));
		
		// Unique Paths
		System.out.println("Unique Paths");
		System.out.println(sol.uniquePaths(23, 12));
		
		// Spiral Matrix II
		System.out.println("Spiral Matrix II");
		iiArray = sol.generateMatrix(6);
		Utility.printMatirx(iiArray);
		
		// Swap Nodes in Pairs
		System.out.println("Swap Nodes in Pairs");
		ListNode nodelist = Utility.buildListNode(new int[]{1,2,3,4,5,6,7});
		Utility.printListNode(nodelist);
		Utility.printListNode(sol.swapPairs(nodelist));
		
		// Permutations
		System.out.println("Permutations");
		llist = sol.permute(new int[]{1,2,3,4});
		Utility.printListList(llist);
		
		// Permutations II
		System.out.println("Permutations II");
		
		
		// Maximum Subarray
		//System.out.println("Maximum Subarray");
		//iArray = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		//System.out.println(sol.maxSubArray(iArray));
		
		
	}
	
	public static TreeNode buildTreeNode08() {
		
		/*
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		*/
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(-57);
		root.left.right = new TreeNode(67);
		root.left.right.right = new TreeNode(-97);
		
		root.right = new TreeNode(-57);
		root.right.left = new TreeNode(67);
		root.right.left.left = new TreeNode(-97);
		
		return root;
	}
	
	public static TreeNode buildTreeNode07() {
		
		/*
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		*/
		
		/*
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.left.right = new TreeNode(5);*/
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.left.left = new TreeNode(8);
		root.left.left.right = new TreeNode(9);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(11);
		
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		root.right.left.left = new TreeNode(12);
		root.right.left.right = new TreeNode(13);
		root.right.right.left = new TreeNode(14);
		root.right.right.right = new TreeNode(15);
		
		return root;
	}

	public static TreeNode buildTreeNode06() {
		
		/*
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);*/
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.left.right = new TreeNode(5);
		
		return root;
	}
	
	public static TreeNode buildTreeNode02() {
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(3);
		root.left.right.left = new TreeNode(0);
		
		root.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		
		return root;
	}
	
	public static TreeNode buildTreeNode01() {
		
		TreeNode root = new TreeNode(1);
		//root.left = new TreeNode(2);
		//root.left.left = new TreeNode(3);
		//root.left.right = new TreeNode(4);
		
		//root.right = new TreeNode(5);
		//root.right.right = new TreeNode(6);
		
		return root;
	}
	
	public static TreeLinkNode buildTreeLinkI() {
		
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.left.left = new TreeLinkNode(4);
		root.left.left.left = new TreeLinkNode(8);
		root.left.left.right = new TreeLinkNode(9);
		root.left.right = new TreeLinkNode(5);
		root.left.right.left = new TreeLinkNode(10);
		root.left.right.right = new TreeLinkNode(11);
		
		root.right = new TreeLinkNode(3);
		root.right.left = new TreeLinkNode(6);
		root.right.left.left = new TreeLinkNode(12);
		root.right.left.right = new TreeLinkNode(13);
		root.right.right = new TreeLinkNode(7);
		root.right.right.left = new TreeLinkNode(14);
		root.right.right.right = new TreeLinkNode(15);
		
		return root;
	}
	
	public static TreeLinkNode buildTreeLinkII() {
		
		TreeLinkNode root = new TreeLinkNode(1);
		/*
		root.left = new TreeLinkNode(2);
		root.left.left = new TreeLinkNode(4);
		root.left.left.left = new TreeLinkNode(8);
		//root.left.left.right = new TreeLinkNode(9);
		root.left.right = new TreeLinkNode(5);
		root.left.right.left = new TreeLinkNode(10);
		root.left.right.right = new TreeLinkNode(11);*/
		
		root.right = new TreeLinkNode(3);
		root.right.left = new TreeLinkNode(6);
		//root.right.left.left = new TreeLinkNode(12);
		root.right.left.right = new TreeLinkNode(13);
		root.right.right = new TreeLinkNode(7);
		root.right.right.left = new TreeLinkNode(14);
		root.right.right.right = new TreeLinkNode(15);
		
		return root;
	}
	
	/*        5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
    */
	
	public static TreeNode buildTree01() {
		
		TreeNode root = new TreeNode(5);
		
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		
		return root;
	}
	
	/*        5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
	 */
	public static TreeNode buildTree02() {
		
		TreeNode root = new TreeNode(5);
		/*
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		*/
		return root;
	}
	
	/*
	 *       1
	 *     /  \
	 *    2    3
	 *   / \  / \
	 *  4  5  6  7
	 *      \   /
	 *       8 9
	 */
	public static TreeNode buildTree() {
		
		TreeNode root = new TreeNode(1);
		
		TreeNode left = new TreeNode(2);
		left.left = new TreeNode(4);
		left.right = new TreeNode(5);
		left.right.right = new TreeNode(8);
		
		TreeNode right = new TreeNode(3);
		right.left = new TreeNode(6);
		right.right = new TreeNode(7);
		right.right.left = new TreeNode(9);
		
		root.left = left;
		root.right = right;
		
		return root;
	}
	
	public static Set<String> buildSet(String[] array) {
		
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < array.length; ++i) {
			
			set.add(array[i]);
		}
		return set;
	}
	
	public static ListNode buildListNode(int[] array) {
		
		ListNode head = new ListNode(array[0]);
		
		ListNode p = head;
		for (int i = 1; i < array.length; ++i) {
			
			p.next = new ListNode(array[i]);
			p = p.next;
		}
		
		return head;
	}
	
	public static void runInterview() {
		
		Interview iv = new Interview();
		
		int numOfCards = 54;
		int[] cards = new int[numOfCards];
		for (int i = 0; i < numOfCards; ++i) {
			
			cards[i] = i + 1;
		}
		iv.shuffle1(cards);
		for (int i = 0; i < cards.length; ++i) {
			
			System.out.print(cards[i] + " ");
		}
		System.out.println("");
		
		cards = new int[numOfCards];
		for (int i = 0; i < numOfCards; ++i) {
			
			cards[i] = i + 1;
		}
		iv.shuffle2(cards);
		for (int i = 0; i < cards.length; ++i) {
			
			System.out.print(cards[i] + " ");
		}
		System.out.println("");
		
		int result = iv.getSUM(5, 3);
		System.out.println(result);
		
		String input = "[6,27] [5,7] [21,34] [13,25]";
		//String input = "[24,35] [3,20] [-2,9] [37,40]";
		String output = iv.mergeIntervals(input);
		System.out.println(output);
		
		
		String text = "anavebaron";
		String dic = "{a,an,ave,bar,baron,nave,on}";
		int res = iv.matchDictionary(text,dic);
		System.out.println(res);
		
		String input1 = "{1,7,12,47,161,0,9}";
		String output1 = iv.getMaxNumber(input1);
		System.out.println(output1);
		
		
		String input002 = "a,b,c";
		String[] output003 = iv.getFullCombination(input002);
		System.out.println("number: " + output003.length);
		for (int i = 0; i < output003.length; ++i) {
			
			System.out.println(output003[i]);
		}
		
		int[][] array1 = {
			{ 1, 2, 3, 4},
			{12,13,14, 5},
			{11,16,15, 6},
			{10, 9, 8, 7}
		};
		
		int[][] array2 = {
			{ 1, 2, 3, 4, 5, 6, 7},
			{24,25,26,27,28,29, 8},
			{23,40,41,42,43,30, 9},
			{22,39,48,49,44,31,10},
			{21,38,47,46,45,32,11},
			{20,37,36,35,34,33,12},
			{19,18,17,16,15,14,13}
		};
		
		int[][] array3 = {
				
			{ 1, 2, 3, 4, 5, 6, 7},
			{20,21,22,23,24,25, 8},
			{19,32,33,34,35,26, 9},
			{18,31,30,29,28,27,10},
			{17,16,15,14,13,12,11}
		};
		
		//iv.printMatrix(array3, 7, 5);
		
	}
	
	

}
