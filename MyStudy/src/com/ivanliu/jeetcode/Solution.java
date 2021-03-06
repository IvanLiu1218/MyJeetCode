package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	// Data Structure
	public static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public static class ListNode {
		
		 int val;
		 ListNode next;
		 ListNode(int x) {
			 val = x;
			 next = null;
		}
	}
	
	/*
	 * Reverse Words in a String
	 * 
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
	 * Evaluate Reverse Polish Notation
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
	 * Binary Tree Postorder Traversal 
	 * 
	 * ACCEPTED
	 * 
	 */
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
	 * Binary Tree Preorder Traversal
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
	 * Sort List 
	 * 
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 * 
	 * ACCEPTED
	 * 
	 */
	public ListNode sortList(ListNode head) {
        
		if (head == null) return null;
		if (head.next == null) return head;
		ListNode mid = getMiddleNode(head);
		
		ListNode nhead1 = head;
		ListNode nhead2 = mid.next;
		mid.next = null;
		
		ListNode h = null;
		if (nhead1 != null && nhead2 != null) {
			
			ListNode h1 = sortList(nhead1);
			//printListNode(h1);
			ListNode h2 = sortList(nhead2);
			//printListNode(h2);
			h = mergeListNode(h1, h2);
			//printListNode(h);
		}
		
		return h;
    }
	
	private ListNode getMiddleNode(ListNode head) {
		
		if (head == null) return null;
		if (head.next == null) return head;
		
		ListNode p1 = head;
		ListNode p2 = p1.next;
		
		while (p2 != null && p2.next != null) {
			
			p1 = p1.next;
			p2 = p2.next.next;
		}
		
		return p1;
	}
	
	private ListNode mergeListNode(ListNode a, ListNode b) {
		
		ListNode h = new ListNode(0);
		ListNode p1 = a;
		ListNode p2 = b;
		
		ListNode head = h;
		
		while (p1!= null && p2 != null) {
			
			if (p1.val < p2.val) {
				
				h.next = p1;
				p1 = p1.next;
			}
			else {
				
				h.next = p2;
				p2 = p2.next;
			}
			h = h.next;
		}
		if (p1 != null) {
			
			h.next = p1;
		}
		if (p2 != null) {
			
			h.next = p2;
		}
		
		return head.next;
	}
	
	/*
	 * Insertion Sort List 
	 * 
	 * Sort a linked list using insertion sort.
	 * 
	 * ACCEPTED
	 */
	public ListNode insertionSortList(ListNode head) {
        
		if (head == null) return null;
		if (head.next == null) return head;
		
		ListNode nhead = new ListNode(Integer.MIN_VALUE);
		nhead.next = head;
		
		ListNode p = head.next;
		
		while (p != null) {
			
			p = insertNode(p, nhead);
		}
		
		return nhead.next;
    }
	
	private ListNode insertNode(ListNode node, ListNode from) {
		
		ListNode prev = getPrevNode(from, node);
		ListNode next = node.next;
		ListNode p1 = from; //start
		ListNode p2 = node; //end
		
		while (p1 != null && p1 != p2) {
			
			if (p1.val <= node.val && node.val < p1.next.val) {
				
				node.next = p1.next;
				p1.next = node;
				prev.next = next;
				return next;
			}
			p1 = p1.next;
		}
		
		return next;
	}
	
	private ListNode getPrevNode(ListNode head, ListNode node) {
		
		ListNode p1 = head;
		ListNode p2 = head.next;
		while (p2 != node) {
			
			p1 = p2;
			p2 = p2.next;
		}
		
		return p1;
	}
	
	/*
	 * Linked List Cycle
	 * 
	 * Given a linked list, determine if it has a cycle in it.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * ACCEPTED
	 */
	public boolean hasCycle(ListNode head) {
        
		if (head == null) return false;
		if (head.next == null) return false;
		
		ListNode p1 = head.next;
		ListNode p2 = p1.next.next;
		
		while (p2 != null ) {
			
			if (p1 == p2)
				return true;
			
			p1 = p1.next;
			p2 = p2.next;
			if (p2 == null) break;
			p2 = p2.next;
		}
		
		return false;
		
    }
	
	/*
	 * Linked List Cycle II
	 * 
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * ACCEPTED
	 * 
	 */
	public ListNode detectCycle(ListNode head) {
        
		if (head == null) return null;
		if (head.next == null) return null;
		
		ListNode p1 = head.next;
		ListNode p2 = head.next.next;
		
		while (p2 != null ) {
			
			if (p1 == p2) {
				
				return getJoint(head, p1);
			}
			
			p1 = p1.next;
			p2 = p2.next;
			if (p2 == null) break;
			p2 = p2.next;
		}
		
		return null;
    }
	
	private ListNode getJoint(ListNode head, ListNode met) {
		
		ListNode p1 = head;
		ListNode p2 = met;
		
		while (p1 != p2) {
			
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p1;
	}
	
	/*
	 * Word Break 
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a space-separated 
	 * sequence of one or more dictionary words.
	 * 
	 * For example, given
	 * s = "leetcode",
	 * dict = ["leet", "code"].
	 * Return true because "leetcode" can be segmented as "leet code".
	 * 
	 * Last executed input:	
	 * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", 
	 * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
	 */
	public boolean isBreak = false;
	
	public boolean wordBreak1(String s, Set<String> dict) {
        
		isBreak = false;
		
		String[] dicts = dict.toArray(new String[]{});
		for (int i = 0; i < dicts.length; ++i) {
			
			String prefix = dicts[i];
			if (s.startsWith(prefix)) {
				
				wordBreak1(s.substring(prefix.length()), dicts);
			}
		}
		
		return isBreak;
    }
	
	private void wordBreak1(String s, String[] dicts) {
		
		if (isBreak) return;
		
		if (s.length() == 0) {
			
			isBreak = true;
			return;
		}
		
		for (int i = 0; i < dicts.length; ++i) {
			
			String prefix = dicts[i];
			if (s.startsWith(prefix)) {
				
				wordBreak1(s.substring(prefix.length()), dicts);
			}
		}
	}
	
	/*
	 * Word Break II
	 * 
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
	 * Return all such possible sentences.
	 * For example, given
	 * s = "catsanddog",
	 * dict = ["cat", "cats", "and", "sand", "dog"].
	 * A solution is ["cats and dog", "cat sand dog"].
	 * 
	 * Last executed input:	
	 * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", 
	 * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
	 * 
	 */
	private ArrayList<String> resArray = new ArrayList<String>();
	
	public List<String> wordBreak(String s, Set<String> dict) {
        
		String[] dicts = dict.toArray(new String[]{});
		
		for (int i = 0; i < dicts.length; ++i) {
			
			String res = "";
			String prefix = dicts[i];
			if (s.startsWith(prefix)) {
				
				res += prefix + ' ';
				wordBreak(s.substring(prefix.length()), dicts, res);
			}
		}
		return resArray;
    }
	
	private void wordBreak(String s, String[] dicts, String result) {
		
		if (s.length() == 0) {
			
			resArray.add(result.trim());
			return;
		}
		
		for (int i = 0; i < dicts.length; ++i) {
			
			String prefix = dicts[i];
			if (s.startsWith(prefix)) {
				
				result += prefix + ' ';
				wordBreak(s.substring(prefix.length()), dicts, result);
			}
		}
	}
	
	/*
	 * Reorder List
	 * 
	 * Given a singly linked list L: L0鈫扡1鈫掆�︹啋Ln-1鈫扡n,
	 * reorder it to: L0鈫扡n鈫扡1鈫扡n-1鈫扡2鈫扡n-2鈫掆��
	 * You must do this in-place without altering the nodes' values.
	 * For example,
	 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * 
	 * ACCEPTED
	 */
	public void reorderList(ListNode head) {
        
		ArrayList<ListNode> list = new ArrayList<ListNode>();
		ListNode p = head;
		while (p != null) {
			
			list.add(p);
			p = p.next;
		}
		
		ListNode nhead = new ListNode(0);
		ListNode tp = nhead;
		int len = list.size();
		int mid = len / 2;
		int i = 0;
		while (i < mid){
			
			tp.next = list.get(i);
			tp = tp.next;
			tp.next = list.get(len - 1 - i);
			tp = tp.next;
			i++;
		}
		
		if (len % 2 == 1) {
			
			tp.next = list.get(i);
			tp = tp.next;
		}
		tp.next = null;
		
		head = nhead.next;
    }
	
	/*
	 * Gas Station
	 * 
	 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
	 * You begin the journey with an empty tank at one of the gas stations.
	 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
	 * 
	 * Note:
	 * The solution is guaranteed to be unique.
	 * 
	 * ACCEPTED
	 */
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
        
		int size = gas.length;
		if (size == 1) return gas[0] >= cost[0] ? 0 : -1;
		
		for (int i = 0; i < size; ++i) {
			
			int hasGas = gas[i] - cost[i];
			int j = i + 1;
			//if (j >= size) j = j % size;
			
			while (hasGas >= 0 && j != i) {
				
				if (j >= size) {
					
					j = j % size;
				}
				hasGas += gas[j] - cost[j];
				if (hasGas < 0) break;
				j++;
			}
			
			if (j == i) return i;
		}
		
		
		return -1;
		
    }
	
	/*
	 * Palindrome Partitioning
	 * 
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * 
	 * For example, given s = "aab",
	 * Return
	 * [
	 *   ["aa","b"],
	 *   ["a","a","b"]
	 * ]
	 * 
	 * Time Limit Exceeded
	 * "eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj"
	 */
	
	private List<List<String>> resList = null;
	
	public List<List<String>> partition(String s) {
        
		if (s == null) return null;
		
		resList = new ArrayList<List<String>>();
		
		int strlen = s.length();		
		for (int i = 0; i < strlen; ++i) {
			
			List<String> list = new ArrayList<String>();
			recurPartition(s, i, list);
		}
		
		return resList;
    }
	
	private void recurPartition(String s, int i, List<String> list) {
		

		if (s.length() == 0) {
			
			resList.add(new ArrayList<String>(list));
			return;
		}
		if (s.length() == 1) {
			
			list.add(s);
			resList.add(new ArrayList<String>(list));
			list.remove(list.size() - 1);
			return;
		}
		
		int j1 = i;
		int j2 = i;
		while (j1 >= 0 && j2 < s.length()) {
			
			if (s.charAt(j1) == s.charAt(j2) && j1 == 0) {
				
				list.add(s.substring(j1, j2 + 1));
				String prefix = s.substring(j2 + 1);
				for (int k = 0; k < prefix.length(); ++k) {
					
					recurPartition(s.substring(j2 + 1), k, list);
				}
				list.remove(list.size() - 1);
			}
			j1--;
			j2++;
		}
		
		j1 = i;
		j2 = i + 1;
		while (j1 >= 0 && j2 < s.length()) {
			
			if (s.charAt(j1) == s.charAt(j2) && j1 == 0) {
				
				list.add(s.substring(j1, j2 + 1));
				recurPartition(s.substring(j2 + 1), 0, list);
				list.remove(list.size() - 1);
			}
			j1--;
			j2++;
		}
	}
	
	/*
	 * Sum Root to Leaf Numbers
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
	 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
	 * Find the total sum of all root-to-leaf numbers.
	 * For example,
	 *    1
	 *   / \
	 *  2   3
	 *  The root-to-leaf path 1->2 represents the number 12.
	 *  The root-to-leaf path 1->3 represents the number 13.
	 *  Return the sum = 12 + 13 = 25.
	 *  
	 *  ACCEPTED
	 */
	public int sumNumbers(TreeNode root) {
        
		int sum = 0;
		if (root == null) return 0;
		
		List<String> list = new ArrayList<String>();
		String num = "";
		searchDLS(root, num, list);
		
		for (int i = 0; i < list.size(); ++i) {
			
			int number = Integer.parseInt(list.get(i));
			//System.out.println(number);
			sum += number;
		}
		
		return sum;
    }
	
	private void searchDLS(TreeNode node, String num, List<String> resList) {
		
		if (node == null) {
			
			return;
		}
		
		num += String.format("%d", node.val);
		searchDLS(node.left, num, resList);
		searchDLS(node.right, num, resList);
		
		if (node.left == null && node.right == null) {
			
			resList.add(num);
			return;
		}
	}
	
	/*
	 * Longest Consecutive Sequence
	 * 
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * For example,
	 * Given [100, 4, 200, 1, 3, 2],
	 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * Your algorithm should run in O(n) complexity.
	 * 
	 * Runtime Error Message:	Line 16: java.lang.ArrayIndexOutOfBoundsException: -1
	 * Last executed input:	[0,-1]
	 * 
	 * Runtime Error Message:	Line 15: java.lang.NegativeArraySizeException
	 * Last executed input:	[2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645]

	 */
	public int longestConsecutive(int[] num) {
		
		if (num == null || num.length == 0) return 0;
		if (num.length == 1) return 1;
		
		long max = Integer.MIN_VALUE;
		long min = Integer.MAX_VALUE;
		for (int i = 0; i < num.length; ++i) {
			
			if (num[i] > max) max = num[i];
			if (num[i] < min) min = num[i];
		}
		
		long size = (max - min + 1);
		size = size/ 63 + 1;
		//int size = (max - min + 1) / 63 + 1;
		//int[] array = new int[max - min + 1];  // <-- java.lang.NegativeArraySizeException
		long[] array = new long[(int)size];
		for (int i = 0; i < num.length; ++i) {
			
			//array[num[i] - min] = 1;
			this.putNum(array, num[i]);
		}
		
		int maxLength = 0;
		int len = 0;
		int i = 0;
		for (; i < array.length; ++i) {
			
			for (int j = 0; j < 32; ++j) {
				
				int index = i * 31 + j;
				boolean is = getNum(array, index);
				
				if (is == true) {
					
					len++;
				}
				else {
					
					if (len > maxLength)
						maxLength = len;
					
					len = 0;
				}
			}
		}
		if (len > maxLength) maxLength = len;
		
		return maxLength;
    }
	
	private void putNum(long[] array, long index) {
		
		int i = (int)index / 63;
		int j = (int)index % 63;
		
		long number = array[i];
		long mask = 1;
		while (j > 0) {
			
			mask = mask << 1;
			j--;
		}
		number = number | mask;
	}
	
	private boolean getNum(long[] array, long index) {
		
		int i = (int)index / 63;
		int j = (int)index % 63;
		
		long number = array[i];
		long mask = 1;
		while (j > 0) {
			
			mask = mask << 1;
			j--;
		}
		
		return (number & mask) == mask;
	}
	
	/*
	 * Best Time to Buy and Sell Stock
	 * 
	 * Say you have an array for which the i-th element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction 
	 * (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	 * 
	 * ACCEPTED
	 * 
	 */
	public int maxProfitI(int[] prices) {
        
		if (prices == null || prices.length == 0) return 0;
		
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; ++i) {
			
			int buy = prices[i];
			int sell = prices[i];
			for (int j = i + 1; j < prices.length; ++j) {
				
				sell = prices[j];
				if (sell - buy > maxProfit) maxProfit = sell - buy;
			}
			if (sell - buy > maxProfit) maxProfit = sell - buy;
			
		}
		
		return maxProfit;
    }
	
	/*
	 * Best Time to Buy and Sell Stock II
	 * 
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
	 * (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple 
	 * transactions at the same time (ie, you must sell the stock before you buy again).
	 * 
	 */
	
	private int maxProfitII1(int[] prices, int x, int y) {
        
		if (prices == null || prices.length == 0) return 0;
		
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; ++i) {
			 
			int buy = prices[i];
			int sell = prices[i];
			for (int j = i + 1; j < prices.length; ++j) {
				
				sell = prices[j];
				if (sell - buy > maxProfit) maxProfit = sell - buy;
			}
			if (sell - buy > maxProfit) maxProfit = sell - buy;
			
		}
		
		return maxProfit;
    }
	
	public int maxProfit(int[] prices) {
        
		if (prices == null || prices.length == 0) return 0;
		
		for (int i = 0; i < prices.length; ++i) {
			
			
		}
		
		return 0;
    }
	
	/*
	 * Triangle
	 * 
	 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
	 * For example, given the following triangle
	 * [
	 *      [2],
	 *     [3,4],
	 *    [6,5,7],
	 *   [4,1,8,3]
	 * ]
	 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	 * Note:
	 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	 *   
	 * Time Limited Exceed
	 * 
	 * ACCEPTED
	 */
	/* 瓒呮椂锛�
	private int minValue = 0;
	public int minimumTotal(List<List<Integer>> triangle) {
        
		minValue = Integer.MAX_VALUE;
		int min = 0;
		getValueFromTriangle(triangle, 0, 0, min);
		return minValue;
    }
	
	private void getValueFromTriangle(List<List<Integer>> triangle, int x, int y, int min) {
		
		if (x >= triangle.size()) {
			
			if (min < minValue) minValue = min;
			return;
		}
		List<Integer> tlist = triangle.get(x);
		if (y >= triangle.size()) {
			
			if (min < minValue)  minValue = min;
			return;
		}
		
		min += tlist.get(y);
		getValueFromTriangle(triangle, x + 1, y, min);
		getValueFromTriangle(triangle, x + 1, y + 1, min);
	}*/
	public int minimumTotal(List<List<Integer>> triangle) {
		
		if (triangle == null || triangle.size() == 0) {
			
			return 0;
		}

		int sizeX = triangle.size();
		for (int i = sizeX - 2; i >= 0; --i) {
			
			List<Integer> tlist1 = triangle.get(i);
			List<Integer> tlist2 = triangle.get(i + 1);
			for (int j = tlist2.size() - 2; j >= 0; --j) {
				
				int a = tlist2.get(j);
				int b = tlist2.get(j + 1);
				tlist1.set(j, tlist1.get(j) + (a < b ? a : b));
			}
		}
		
		return triangle.get(0).get(0);
	}
	
	/*
	 * Pascal's Triangle 
	 * 
	 * Given numRows, generate the first numRows of Pascal's triangle.
	 * For example, given numRows = 5,
	 * Return
	 * [
	 *      [1],
	 *     [1,1],
	 *    [1,2,1],
	 *   [1,3,3,1],
	 *  [1,4,6,4,1]
	 * ]
	 * 
	 * ACCEPTED
	 */
	public List<List<Integer>> generate(int numRows) {
		
		List<List<Integer>> llist = new ArrayList<List<Integer>>();
		
		int num = numRows;
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		while (num > 0) {
			
			llist.add(list);
			list = generateList(list);
			num--;
		}
		
		return llist;
    }
	
	private List<Integer> generateList(List<Integer> list) {
		
		List<Integer> resList = new ArrayList<Integer>();
		int prev = 0;
		for (int i = 0 ; i < list.size(); ++i) {
			
			int curr = list.get(i);
			resList.add(prev + curr);
			prev = curr;
		}
		resList.add(list.get(list.size() - 1));
		
		return resList;
	}
	
	public void printListIntegerII(List<List<Integer>> list) {
		
		for (int i = 0; i < list.size(); ++i) {
			
			List<Integer> ilist = list.get(i);
			for (int j = 0; j < ilist.size(); ++j) {
				
				System.out.print(ilist.get(j) + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Pascal's Triangle II 
	 * 
	 * Given an index k, return the kth row of the Pascal's triangle.
	 * For example, given k = 3,
	 * Return [1,3,3,1].
	 * Note:
	 * Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 * 
	 * ACCEPTED
	 */
	public List<Integer> getRow(int rowIndex) {
        
		List<Integer> list = new ArrayList<Integer>();
		
		int index = rowIndex + 1;
		
		while (index > 0) {
			
			int prev = 0;
			for (int i = 0; i < list.size(); ++i) {
				
				int curr = list.get(i);
				list.set(i,  prev + curr);
				prev = curr;
			}
			list.add(1);
			
			index--;
		}
		
		return list;
    }

	/*
	 * Path Sum
	 * 
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given sum.
	 * For example:
	 * Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
	 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	 * 
	 * ACCEPTED
	 */
	private boolean result = false;
	
	public boolean hasPathSum(TreeNode root, int sum) {
        
		if (root == null) return false;
		
		result = false;
		int value = 0;
		searchPath(root, value, sum);
		return result;
    }
	
	public void searchPath(TreeNode node, int value, int sum) {
		
		if (node.left == null && node.right == null) {
			
			value += node.val;
			if (value == sum) {
				
				result = true;
			}
		}
		
		value += node.val;
		if (node.left != null) {
			
			searchPath(node.left, value, sum);
		}
		if (node.right != null) {
			
			searchPath(node.right, value, sum);
		}
	}
	
	/*
	 * Path Sum II
	 * 
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 * For example:
	 * Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
     * 
     * return
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     * 
     * ACCEPTED
	 */
	private List<List<Integer>> rList = null;
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
		rList = new ArrayList<List<Integer>>();
		if (root == null) return rList;
		
		int value = 0;
		List<Integer> list = new ArrayList<Integer>();
		searchPathII(root, value, sum, list);
		
		return rList;
    }
	
	public void searchPathII(TreeNode node, int value, int sum, List<Integer> list) {
		
		if (node.left == null && node.right == null) {
			
			value += node.val;
			if (value == sum) {
				
				list.add(node.val);
				rList.add(new ArrayList<Integer>(list));
				list.remove(list.size() - 1);
			}
			return;
		}
		
		value += node.val;
		list.add(node.val);
		
		if (node.left != null) {
			
			searchPathII(node.left, value, sum, list);
		}
		if (node.right != null) {
			
			searchPathII(node.right, value, sum, list);
		}
		list.remove(list.size() - 1);
	}
	
	/* 
	 * Populating Next Right Pointers in Each Node
	 * 
	 * Given a binary tree
	    struct TreeLinkNode {
	      TreeLinkNode *left;
	      TreeLinkNode *right;
	      TreeLinkNode *next;
	    }
	 * Populate each next pointer to point to its next right node. 
	 * If there is no next right node, the next pointer should be set to NULL.
	 * Initially, all next pointers are set to NULL.
	 * Note:
	 * You may only use constant extra space.
	 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	 * For example,
	 * Given the following perfect binary tree,
	        1
	       /  \
	      2    3
	     / \  / \
	    4  5  6  7
     * After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \  / \
	    4->5->6->7 -> NULL
	 *  
	 * ACCEPTED
	 */
	public static class TreeLinkNode {
		
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
	
	public void connectI(TreeLinkNode root) {
        
		if (root == null) return;
		ArrayDeque<TreeLinkNode> queue = new ArrayDeque<TreeLinkNode>();
		queue.add(root);
		
		TreeLinkNode prev = null;
		int index = 1;
		int factor = 1;
		while (!queue.isEmpty()) {
			
			TreeLinkNode node = queue.remove();
			if (index == factor) {
				
				node.next = null;
				factor *= 2;
				index = 0;
				if (prev != null) prev.next = node;
				prev = null;
			}
			else {
				
				if (prev == null) prev = node;
				else {
					
					prev.next = node;
					prev = node;
				}
			}
			
			index++;
			
			if (node.left != null) queue.add(node.left);
			if (node.right != null) queue.add(node.right);
		}
    }
	
	/*
	 * Populating Next Right Pointers in Each Node II 
	 * 
	 * Follow up for problem "Populating Next Right Pointers in Each Node".
	 * What if the given tree could be any binary tree? Would your previous solution still work?
	 * Note:
	 * You may only use constant extra space.
	 * For example,
	 * Given the following binary tree,
	         1
	       /  \
	      2    3
	     / \    \
	    4   5    7
     * After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \    \
	    4-> 5 -> 7 -> NULL
	 *
	 * ACCEPTED
	 */
	
	public void connect(TreeLinkNode root) {
        
		if (root == null) return;
		
		Deque<TreeLinkNode> queue0 = new ArrayDeque<TreeLinkNode>();  // Queue 1
		Deque<TreeLinkNode> queue1 = new ArrayDeque<TreeLinkNode>();  // Queue 2
		
		int level = 0;
		queue0.add(root);
		
		TreeLinkNode prev = null;
		while (!queue0.isEmpty() || !queue1.isEmpty()) {
			
			if (level % 2 == 0) {
				
				while (!queue0.isEmpty()) {
					
					TreeLinkNode node = queue0.remove();
					if (prev == null) prev = node;
					else {
						
						prev.next = node;
						prev = node;
					}
					if (node.left != null) queue1.add(node.left);
					if (node.right != null) queue1.add(node.right);
				}
				prev.next = null;
				prev = null;
			}
			else {
				
				while (!queue1.isEmpty()) {
					
					TreeLinkNode node = queue1.remove();
					if (prev == null) prev = node;
					else {
						
						prev.next = node;
						prev = node;
					}
					if (node.left != null) queue0.add(node.left);
					if (node.right != null) queue0.add(node.right);
				}
				prev.next = null;
				prev = null;
			}
			level++;
		}
    }
	
	/*
	 * Flatten Binary Tree to Linked List 
	 * 
	 * Given a binary tree, flatten it to a linked list in-place.
	 * For example,
	 * Given
	         1
	        / \
	       2   5
	      / \   \
	     3   4   6
     * The flattened tree should look like:
		   1
		    \
		     2
		      \
		       3
		        \
		         4
		          \
		           5
		            \
		             6
     * click to show hints.
     * Hints:
     * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
     * 
     * ACCEPTED
	 */
	private List<TreeNode> nodeList = null;
	
	public void flatten(TreeNode root) {
		
		nodeList = new ArrayList<TreeNode>();
		searchTreeNLR(root);
		
		TreeNode prev = new TreeNode(0);
		for (int i = 0; i < nodeList.size(); ++i) {
			
			TreeNode node = nodeList.get(i);
			node.left = null;
			prev.right = node;
			prev = node;
		}
    }
	
	private void searchTreeNLR(TreeNode node) {
		
		if (node == null) return;
		
		nodeList.add(node);
		searchTreeNLR(node.left);
		searchTreeNLR(node.right);
	}
	
	/*
	 * Minimum Depth of Binary Tree
	 * 
	 * Given a binary tree, find its minimum depth.
	 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
	 * 
	 * ACCEPTED
	 */
	private int minDepth = Integer.MAX_VALUE;
	public int minDepth(TreeNode root) {
        
		if (root == null) return 0;
		
		minDepth = Integer.MAX_VALUE;
		int depth = 0;
		searchTreeMin(root, depth);
		return minDepth;
    }
	
	private void searchTreeMin(TreeNode node, int depth) {
		
		if (node.left == null && node.right == null) {
			
			depth++;
			if (depth < minDepth)
				minDepth = depth;
			return;
		}
		
		depth++;
		if (node.left != null) searchTreeMin(node.left, depth);
		if (node.right != null) searchTreeMin(node.right, depth);
		
	}
	
	/*
	 * Maximum Depth of Binary Tree
	 * 
	 * Given a binary tree, find its maximum depth.
	 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
	 * 
	 * ACCEPTED
	 */
	private int maxDepth = Integer.MIN_VALUE;
	public int maxDepth(TreeNode root) {
        
		if (root == null) return 0;
		
		maxDepth = Integer.MIN_VALUE;
		int depth = 0;
		searchTreeMax(root, depth);
		return maxDepth;
    }
	
	private void searchTreeMax(TreeNode node, int depth) {
		
		if (node.left == null && node.right == null) {
			
			depth++;
			if (depth > maxDepth)
				maxDepth = depth;
			return;
		}
		
		depth++;
		if (node.left != null) searchTreeMax(node.left, depth);
		if (node.right != null) searchTreeMax(node.right, depth);
		
	}
	
	/*
	 * Construct Binary Tree from Preorder and Inorder Traversal 
	 * 
	 * Given preorder and inorder traversal of a tree, construct the binary tree.
	 * Note:
	 * You may assume that duplicates do not exist in the tree.
	 * 
	 * ACCEPTED
	 */
	public TreeNode buildTreeI(int[] preorder, int[] inorder) {
        
		if (preorder == null || preorder.length == 0) return null;
		if (inorder == null || inorder.length == 0) return null;
		if (preorder.length != inorder.length) return null;
		
		TreeNode root = new TreeNode(preorder[0]);
		int rootIndex = findIndex(inorder, root.val, preorder.length);
		root.left = buildI(preorder, 1, 1 + rootIndex, inorder, 0, rootIndex);
		root.right = buildI(preorder, 1 + rootIndex, preorder.length, inorder, rootIndex + 1, inorder.length);
		
		return root;
    }
	
	private TreeNode buildI(int[] preorder, int x1, int y1, int[] inorder, int x2, int y2) {
		
		if (preorder == null || preorder.length == 0 || x1 >= preorder.length) return null;
		if (inorder == null || inorder.length == 0 || x2 >= inorder.length) return null;
		
		TreeNode root = null;
		if (x1 != y1 && x2 != y2) {
			
			root = new TreeNode(preorder[x1]);
			int rootIndex = findIndex(inorder, root.val, preorder.length);
			root.left = buildI(preorder, x1 + 1, x1 + 1 + rootIndex - x2, inorder, x2, rootIndex);
			root.right = buildI(preorder, x1 + 1 + rootIndex - x2, y1, inorder, rootIndex + 1, y2);
		}
		
		return root;
	}
	
	/*
	 * Construct Binary Tree from Inorder and Postorder Traversal
	 * 
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 * Note:
	 * You may assume that duplicates do not exist in the tree.
	 * 
	 * ACCEPTED
	 * 
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        
		if (postorder == null || postorder.length == 0) return null;
		if (inorder == null || inorder.length == 0) return null;
		if (postorder.length != inorder.length) return null;
		
		TreeNode root = new TreeNode(postorder[postorder.length - 1]);
		int rootIndex = findIndex(inorder, root.val, postorder.length);
		int x1 = 0;
		int y1 = rootIndex;
		int x2 = 0;
		int y2 = rootIndex;
		root.left = buildII(postorder, x1, y1, inorder, x2, y2);
		
		x1 = y1;
		y1 = postorder.length - 1;
		x2 = rootIndex + 1;
		y2 = inorder.length;
		root.right = buildII(postorder, x1, y1, inorder, x2, y2);
		
		return root;
    }
	
	private TreeNode buildII(int[] postorder, int x1, int y1, int[] inorder, int x2, int y2) {
		
		if (postorder == null || postorder.length == 0 || x1 >= postorder.length) return null;
		if (inorder == null || inorder.length == 0 || x2 >= inorder.length) return null;
		
		TreeNode root = null;
		if (x1 != y1 && x2 != y2) {
			
			root = new TreeNode(postorder[y1 - 1]);
			int rootIndex = findIndex(inorder, root.val, postorder.length);
			root.left = buildII(postorder, x1, x1 + rootIndex - x2, inorder, x2, rootIndex);
			root.right = buildII(postorder, x1 + rootIndex - x2, y1 - 1, inorder, rootIndex + 1, y2);
		}
		
		return root;
	}
	
	private int findIndex(int[] array, int val, int end) {
		
		for (int i = 0; i < Math.min(array.length, end); ++i) {
			
			if (array[i] == val) return i;
		}
		return -1;
	}
	
	/*
	 * Binary Tree Level Order Traversal
	 * 
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 * For example:
	 * Given binary tree {3,9,20,#,#,15,7},
		    3
		   / \
		  9  20
		    /  \
		   15   7
     * return its level order traversal as:
		[
		  [3],
		  [9,20],
		  [15,7]
		]
     * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     * OJ's Binary Tree Serialization:
     * The serialization of a binary tree follows a level order traversal, 
     * where '#' signifies a path terminator where no node exists below.
     * Here's an example:
		   1
		  / \
		 2   3
		    /
		   4
		    \
		     5
     * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
	 * 
	 * ACCEPTED
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
        
		List<List<Integer>> rList = new ArrayList<List<Integer>>();
		
		if (root == null) return rList;
		
		Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		queue.add(new TreeNode(Integer.MAX_VALUE));
		
		List<Integer> list = new ArrayList<Integer>();
		
		while (queue.size() != 0) {
			
			TreeNode node = queue.remove();
			if (node.val != Integer.MAX_VALUE) {
				
				list.add(node.val);
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
			else {
				
				if (queue.size() != 0) {
					
					queue.add(new TreeNode(Integer.MAX_VALUE));
				}
				rList.add(list);
				list = new ArrayList<Integer>();
			}
			
		}
		
		return rList;
    }
	
	/*
	 * Binary Tree Zigzag Level Order Traversal
	 * 
	 * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
	 * (ie, from left to right, then right to left for the next level and alternate between).
	 * For example:
	 * Given binary tree {3,9,20,#,#,15,7},
		    3
		   / \
		  9  20
		    /  \
		   15   7
     * return its zigzag level order traversal as:
		[
		  [3],
		  [20,9],
		  [15,7]
		]
	 * 
	 * ACCEPTED
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
		List<List<Integer>> rList = new ArrayList<List<Integer>>();
		
		if (root == null) return rList;
		
		Deque<TreeNode> stack1 = new ArrayDeque<TreeNode>();
		Deque<TreeNode> stack2 = new ArrayDeque<TreeNode>();
		stack1.push(root);
		int level = 1;
		List<Integer> list = new ArrayList<Integer>();
		while (stack1.size() != 0 || stack2.size() != 0) {
			
			if (level % 2 == 1) {
				
				TreeNode node = stack1.pop();
				list.add(node.val);
				if (node.left != null) stack2.push(node.left);
				if (node.right != null) stack2.push(node.right);
				
				if (stack1.size() == 0) {
					
					level++;
					rList.add(list);
					list = new ArrayList<Integer>();
				}
			}
			else {
				
				TreeNode node = stack2.pop();
				list.add(node.val);
				if (node.right != null) stack1.push(node.right);
				if (node.left != null) stack1.push(node.left);
				
				if (stack2.size() == 0) {
					
					level++;
					rList.add(list);
					list = new ArrayList<Integer>();
				}
			}
		}
		
		
		return rList;
    }
	
	/*
	 * Symmetric Tree
	 * 
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 * For example, this binary tree is symmetric:
		    1
		   / \
		  2   2
		 / \ / \
		3  4 4  3
     * But the following is not:
		    1
		   / \
		  2   2
		   \   \
		   3    3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     * 
     * Wrong Answer
     * Input:	{4,-57,-57,#,67,67,#,#,-97,-97}
     * Output:	false
     * Expected:	true
     * 
     * ACCEPTED
	 */
	List<List<String>> strList = null;
	public boolean isSymmetric(TreeNode root) {
		
		if (root == null) return true;
        
		strList = new ArrayList<List<String>>();
		
		List<String> path = new ArrayList<String>();
		searchTreeNode(root, path);
		
		//Utility.printListList(strList);
		
		int size = strList.size();
		int middle = size / 2;
		for (int i = 0; i < middle; ++i) {
			
			List<String> list1 = strList.get(i);
			List<String> list2 = strList.get(size - 1 - i);
			if (!list1.equals(list2)) {
				return false;
			}
		}
		return true;
    }
	
	private void searchTreeNode(TreeNode node, List<String> path) {
		
		if (node == null) {
			
			path.add("#");
			strList.add(new ArrayList<String>(path));
			path.remove(path.size() - 1);
			return;
		}
		
		path.add(String.format("%d", node.val));
		searchTreeNode(node.left, path);
		searchTreeNode(node.right, path);
		path.remove(path.size() - 1);
	}
	
	/*
	 * Same Tree
	 * 
	 * Given two binary trees, write a function to check if they are equal or not.
	 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
	 * 
	 * ACCEPTED
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        
		List<List<String>> pAllPath = new ArrayList<List<String>>();
		List<String> pPath = new ArrayList<String>();
		getTreePath(p, pPath, pAllPath);
		//Utility.printListList(pAllPath);
		
		List<List<String>> qAllPath = new ArrayList<List<String>>();
		List<String> qPath = new ArrayList<String>();
		getTreePath(q, qPath, qAllPath);
		//Utility.printListList(qAllPath);
		
		return pAllPath.equals(qAllPath);
    }
	
	private void getTreePath(TreeNode node, List<String> path, List<List<String>> allPath) {
		
		if (node == null) {
			
			path.add("#");
			allPath.add(new ArrayList<String>(path));
			path.remove(path.size() - 1);
			return;
		}
		
		path.add(String.format("%d", node.val));
		getTreePath(node.left, path, allPath);
		getTreePath(node.right, path, allPath);
		path.remove(path.size() - 1);
	}
	
	/*
	 * Binary Tree Inorder Traversal 
	 * 
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * For example:
	 * Given binary tree {1,#,2,3},
		   1
		    \
		     2
		    /
		   3
     * return [1,3,2].
     * Note: Recursive solution is trivial, could you do it iteratively?
     * 
     * ACCEPTED
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
        
		List<Integer> rList = new ArrayList<Integer>();
		if (root == null) return rList;
		
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		TreeNode node = root;
		while (node != null || stack.size() != 0) {
			
			while (node != null) {
				
				stack.push(node);
				node = node.left;
			}
			node = stack.remove();
			rList.add(node.val);
			node = node.right;
		}
		
		return rList;
    }
	
	/*
	 * Reverse Linked List II 
	 * 
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 * For example:
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * return 1->4->3->2->5->NULL.
	 * Note:
	 * Given m, n satisfy the following condition:
	 * 1 鈮� m 鈮� n 鈮� length of list.
	 * 
	 * ACCEPTED
	 * 
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
        
		ListNode thead = new ListNode(-1);
		thead.next = head;
		
		ListNode curr = head;
		ListNode prev = thead;
		
		int pos = 1;
		while (pos < m) {
			
			pos++;
			prev = curr;
			curr = curr.next;
		}
			
			
		ListNode h = curr;
		ListNode t = h;
		while (m <= pos && pos < n) {
			
			ListNode temp = h;
			h = t.next;
			t.next = h.next;
			h.next = temp;
			pos++;
		}
		
		prev.next = h;

		return thead.next;
    }
	
	/* Gray Code
     *
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
		00 - 0
		01 - 1
		11 - 3
		10 - 2
     * Note:
     * For a given n, a gray code sequence is not uniquely defined.
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     * 
     * ACCEPTED
     */	
	public List<Integer> grayCode(int n) {
        
		List<Integer> gcList = new ArrayList<Integer>();
		gcList.add(0);
		
		int times = getFactorPower(2, n) - 1;
		int number = 0;
		for (int i = 0; i < times; ++i) {
			
			int factor = 1;
			int temp = number ^ factor;
			while (gcList.contains(temp)) {
				
				factor <<= 1;
				temp = number ^ factor;
				
			}
			gcList.add(temp);
			number = temp;
		}
		
		return gcList;
    }
	
	private int getFactorPower(int factor, int power) {
		
		int result = 1;
		for (int i = 0; i < power; ++i) {
			
			result *= factor;
		}
		
		return result;
	}
	
	/*
	 * Merge Sorted Array
	 * 
	 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * Note:
	 * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
	 * The number of elements initialized in A and B are m and n respectively.
	 * 
	 * ACCEPTED
	 * 
	 */
	public void merge(int A[], int m, int B[], int n) {
        
		int[] temp = new int[m + n];
		int i = 0;
		int j = 0;
		int index = 0;
		while (i < m && j < n) {
			
			if (A[i] < B[j]) {
				
				temp[index++] = A[i++];
			}
			else {
				
				temp[index++] = B[j++];
			}
		}
		while (i < m) {
			
			temp[index++] = A[i++];
		}
		while (j < n) {
			
			temp[index++] = B[j++];
		}
		/* arraycopy(Object src, int srcPos, Object dest, int destPos, int length) 
		 * 
		 * src - the source array.
		 * srcPos - starting position in the source array.
		 * dest - the destination array.
		 * destPos - starting position in the destination data.
		 * length - the number of array elements to be copied. 
		 */
		System.arraycopy(temp, 0, A, 0, m + n);
    }
	
	/*
	 * Remove Duplicates from Sorted List 
	 * 
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * For example,
	 * Given 1->1->2, return 1->2.
	 * Given 1->1->2->3->3, return 1->2->3.
	 * 
	 * ACCEPTED
	 */
	public ListNode deleteDuplicatesI(ListNode head) {
        
		ListNode node = head;
		ListNode prev = head;
		
		while (node != null) {
			
			if (prev.val != node.val) {
				
				prev.next = node;
				prev = node;
			}
			
			node = node.next;
			prev.next = null;
		}
		
		return head;
    }
	
	/*
	 * Remove Duplicates from Sorted List II
	 * 
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 * 
	 * For example,
	 * Given 1->2->3->3->4->4->5, return 1->2->5.
	 * Given 1->1->1->2->3, return 2->3.
	 * 
	 * ACCEPTED
	 * 
	 */
	public ListNode deleteDuplicates(ListNode head) {
		
		ListNode thead = new ListNode(-1);
		ListNode tnode = thead;
		
		ListNode prev = head;
		ListNode node = prev;
		while (prev != null && node != null) {
			
			node = node.next;
			if (node == null || prev.val != node.val) {
				
				tnode.next = prev;
				tnode = tnode.next;
			}
			else {
				
				while (node != null && prev.val == node.val) {
					
					node = node.next;
				}
			}
			prev = node;
		}
		tnode.next = prev;
		
		
		return thead.next;
    }
	
	/*
	 * Search in Rotated Sorted Array
	 * 
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * You are given a target value to search. If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 * 
	 * ACCEPTED
	 */
	public int searchI(int[] A, int target) {
        
		if (A == null || A.length == 0) return -1;
		
		int pivot = A[0];
		if (target < pivot) {
			
			for (int i = A.length - 1; i >= 0; --i) {
				
				if (A[i] > pivot) break;
				if (A[i] == target) return i;
			}
		}
		else {
			
			for (int i = 0; i < A.length; ++i) {
				
				if (A[i] < pivot) break;
				if (A[i] == target) return i;
			}
		}
		
		return -1;
    }
	
	/*
	 * Search in Rotated Sorted Array II
	 * 
	 * Follow up for "Search in Rotated Sorted Array":
	 * What if duplicates are allowed?
	 * Would this affect the run-time complexity? How and why?
	 * Write a function to determine if a given target is in the array.
	 * 
	 * ACCEPTED
	 */
	public boolean search(int[] A, int target) {
        
		if (A == null || A.length == 0) return false;
		
		int pivot = A[0];
		if (target < pivot) {
			
			for (int i = A.length - 1; i >= 0; --i) {
				
				if (A[i] > pivot) break;
				if (A[i] == target) return true;
			}
		}
		else {
			
			for (int i = 0; i < A.length; ++i) {
				
				if (A[i] < pivot) break;
				if (A[i] == target) return true;
			}
		}
		
		return false;
    }
	
	/*
	 * Remove Duplicates from Sorted Array
	 * 
	 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	 * Do not allocate extra space for another array, you must do this in place with constant memory.
	 * For example,
	 * Given input array A = [1,1,2],
	 * Your function should return length = 2, and A is now [1,2].
	 * 
	 * Wrong Answer
	 * Input:	[1,1,2,2]
	 * Output:	[1,2,2]
	 * Expected:[1,2]
	 * 
	 * Input:	[-1,0,0,0,0,3,3]
	 * Output:	[-1,0,3,3]
	 * Expected:[-1,0,3]
	 * 
	 * ACCEPTED
	 */
	public int removeDuplicatesI(int[] A) {
        
		if (A == null) return -1;

		int length = A.length;
		int dupi = -1;
		int prev = 0;
		int curr = 1;
		while (0 < curr && curr < length) {
			
			if (dupi == -1) {
				
				if (A[prev] == A[curr]) {
					
					dupi = curr;
				}
				prev = curr;
				curr++;
			}
			else {
				
				if (A[prev] != A[curr]) {
					
					moveForwardt(A, curr, dupi);
					length -= (curr - dupi);
					curr = dupi;
					prev = curr - 1;
					dupi = -1;
				}
				else {
					
					prev = curr;
					curr++;
				}
			}
		}
		if (dupi != -1 && curr >= length) {
			
			length = dupi;
		}
		
		return length;
    }
	/*
	 *   |_______|i|_________|j|______|
	 *           to <------- from
	 *   to < from
	 */
	private void moveForwardt(int[] array, int from, int to) {
		
		int i = to;
		int j = from;
		while (i < j && j < array.length) {
			
			array[i++] = array[j++];
		}
	}
	
	/*
	 * Remove Duplicates from Sorted Array II 
	 * 
	 * Follow up for "Remove Duplicates":
	 * What if duplicates are allowed at most twice?
	 * For example,
	 * Given sorted array A = [1,1,1,2,2,3],
	 * Your function should return length = 5, and A is now [1,1,2,2,3].
	 * 
	 * ACCEPTED
	 */
	public int removeDuplicates(int[] A) {
        
		if (A == null) return -1;

		int length = A.length;
		int dupi = -1;
		int prev = 0;
		int curr = 1;
		while (0 < curr && curr < length) {
			
			if (dupi == -1) {
				
				if (A[prev] == A[curr]) {
					
					dupi = curr;
				}
				prev = curr;
				curr++;
			}
			else {
				
				if (A[dupi] == A[curr]) {
					
					moveForwardt(A, curr, dupi);
					length -= (curr - dupi);
					//curr = dupi;
					//prev = curr - 1;
					//dupi = -1;
				}
				else {
					
					dupi = -1;
					prev = curr;
					curr++;
				}
			}
		}
		/*
		if (dupi != -1 && curr >= length) {
			
			length = dupi;
		}*/
		
		return length;
    }
	
	/*
	 * Combinations
	 * 
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 * For example,
	 * If n = 4 and k = 2, a solution is:
		[
		  [2,4],
		  [3,4],
		  [2,3],
		  [1,2],
		  [1,3],
		  [1,4],
		]
		
	 * Time Limit Exceeded
	 * n = 10, k = 7
	 * 
	 * Wrong Answer
	 * Input:	1, 1
	 * Output:	[]
	 * Expected:[[1]]
	 * 
	 * ACCEPTED
	 */
	/*
	private List<List<Integer>> combineList = null;
	public List<List<Integer>> combine(int n, int k) {
        
		combineList = new ArrayList<List<Integer>>();
		List<Integer> list = null;
		int level = 1;
		for (int i = 1; i <= n; ++i) {
			
			list = new ArrayList<Integer>();
			list.add(i);
			getCombine(n, level, k, list);
			list.remove(list.size() - 1);
		}
		return combineList;
    }
	
	private void getCombine(int range, int level, int maxLevel, List<Integer> list) {
		
		if (level == maxLevel) {
			
			List<Integer> ml = new ArrayList<Integer>(list);
			Collections.sort(ml);
			if (!combineList.contains(ml)) {
				
				combineList.add(new ArrayList<Integer>(ml));
			}
			return;
		}
		
		level++;
		for (int i = 1; i <= range; ++i) {
			
			if (!list.contains(i)) {
				
				list.add(i);
				getCombine(range, level, maxLevel, list);
				list.remove(list.size() - 1);
			}
		}
	}*/
	private List<List<Integer>> combineList = null;
	public List<List<Integer>> combine(int n, int k) {
		
		List<Integer> rangeList = new ArrayList<Integer>();
		for (int i = 1; i <= n; ++i) {
			
			rangeList.add(i);
		}
		
		combineList = new ArrayList<List<Integer>>();
		int level = 1;
		for (int i = 0; i < rangeList.size(); ++i) {
			
			List<Integer> list = new ArrayList<Integer>();
			int num = rangeList.get(i);
			list.add(num);
			getCombine(rangeList, i + 1, level, k, list);
		}
		return combineList;
	}
	
	private void getCombine(List<Integer> range, int index, int level, int maxLevel, List<Integer> list) {
		
		if (level >= maxLevel) {
			
			combineList.add(new ArrayList<Integer>(list));
			return;
		}
		level++;
		for (int i = index; i < range.size(); ++i) {
			
			int num = range.get(i);
			list.add(num);
			getCombine(range, i + 1, level, maxLevel, list);
			list.remove(new Integer(num));
			//range.add(i, num);
		}
	}
	
	/*
	 * Sort Colors
	 * 
	 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
	 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	 * Note:
	 * You are not suppose to use the library's sort function for this problem.
	 * 
	 * click to show follow up.
	 * 
	 * Follow up:
	 * A rather straight forward solution is a two-pass algorithm using counting sort.
	 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
	 * Could you come up with an one-pass algorithm using only constant space?
	 * 
	 * ACCEPTED
	 */
	public void sortColors(int[] A) {
        
		if (A == null || A.length == 0) return;
		
		int i = 0;
		for (int k = 0; k < 2; ++k) {
			
			int j = A.length - 1;
			while (i < j && i < A.length) {
				
				while (i < A.length && A[i] == k) i++;
				while (0 <= j && A[j] != k) j--;
				
				if (i < j) {
						
					int temp = A[i];
					A[i] = A[j];
					A[j] = temp;
					
					i++;
				}
			}
		}
    }
	
	/*
	 * Search a 2D Matrix
	 * 
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
	 * Integers in each row are sorted from left to right.
	 * The first integer of each row is greater than the last integer of the previous row.
	 * For example,
	 * 
	 * Consider the following matrix:
		[
		  [1,   3,  5,  7],
		  [10, 11, 16, 20],
		  [23, 30, 34, 50]
		]
	 * Given target = 3, return true.
	 * 
	 * ACCEPTED
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        
		if (matrix == null || matrix.length == 0) return false;
		
		int i = 0;
		while (i < matrix.length && matrix[i][0] <= target) {
			
			i++;
		}
		
		// matrix[i][0] > target
		if (i > 0) i--;
		
		int j = 0;
		while (j < matrix[i].length && matrix[i][j] <= target) {
			
			j++;
		}
		// matrix[i][j] > target
		if (j > 0) j--;
		
		if (matrix[i][j] == target) return true;
		
		return false;
    }
	
	/*
	 * Set Matrix Zeroes
	 * 
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 * 
	 * click to show follow up.
	 * 
	 * Follow up:
	 * Did you use extra space?
	 * A straight forward solution using O(mn) space is probably a bad idea.
	 * A simple improvement uses O(m + n) space, but still not the best solution.
	 * Could you devise a constant space solution?
	 * 
	 * Wrong Answer
	 * Input:	 [[0,0,0,5],[4,3,1,4],[0,1,1,4],[1,2,1,3],[0,0,1,1]]
	 * Output:	 [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
	 * Expected:[[0,0,0,0],[0,0,0,4],[0,0,0,0],[0,0,0,3],[0,0,0,0]]
     * 
     * ACCEPTED
	 */
	public void setZeroes(int[][] matrix) {
        
		boolean isFirstRow = false;
		boolean isFirstColumn = false;
		
		for (int j = 0; j < matrix[0].length; ++j) {
			
			if (matrix[0][j] == 0) {
				
				isFirstRow = true;
			}
		}
		
		for (int i = 0; i < matrix.length; ++i) {
			
			if (matrix[i][0] == 0) {
				isFirstColumn = true;
			}
		}
		
		for (int i = 1; i < matrix.length; ++i) {
			
			for (int j = 1; j < matrix[i].length; ++j) {
				
				if (matrix[i][j] == 0) {
					
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		// check row
		for (int i = 1; i < matrix.length; ++i) {
			
			if (matrix[i][0] == 0) {
				
				for (int j = 1; j < matrix[i].length; ++j) {
					
					matrix[i][j] = 0;
				}
			}
		}
		
		// check column
		for (int j = 1; j < matrix[0].length; ++j) {
			
			if (matrix[0][j] == 0) {
				
				for (int i = 1; i < matrix.length; ++i) {
					
					matrix[i][j] = 0;
				}
			}
		}
		
		if (isFirstRow) {
			
			for (int j = 0; j < matrix[0].length; ++j) {
				
				matrix[0][j] = 0;
			}
		}
		if (isFirstColumn) {
			
			for (int i = 0; i < matrix.length; ++i) {
				
				matrix[i][0] = 0;
			}
		}
    }
	
	/*
	 * Climbing Stairs
	 * 
	 * You are climbing a stair case. It takes n steps to reach to the top.
	 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	 * 
	 * Time Limit Exceeded
	 * Last executed input:	38
	 * 
	 * Wrong Answer
	 * Input:	35
	 * Output:	969093
	 * Expected:	14930352
	 * 
	 * Compile Error
	 * Line 19: error: cannot find symbol: class BigInteger
	 */
	
	private int ways = 0;
	public int climbStairsSlow(int n) {
        
		ways = 0;
		
		int[] types = new int[]{1,2};
		getSum(types, ways, n);
		
		return ways;
    }
	
	private void getSum(int[] types, int value, int target) {
		
		if (value > target) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			
			return;
		}
		if (value == target) {
			
			ways++;
			return;
		}
		for (int i = 0; i < types.length; ++i) {
			
			getSum(types, value + types[i], target);
		}
	}
	/*
	public int climbStairs(int n) {
		
		int ways = 0;
		int numOf1 = n;
		int numOf2 = 0;
		while (numOf1 >= 0) {
			
			int x = numOf1 + numOf2;
			int y = numOf2;
			//int valueBI = getCombinationBI(x, y);
			int value = getCombination(x, y);
			//System.out.println("x=" + x + " y=" + y + " BI:" + valueBI + " v:" + value);
			
			ways += value;
			numOf2++;
			numOf1 -= 2;
		}
		
		//int res1 = getCombinationBI(35,0);
		//int res2 = getCombination(35,0);
		//System.out.println(res1 + " " + res2);
		
		return ways;
	}*/
	// Line 19: error: cannot find symbol: class BigInteger
	/*
	private int getCombinationBI(int m, int n) {
		
		BigInteger result = new BigInteger("1");
		int num = m;
		while (num != (m - n)) {
			
			result = result.multiply(new BigInteger(String.format("%d", num)));
			num--;
		}
		num = n;
		while (num > 1) {
			
			result = result.divide(new BigInteger(String.format("%d", num)));
			num--;
		}
		return result.intValue();
	}*/
	
	//  n
	// Cm
	// Time Limit Exceeded
	/*
	private int getCombination(int m, int n) {
		
		if (m == n || n == 0) {
			
			return 1;
		}
		if (n == 1) {
			
			return m;
		}
		
		int val1 = getCombination(m - 1, n - 1);
		int val2 = getCombination(m - 1, n);
		return val1 + val2;
	}*/
	/*
	 * 1 - 1
	 * 2 - 2
	 * 3 - 3
	 * 4 - 5
	 * 5 - 8
	 * 6 - 13
	 * 7 - 21 
	 * 
	 * ACCEPTED
	 */
	private Map<Integer, Integer> resMap = new HashMap<Integer, Integer>();
	public int climbStairs(int n) {
		
		if (resMap.containsKey(n)) {
			
			return resMap.get(n);
		}
		
		if (n == 1 || n == 2) {
			
			resMap.put(n, n);
			return n;
		}
		
		int value = climbStairs(n - 1) + climbStairs(n - 2);
		resMap.put(n, value);
		return value;
	}
	
	/*
	 * Plus One
	 * 
	 * Given a non-negative number represented as an array of digits, plus one to the number.
	 * The digits are stored such that the most significant digit is at the head of the list.
	 * 
	 * ACCEPTED
	 */
	public int[] plusOne(int[] digits) {
		
		if (digits == null) return null;
		
		int next = 1;
		for (int i = digits.length - 1; i >= 0; --i) {
			
			int number = digits[i];
			if (number + next > 9) {
				
				digits[i] = 0;
				next = 1;
			}
			else {
				
				digits[i] = number + next;
				next = 0;
			}
		}
		if (next == 1) {
			
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			System.arraycopy(digits, 0, newDigits, 1, digits.length);
			return newDigits;
		}
        return digits;
    }
	/*
	 * Merge Two Sorted Lists
	 * Merge two sorted linked lists and return it as a new list. 
	 * The new list should be made by splicing together the nodes of the first two lists.
	 * 
	 * ACCEPTED
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
		ListNode thead = new ListNode(-1);
		ListNode node = thead;
		
		ListNode p1 = l1;
		ListNode p2 = l2;
		while (p1 != null && p2 != null) {
			
			if (p1.val <= p2.val) {
				
				node.next = p1;
				p1 = p1.next;
			}
			else {
				
				node.next = p2;
				p2 = p2.next;
			}
			node = node.next;
		}
		if (p1 != null) {
			
			node.next = p1;
		}
		if (p2 != null) {
			
			node.next = p2;
		}
		
		return thead.next;
    }
	
	/*
	 * Single Number
	 * 
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * Note:
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * 
	 * ACCEPTED
	 */
	public int singleNumberI(int[] A) {
        
		for (int i = 1; i < A.length; ++i) {
			
			A[0] = A[0] ^ A[i];
		}
		
		return A[0];
    }
	
	/*
	 * Single Number II 
	 * 
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	 * Note:
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 */
	public int singleNumber(int[] A) {
        
		if (A == null) return -1;
		if (A.length == 1) return A[0];
		
		//int temp = A[0];
		//A[0] = (A[0] ^ A[1]) & ~A[0];
		//A[1] = (temp ^ A[1]) & ~A[0];
		
		int ones = 0;
		int twos = 0;
		// i = 0;
		//ones = A[0];
		//twos = (0 ^ A[0]) & ~A[0];
		// i = 1;
		ones = (A[0] ^ A[1]) & ~((0 ^ A[0]) & ~A[0]);
		twos = (((0 ^ A[0]) & ~A[0]) ^ A[1]) & ~ones;

		for (int i = 2; i < A.length; ++i) {
			
			ones = (ones ^ A[i]) & ~twos;
			twos = (twos ^ A[i]) & ~ones;
		}
		
		return ones;
		
		/*
		 * answer with extra space
		 */
		/*
		int ones = 0, twos = 0;
	    for(int i = 0; i < A.length; i++){
	        ones = (ones ^ A[i]) & ~twos;
	        twos = (twos ^ A[i]) & ~ones;
	    }
	    return ones;
	    */
    }
	
	/*
	 * Minimum Path Sum
	 * 
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
	 * which minimizes the sum of all numbers along its path.
	 * Note: You can only move either down or right at any point in time.
	 * 
	 * Time Limit Exceeded
	 * Last executed input:	[[7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5],
	 *                       [9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6],
	 *                       [8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8],
	 *                       [6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4],
	 *                       [7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4],
	 *                       [9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9],
	 *                       [1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4],
	 *                       [3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2],
	 *                       [1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3],
	 *                       [5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7],
	 *                       [2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7],
	 *                       [0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9]]
	 * 
	 * ACCEPTED
	 */
	/* Time Limit Exceeded
	private int minPathSum = Integer.MAX_VALUE;
	public int minPathSum(int[][] grid) {
        
		if (grid == null || grid.length == 0) return -1;
		
		int sum = 0;
		searchMetrix(grid, 0, 0, sum);
		 
		return minPathSum;
    }
	
	private void searchMetrix(int[][] grid, int i, int j, int sum) {
		
		if (i == grid.length - 1 && j == grid[0].length - 1) {
			
			sum += grid[i][j];
			if (sum < minPathSum) {
				
				minPathSum = sum;
			}
			return;
		}
		sum += grid[i][j];
		if (i + 1 < grid.length) {
			
			searchMetrix(grid, i + 1, j, sum);
		}
		if (j + 1 < grid[0].length) {
			
			searchMetrix(grid, i, j + 1, sum);
		}
	}*/
	public int minPathSum(int[][] grid) {
	
		if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
		
		for (int i = grid.length - 1; i >= 0; --i) {
			
			for (int j = grid[0].length - 1; j >= 0; --j) {
				
				grid[i][j] += getMinDist(grid, i, j);
			}
		}
		
		return grid[0][0];
	}
	private int getMinDist(int[][] grid, int i, int j) {
		
		if (i == grid.length - 1 && j == grid[0].length - 1) {
			
			return 0;
		}
		
		int right = Integer.MAX_VALUE;
		int down = Integer.MAX_VALUE;
		
		if (i + 1 < grid.length) {
			
			down = grid[i + 1][j];
			
		}
		if (j + 1 < grid[0].length) {
			
			right = grid[i][j + 1];
		}
		return Math.min(right, down);
	}
	
	/*
	 * Unique Paths
	 * 
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
	 * The robot can only move either down or right at any point in time. The robot is trying to reach 
	 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
	 * How many possible unique paths are there?
	 * 
	 * Note: m and n will be at most 100.
	 * 
	 * Time Limit Exceeded
	 * Last executed input:	23, 12
	 * 
	 * ACCEPTED
	 */
	/*
	 * Time Limit Exceeded
	private int uniquePaths = 0;
	public int uniquePaths(int m, int n) {
		
		if (m <= 0 || n <= 0) return -1;
        
		uniquePaths = 0;
		searchMetrix(0, 0, m, n);
		return uniquePaths;
    }
	
	private void searchMetrix(int i, int j,  int m, int n) {
		
		if (i >= m || j >= n) return;
		
		if (i == m - 1 && j == n - 1) {
			
			uniquePaths++;
			return;
		}
		
		searchMetrix(i + 1, j, m, n);
		searchMetrix(i, j + 1, m, n);
	}
	*/
	/* 5x3
	 * | 1 | 1 | 1 |
	 * | 1 | 2 | 3 | ==> 3 = 1 + 2
	 * | 1 | 3 | 6 | ==> 6 = 3 + 3
	 * | 1 | 4 | 10| ==> 10 = 6 + 4 (up + left)
	 * | 1 | 5 | 15|
	 */
	public int uniquePaths(int m, int n) {
		
		if (m <= 0 || n <= 0) return -1;
		
		int[][] metrix = new int[m][n];
		
		for (int i = 0; i < m; ++i) {
			
			for (int j = 0; j < n; ++j) {
				
				metrix[i][j] = getMetrixValue(metrix, i, j);
			}
		}
		
		return metrix[m - 1][n - 1];
	}
	private int getMetrixValue(int[][] metrix, int i , int j) {
		
		if (i == 0 && j == 0) {
			
			return 1;
		}
		
		int up = 0;
		int left = 0;
		
		if (i - 1 >= 0) {
			
			up = metrix[i - 1][j];
		}
		if (j - 1 >= 0) {
			
			left = metrix[i][j - 1];
		}
		return up + left;
	}
	/*
	 * Spiral Matrix II
	 * 
	 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
	 * For example,
	 * Given n = 3,
	 * You should return the following matrix:
		[
		 [ 1, 2, 3 ],
		 [ 8, 9, 4 ],
		 [ 7, 6, 5 ]
		]
     * 
     * ACCEPTED
	 */
	public int[][] generateMatrix(int n) {
        
		if (n < 0) return null;
		int[][] metrix = new int[n][n];
		
		int value = 1;
		
		int times = 0;
		int endTimes = (n + 1) / 2;
		
		while (times < endTimes) {
			
			int i = times;
			int j = times;
			
			while (j < n - times) {
				
				metrix[i][j] = value++;
				++j;
			}
			--j;
			++i;
			
			while (i < n - times) {
				
				metrix[i][j] = value++;
				++i;
			}
			--i;
			--j;
			
			while (times <= j) {
				
				metrix[i][j] = value++;
				--j;
			}
			++j;
			--i;
			
			while (times < i) {
				
				metrix[i][j] = value++;
				--i;
			}
			++times;
		}
		
		return metrix;
    }
	
	/*
	 * Swap Nodes in Pairs
	 * 
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example,
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
	 * 
	 * ACCEPTED
	 * 
	 */
	public ListNode swapPairs(ListNode head) {
        
		ListNode thead = new ListNode(-1);
		ListNode prev = thead;
		ListNode node = head;
		while (node != null && node.next != null) {
			
			ListNode next = node.next;
			
			node.next = next.next;
			next.next = node;
			prev.next = next;
			
			prev = node;
			node = node.next;
		}
		if (node != null) {
			
			prev.next = node;
		}
		
		return thead.next;
    }
	
	/*
	 * Permutations
	 * 
	 * Given a collection of numbers, return all possible permutations.
	 * For example,
	 * [1,2,3] have the following permutations:
	 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 * 
	 * ACCEPTED
	 */
	private List<List<Integer>> llist = null;
	public List<List<Integer>> permute(int[] num) {
        
		llist = new ArrayList<List<Integer>>();
		if (num == null || num.length == 0) return llist;
		
		List<Integer> range = new LinkedList<Integer>();
		for (int i = 0; i < num.length; ++i) {
			
			range.add(num[i]);
		}
		List<Integer> list = new ArrayList<Integer>();
		getPermutation(range, list);
		
		return llist;
    }
	
	private void getPermutation(List<Integer> range, List<Integer> list) {
		
		if (range.size() == 0) {
			
			llist.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < range.size(); ++i) {
			
			int val = range.remove(i);
			list.add(val);
			
			getPermutation(range, list);
			
			range.add(i, val);
			list.remove(list.size() - 1);
		}
	}
	
	/*
	 * Length of Last Word
	 * 
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	 * return the length of last word in the string.
	 * If the last word does not exist, return 0.
	 * Note: A word is defined as a character sequence consists of non-space characters only.
	 * For example, 
	 * Given s = "Hello World",
	 * return 5.
	 * 
	 * Wrong Answer
	 * Input:	"a "
	 * Output:	 0
	 * Expected: 1
	 * 
	 * ACCEPTED
	 */
	public int lengthOfLastWord(String s) {
        
		if (s == null) return 0;
		
		int length = 0;
		for (int i = s.length() - 1; i >= 0; --i) {
			
			if (s.charAt(i) == ' ') {
				
				if (length == 0) continue;
				else return length;
			}
			else length++;
		}
		return length;
    }
	
	/*
	 * Search Insert Position
	 * 
	 * Given a sorted array and a target value, return the index if the target is found. 
	 * If not, return the index where it would be if it were inserted in order.
	 * You may assume no duplicates in the array.
	 * 
	 * Here are few examples.
	 * [1,3,5,6], 5 鈫� 2
	 * [1,3,5,6], 2 鈫� 1
	 * [1,3,5,6], 7 鈫� 4
	 * [1,3,5,6], 0 鈫� 0
	 * 
	 * ACCEPTED
	 */
	public int searchInsert(int[] A, int target) {
        
		int i = 0;
		for (; i < A.length; ++i) {
			
			if (target <= A[i]) {
				
				return i;
			}
		}
		if (i >= A.length) {
			
			return i;
		}
		
		return -1;
    }
	
	/*
	 * Remove Element 
	 * 
	 * Given an array and a value, remove all instances of that value in place and return the new length.
	 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 * 
	 * ACCEPTED
	 */
	public int removeElement(int[] A, int elem) {
        
		if (A == null || A.length == 0) return 0;
		
		int length = A.length;
		
		int i = 0;
		while (i < length) {
			
			if (A[i] == elem) {
				
				for (int j = i; j < length - 1; ++j) {
					
					A[j] = A[j + 1];
				}
				--length;
			}
			else {
				
				++i;
			}
		}
		
		return length;
    }
	
	/*
	 * Merge k Sorted Lists
	 * 
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	 * 
	 * Last executed input:	[{},{}]
	 * 
	 * Time Limit Exceeded
	 * 
	 * Runtime Error
	 * Runtime Error Message:	Line 46: java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
	 * Last executed input:	[{},{1}]
	 * 
	 * Time Limit Exceeded
	 * 
	 * ACCEPTED
	 */
	/*
	 * Time Limit Exceeded
	public ListNode mergeKLists(List<ListNode> lists) {
		
		if (lists == null || lists.size() == 0) return null;
		
		ListNode thead = new ListNode(-1);
		ListNode pnode = thead;
		
		while (lists.size() > 1) {
			
			int minValue = Integer.MAX_VALUE;
			int index = -1;
			
			for (int i = lists.size() - 1; i >= 0; --i) {
				
				ListNode node = lists.get(i);
				if (node != null && node.val < minValue) {
					
					minValue = node.val;
					index = i;
				}
			}
			if (index == -1) break;
			
			ListNode node = lists.remove(index);
			pnode.next = node;
			if (node.next != null) {
				
				lists.add(index, node.next);
			}
			pnode = pnode.next;
		}
		if (lists.size() == 1) pnode.next = lists.get(0);
		
		return thead.next;
    }*/
	
	/*
	 *  Time Limit Exceeded
	public ListNode mergeKLists(List<ListNode> lists) {
		
		if (lists == null || lists.size() == 0) return null;
		ListNode n1 = lists.get(0);
		
		for (int i = 1; i < lists.size(); ++i) {
			
			n1 = mergeKLists(n1, lists.get(i));
		}
		return n1;
	}*/
	
	// 分治法
	public ListNode mergeKLists(List<ListNode> lists) {
		
		if (lists == null || lists.size() == 0) return null;
		if (lists.size() == 1) return lists.get(0);
		
		int mid = (lists.size() - 1) / 2;
		ListNode n1 = mergeKLists(lists, 0, mid);
		ListNode n2 = mergeKLists(lists, mid + 1, lists.size() - 1);
		
		return mergeKLists(n1, n2);
	}
	
	public ListNode mergeKLists(List<ListNode> lists, int start, int end) {
		
		if (start == end) {
			
			return lists.get(start);
		}
		
		int mid = (start + end) / 2;
		
		ListNode n1 = mergeKLists(lists, start, mid);
		ListNode n2 = mergeKLists(lists, mid + 1, end);
		
		return mergeKLists(n1, n2);
	}

	public ListNode mergeKLists(ListNode h1, ListNode h2) {
		
		ListNode nhead = new ListNode(-1);
		ListNode pnode = nhead;
		
		ListNode n1 = h1;
		ListNode n2 = h2;
		while (n1 != null && n2 != null) {
			
			if (n1.val < n2.val) {
				
				pnode.next = n1;
				n1 = n1.next;
				
			}
			else {
				
				pnode.next = n2;
				n2 = n2.next;
			}
			pnode = pnode.next;
		}
		if (n1 != null) {
			
			pnode.next = n1;
		}
		if (n2 != null) {
			
			pnode.next = n2;
		}
		
		return nhead.next;
	}
	
	/*
	 * Integer to Roman
	 * Given an integer, convert it to a roman numeral.
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * ACCEPTED
	 */
	public String intToRoman(int num) {
		
		Map<Integer, String> romanMap = new HashMap<Integer, String>();
		romanMap.put(1000, "M");
		romanMap.put(500, "D");
		romanMap.put(100, "C");
		romanMap.put(50, "L");
		romanMap.put(10, "X");
		romanMap.put(5, "V");
		romanMap.put(1, "I");
        
		StringBuilder sb = new StringBuilder();
		int n = num;
		int factor = 1000;
		
		while (n > 0) {
			
			int left = n / factor;
			if (left > 0) {
				
				sb.append(digitToRoman(left, factor, romanMap));
			}
			
			n = n - left * factor;
			factor = factor / 10;
		}
		
		return sb.toString();
    }
	
	private String digitToRoman(int digit, int factor, Map<Integer, String> map) {
		
		StringBuilder sb = new StringBuilder();
		
		if (digit == 9) {
			
			sb.append(map.get(factor));
			sb.append(map.get(factor * 10));
			return sb.toString();
		}
		else if (digit == 5) {
			
			return map.get(digit * factor);
		}
		else if (digit == 4) {
			
			sb.append(map.get(factor));
			sb.append(map.get(5 * factor));
			return sb.toString();
		}
		else if (digit < 4) {  // 1,2,3
			
			String sig = map.get(factor);
			for (int i = 0; i < digit; ++i) {
				
				sb.append(sig);
			}
			return sb.toString();
		}
		else { // digit 6,7,8
			
			sb.append(map.get(5 * factor));
			for (int i = 5; i < digit; ++i) {
				
				sb.append(map.get(factor));
			}
			return sb.toString();
		}
	}
	
	/*
	 * Roman to Integer
	 * Given a roman numeral, convert it to an integer.
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * ACCEPTED
	 */
	public int romanToInt(String s) {
        
		if (s == null || s.length() == 0) return 0;
		
		Map<Character, Integer> romanMap = new HashMap<Character, Integer>();
		romanMap.put('M', 1000);
		romanMap.put('D', 500);
		romanMap.put('C', 100);
		romanMap.put('L', 50);
		romanMap.put('X', 10);
		romanMap.put('V', 5);
		romanMap.put('I', 1);
		
		int result = 0;
		int prev = -1;
		for (int i = s.length() - 1; i >= 0; --i) {
			
			int val = romanMap.get(s.charAt(i));
			
			if (prev == -1) {
				
				prev = val;
			}
			else if (val < prev) {
				
				val *= -1;
			}
			
			result += val;
			prev = val;
		}
		return result;
    }
	
	/*
	 * Reverse Integer
	 * 
	 * Reverse digits of an integer.
	 * 
	 * Example1: x = 123, return 321
	 * Example2: x = -123, return -321
	 * 
	 * click to show spoilers.
	 * 
	 * Have you thought about this?
	 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
	 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
	 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
	 * then the reverse of 1000000003 overflows. How should you handle such cases?
	 * Throw an exception? Good, but what if throwing an exception is not an option? 
	 * You would then have to re-design the function (ie, add an extra parameter).
	 * 
	 * ACCEPTED
	 * (暂时无需考虑溢出问题)
	 */
	public int reverse(int x) {
        
		boolean isNegative = false;
		int num = x;
		if (num < 0) {
			
			num *= -1;
			isNegative = true;
		}
		int result = 0;
		
		while (num > 0) {
			
			result *= 10;
			int left = num % 10;
			result += left;
			num = num / 10;
		}
		
		if (isNegative) {
			
			result *= -1;
		}
		return result;
    }
	
	/*
	 * Subsets 
	 * 
	 * Given a set of distinct integers, S, return all possible subsets.
	 * Note:
	 * Elements in a subset must be in non-descending order.
	 * The solution set must not contain duplicate subsets.
	 * For example,
	 * If S = [1,2,3], a solution is:
			[
			  [3],
			  [1],
			  [2],
			  [1,2,3],
			  [1,3],
			  [2,3],
			  [1,2],
			  []
			]
	 * 
	 * Wrong Answer
	 * 
	 * Input:	[4,1,0]
	 * Output:	[[4,1,0],[4,1],[4,0],[1,0],[4],[1],[0],[]]
	 * Expected:[[],[0],[1],[4],[0,1],[0,4],[1,4],[0,1,4]]
	 * 
	 * ACCEPTED
	 */
	private List<List<Integer>> illist = null;
	public List<List<Integer>> subsets(int[] S) {
        
		illist = new LinkedList<List<Integer>>();
		
		for (int i = S.length; i >= 0; --i) {
			
			getSubSets(S, i);
		}
		
		for (int i = 0; i < illist.size(); ++i) {
			
			Collections.sort(illist.get(i));
		}
		Collections.reverse(illist);
		return illist;
    }
	
	private void getSubSets(int[] S, int num) {
		
		List<Integer> list = new ArrayList<Integer>();
		getSubSets(S, 0, num, list);
	}
	
	private void getSubSets(int[] S, int start, int num, List<Integer> list) {
		
		if (num == 0) {
			
			illist.add(new LinkedList<Integer>(list));
			return;
		}
		
		for (int i = start; i < S.length; ++i) {
			
			list.add(S[i]);
			getSubSets(S, i + 1, num - 1, list);
			list.remove(list.size() - 1);
		}
	}
	
	/*
	 * Subsets II
	 * 
	 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
	 * Note:
	 * Elements in a subset must be in non-descending order.
	 * The solution set must not contain duplicate subsets.
	 * For example,
	 * If S = [1,2,2], a solution is:
			[
			  [2],
			  [1],
			  [1,2,2],
			  [2,2],
			  [1,2],
			  []
			]
	 * 
	 * ACCEPTED
	 */
	public List<List<Integer>> subsetsWithDup(int[] num) {
        
		illist = new LinkedList<List<Integer>>();
		
		LinkedList<List<Integer>> myllist = new LinkedList<List<Integer>>();
		
		for (int i = num.length; i >= 0; --i) {
			
			getSubSets(num, i);
		}
		
		for (int i = 0; i < illist.size(); ++i) {
			
			List<Integer> list = illist.get(i);
			Collections.sort(list);
			if (!myllist.contains(list)) {
				
				myllist.add(list);
			}
		}
		Collections.reverse(myllist);
		return myllist;
    }
	
	/*
	 * String to Integer (atoi) 
	 * 
	 * Implement atoi to convert a string to an integer.
	 * Hint: Carefully consider all possible input cases. If you want a challenge, 
	 * please do not see below and ask yourself what are the possible input cases.
	 * 
	 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
	 * You are responsible to gather all the input requirements up front.
	 * 
	 * spoilers alert... click to show requirements for atoi.
	 * 
	 * Requirements for atoi:
	 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
	 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
	 * and interprets them as a numerical value.
	 * The string can contain additional characters after those that form the integral number, 
	 * which are ignored and have no effect on the behavior of this function.
	 * If the first sequence of non-whitespace characters in str is not a valid integral number, 
	 * or if no such sequence exists because either str is empty or it contains only whitespace characters, 
	 * no conversion is performed.
	 * If no valid conversion could be performed, a zero value is returned. 
	 * If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
	 * 
	 * ACCEPTED
	 */
	public int atoi(String str) {
		
		if (str == null) return 0;
		str = str.trim();
		if (str.length() == 0) return 0;
		
		long result = 0;
		boolean isNegative = false;
		
		int i = 0;
		char c = str.charAt(i);
		
		if (c == '-' || c == '+') {
			
			isNegative = (c == '-');
			i++;
		}
		while (i < str.length()) {
			
			c = str.charAt(i++);
			if (c < '0' || c > '9') break;
			
			result = result * 10 + (c - '0');
			
			if (!isNegative && result >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
	        if (isNegative && (-1) * result <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
		}
		
		if (isNegative) {
			
			result *= -1;
		}
		
        return (int)result;
    }
	
	/*
	 * Palindrome Number
	 * 
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 * 
	 * click to show spoilers.
	 * 
	 * Some hints:
	 * Could negative integers be palindromes? (ie, -1)
	 * If you are thinking of converting the integer to string, note the restriction of using extra space.
	 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
	 * you know that the reversed integer might overflow. How would you handle such case?
	 * There is a more generic way of solving this problem.
	 * 
	 * Wrong Answer
	 * Input:	1000021
	 * Output:	true
	 * Expected:false
	 * 
	 * Input:	0
	 * Output:	false
	 * Expected:true
	 * 
	 * Input:	1001
	 * Output:	false
	 * Expected:true
	 * 
	 * ACCEPTED
	 */
	public boolean isPalindrome(int x) {
        
		if (x < 0) return false;
		if (x == 0) return true;  // 0
		
		int length = getLengthOfInt(x);
		int factor = getFactor(length);
		
		while (x > 9) {  // 12321 -> 232 -> 3
			             // 1221 -> 22 -> 0
			
			int a = x / factor;
			int b = x % 10;
			if (a != b) return false;
			
			x = (x - a * factor - b) / 10;
			factor = factor / 100;
		}
		if (x == 0) return true;  // 1001 -> 00
		if (factor != 1 && factor != 0) return false;  // 1000021 -> 00002
		
		return true;
    }
	
	private int getLengthOfInt(int x) {
		
		if (x == 0) return 1;
		
		int length = 0;
		int num = x;
		
		while (num > 0) {
			
			length++;
			num = num / 10;
		}
		
		return length;
	}
	
	private int getFactor(int length) {
		
		int factor = 1;
		
		for (int i = 0; i < length - 1; ++i) {
			
			factor *= 10;
		}
		
		return factor;
	}
	
	/*
	 * Distinct Subsequences
	 * 
	 * Given a string S and a string T, count the number of distinct subsequences of T in S.
	 * A subsequence of a string is a new string which is formed from the original string 
	 * by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
	 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	 * 
	 * Here is an example:
	 * S = "rabbbit", T = "rabbit"
	 * Return 3.
	 * 
	 * ACCEPTED
	 * 
	 */
	public int numDistinct(String S, String T) {
        
		// length of S >= length of T
		int[][] dp = new int[S.length() + 1][T.length() + 1];
		for (int j = 0; j <= T.length(); ++j) {
			
			dp[0][j] = 0;
		}
		
		for (int i = 0; i <= S.length(); ++i) {
			
			dp[i][0] = 1;
		}
		
		for (int i = 0; i < S.length(); ++i) {
			
			for (int j = 0; j < T.length(); ++j) {
				
				if (S.charAt(i) == T.charAt(j)) {
					
					dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
				}
				else {
					
					dp[i + 1][j + 1] = dp[i][j + 1];
				}
			}
		}
		
		
		return dp[S.length()][T.length()];
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Generate Parentheses
	 * 
	 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	 * For example, given n = 3, a solution set is:
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 * 
	 */
	private List<String> list = null;
	public List<String> generateParenthesis(int n) {
        
		list = new ArrayList<String>();
		
		
		
		return list;
    }
	
	
	
	
	
	
	
	
	/*
	 * Permutations II
	 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
	 * 
	 * For example,
	 * [1,1,2] have the following unique permutations:
	 * [1,1,2], [1,2,1], and [2,1,1].
	 * 
	 * 
	 */
	List<List<Integer>> permuteUniqueList = null;
	public List<List<Integer>> permuteUnique(int[] num) {
        
		permuteUniqueList = new ArrayList<List<Integer>>();
		
		if (num == null || num.length == 0) return llist;
		
		
		
		return permuteUniqueList;
    }
	
	private void getPermutationUnique(List<Integer> range, List<Integer> list) {
		
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Maximum Subarray
	 * 
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
	 * For example, given the array [鈭�2,1,鈭�3,4,鈭�1,2,1,鈭�5,4],
	 * the contiguous subarray [4,鈭�1,2,1] has the largest sum = 6.
	 * 
	 * click to show more practice.
	 * 
	 * More practice:
	 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
	 * 
	 * divide and conquer --> 鍒嗘不娉�
	 * Time Limit Exceeded
	 * 
	 * 
	*/
	/*
	 * Time Limit Exceeded
	public int maxSubArray(int[] A) {
        
		int maxSum = Integer.MIN_VALUE;
		
		for (int i = 0; i < A.length - 1; ++i) {
			
			int sum = A[i];
			for (int j = i + 1; j < A.length; ++j) {
				
				sum += A[j];
				if (sum > maxSum) maxSum = sum;
			}
		}
		
		return maxSum;
    }
    */
	public int maxSubArray(int[] A) {
        
		int maxSum = Integer.MIN_VALUE;
		
		
		return maxSum;
    }
	
	
	
	
	
	
	
	
	
	/*
	 * Balanced Binary Tree
	 * 
	 * Given a binary tree, determine if it is height-balanced.
	 * For this problem, a height-balanced binary tree is defined as a binary tree 
	 * in which the depth of the two subtrees of every node never differ by more than 1.
	 */
	
	public boolean isBalanced(TreeNode root) {
		
		
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        int delta = Math.abs(leftHeight - rightHeight);
        
        return delta <= 1;
        
    }
	
	private int getTreeHeight(TreeNode node) {
		
		
		return 0;
	}
}
