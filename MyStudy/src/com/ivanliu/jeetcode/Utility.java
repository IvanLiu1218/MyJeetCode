package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.ivanliu.jeetcode.Solution.ListNode;
import com.ivanliu.jeetcode.Solution.TreeLinkNode;
import com.ivanliu.jeetcode.Solution.TreeNode;

public class Utility {

	// TreeNode
	public static TreeNode buildTreeNode(String[] array) {
		
		if (array == null || array.length == 0) {
			
			return null;
		}
		
		int index = 0;
		TreeNode root = new TreeNode(Integer.parseInt(array[index++]));
		Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		while (queue.size() != 0 && index < array.length) {
			
			TreeNode node = queue.remove();
			
			String value = array[index++];
			if (!value.equals("#")) {
				
				node.left = new TreeNode(Integer.parseInt(value));
				queue.add(node.left);
			}
			if (index < array.length) {
				
				value = array[index++];
				if (!value.equals("#")) {
					
					node.right = new TreeNode(Integer.parseInt(value));
					queue.add(node.right);
				}
			}
		}
		
		return root;
	}
	
	public static void printTreeNode(TreeNode root) {
		
		if (root == null) return;
		Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		queue.add(new TreeNode(-1));
		while (queue.size() != 0) {
			
			TreeNode node = queue.remove();
			if (node.val != -1) {
				
				System.out.print(node.val + " ");
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
			else {
				
				System.out.println();
				if (queue.size() != 0) queue.add(new TreeNode(-1));
			}
		}
	}
	
	public static void printTreeNodeRight(TreeNode root) {
		
		TreeNode node = root;
		while (node != null) {
			
			System.out.print(node.val + " ");
			node = node.right;
		}
		System.out.println();
	}
	
	// ListNode
	public static ListNode buildListNode(int[] values) {
		
		if (values == null || values.length == 0) return null;
		
		ListNode thead = new ListNode(-1);
		ListNode p = thead;
		for (int i = 0; i < values.length; ++i) {
			
			p.next = new ListNode(values[i]);
			p = p.next;
		}
		
		return thead.next;
	}
	
	public static void printListNode(ListNode head) {
		
		ListNode node = head;
		while (node != null) {
			
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	// Common
	public static <T> void printList(List<T> list) {
		
		for (int i = 0; i < list.size(); ++i) {
			
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	public static <T> void printListList(List<List<T>> llist) {
		
		for (int i = 0; i < llist.size(); ++i) {
			
			List<T> list = llist.get(i);
			for (int j = 0; j < list.size(); ++j) {
				
				System.out.print(list.get(j) + " ");
			}
			System.out.println();
		}
	}
	
	public static void printTreeLinkNodeNext(TreeLinkNode root) {
		
		TreeLinkNode node = root;
		while (node != null) {
			
			TreeLinkNode p = node;
			while (p != null) {
				
				System.out.print(p.val + " ");
				p = p.next;
			}
			System.out.println();
			
			if (node.left != null) node = node.left;
			else node = node.right;
		}
	}
	
	public static void printArray(int[] array, int length) {
		
		if (array == null) return;
		for (int i = 0; i < length; ++i) {
			
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void printArray(int[] array) {
		
		if (array == null) return;
		printArray(array, array.length);
	}
	
	public static void printMatirx(int[][] matrix) {
		
		for (int i = 0; i < matrix.length; ++i) {
			
			for (int j = 0; j < matrix[i].length; ++j) {
				
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
