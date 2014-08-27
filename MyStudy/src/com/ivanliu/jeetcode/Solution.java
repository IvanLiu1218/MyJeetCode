package com.ivanliu.jeetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
	 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
	 * reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
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
	
}
