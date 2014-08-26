package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	/*
	 * #0001 Reverse Words in a String
	 * Given an input string, reverse the string word by word.
	 * For example,
	 * Given s = "the sky is blue",
	 * return "blue is sky the".
	 * 
	 * Q: What constitutes a word?
	 * A: A sequence of non-space characters constitutes a word.
	 * Q: Could the input string contain leading or trailing spaces?
	 * A: Yes. However, your reversed string should not contain leading or trailing spaces.
	 * Q: How about multiple spaces between two words?
	 * A: Reduce them to a single space in the reversed string.
	 * 
	 * ACCEPTED
	 */
	public String reverseWords(String s) {
        
		char[] charArray = s.toCharArray();
        int i = 0;
        int j = s.length();
        reverse(charArray, i, j);
        
        i = getIndexStart(charArray, ' ', 0);
        j = getIndexEnd(charArray, ' ', i);
        String result = "";
        
        while (i < j && j <= charArray.length) {
        	//System.out.println("i=" + i + ",j=" + j);
	        result += reverse(charArray, i, j) + " ";
	        i = getIndexStart(charArray, ' ', j);
	        j = getIndexEnd(charArray, ' ', i);
        }
        
        return result.trim();
    }
	
	private int getIndexStart(char[] arrays, char target, int end) {
		
		int i = end;
		while (i < arrays.length && arrays[i] == target) {
			
			i++;
		}
		
		return i;
	}
	
	private int getIndexEnd(char[] arrays, char target, int start) {
		
		for (int i = start; i < arrays.length; ++i) {
			
			if (arrays[i] == target)
				return i;
		}
		
		return arrays.length;
	}
	
	private String reverse(char[] array, int start, int end) {
		
		int i = start;
		int j = end - 1;
		while (i < j) {
				
			char c = array[i];
			array[i] = array[j];
			array[j] = c;
			i++;
			j--;
		}
		
		return new String(array).substring(start, end);
	}
	
	/*
	 * #0002 Evaluate Reverse Polish Notation
	 * 
	 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
	 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
	 * Some examples:
	 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 * 
	 * ACCEPTED
	 */
	public int evalRPN(String[] tokens) {
        
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		
		for (String v : tokens) {
			
			if (v.equals("+")) {
				
				Integer a = stack.pop();
				Integer b = stack.pop();
				stack.push(b + a);
			}
			else if (v.equals("-")) {
				
				Integer a = stack.pop();
				Integer b = stack.pop();
				stack.push(b - a);
			}
			else if (v.equals("*")) {
				
				Integer a = stack.pop();
				Integer b = stack.pop();
				stack.push(b * a);
			}
			else if (v.equals("/")) {
				
				Integer a = stack.pop();
				Integer b = stack.pop();
				stack.push(b / a);
			}
			else {
				
				stack.push(Integer.parseInt(v));
			}
		}
		
		return stack.pop();
    }
	
	/*
	 * #0003 Binary Tree Postorder Traversal 
	 * 
	 * ACCEPTED
	 * 
	 */
	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public List<Integer> postorderTraversal(TreeNode root) {
        
		List<Integer> arrayList = new ArrayList<Integer>();
		postorder(root, arrayList);
		
		return arrayList;
    }
	
	private void postorder(TreeNode node, List<Integer> list) {
		
		if (node == null) return;
		
		if (node.left != null) {
			
			postorder(node.left, list);
		}
		if (node.right != null) {
			
			postorder(node.right, list);
		}
		
		list.add(node.val);
	}
	
	/*
	 * #0004 Binary Tree Preorder Traversal
	 * 
	 * ACCEPTED
	 * 
	 */
	
	public List<Integer> preorderTraversal(TreeNode root) {
        
		List<Integer> arrayList = new ArrayList<Integer>();
		preorder(root, arrayList);
		
		return arrayList;
    }
	
	private void preorder(TreeNode node, List<Integer> list) {
		
		if (node == null) return;
		list.add(node.val);
		
		if (node.left != null) {
			
			preorder(node.left, list);
		}
		if (node.right != null) {
			
			preorder(node.right, list);
		}
	}
	
	/*
	 * #0005 
	 * 
	 */
	static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) {
			 val = x;
			 next = null;
		}
	}
	
	public void reorderList(ListNode head) {
        
		
    }
	
	private ListNode reverseList(ListNode node) {
		
		if (node.next == null) return node;
		
		ListNode next = node.next;
		ListNode newhead = reverseList(next);
		next.next = node;
		
		return newhead;
		
	}
}
