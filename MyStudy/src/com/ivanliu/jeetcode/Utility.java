package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.ivanliu.jeetcode.Solution.TreeNode;

public class Utility {

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
}
