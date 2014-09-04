package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;

public class Solution {

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
	
	static class ListNode {
		
		 int val;
		 ListNode next;
		 ListNode(int x) {
			 val = x;
			 next = null;
		}
	}
	
	public static void printListNode(ListNode head) {
		
		ListNode p = head;
		while (p != null) {
			
			System.out.print(p.val + " ");
			p = p.next;
		}
		System.out.println();
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
	 * Given a singly linked list L: L0��L1������Ln-1��Ln,
	 * reorder it to: L0��Ln��L1��Ln-1��L2��Ln-2����
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
	
	public static void printList(List<String> list) {
		
		System.out.print("[");
		int i = 0;
		for (; i < list.size() - 1; ++i) {
			
			System.out.print(list.get(i) + ",");
		}
		System.out.println(list.get(i) + "]");
	}
	
	public static void printListII(List<List<String>> llist) {
		
		System.out.println("[");
		int i = 0;
		for (; i < llist.size(); ++i ) {
			
			List<String> list = llist.get(i);
			printList(list);
		}
		System.out.println("]");
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
	/* ��ʱ��
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
	
	public void printListIntegerI(List<Integer> list) {
		
		for (int i = 0; i < list.size(); ++i) {
			
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
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
	
	public void printTreeLinkNode(TreeLinkNode root) {
		
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
	
}
