package com.ivanliu.jeetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



import java.util.Set;

import com.ivanliu.jeetcode.Solution.ListNode;
import com.ivanliu.jeetcode.Solution.TreeNode;

public class MyMain {

	public static void main(String[] args) {
		
		runJettCode();
		//runInterview();

	}
	
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
		Solution.printListNode(list);
		ListNode head = sol.sortList(list);
		Solution.printListNode(head);
		
		list = buildListNode(new int[] {10,3,7,21,4,7,3,4,0});
		head = sol.insertionSortList(list);
		Solution.printListNode(head);
		
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
		Solution.printListNode(list2);
		
		int[] gas = new int[] {2,4};
		int[] cost = new int[] {3,4};
		System.out.println(sol.canCompleteCircuit(gas, cost));
		
		// Palindrome Partitioning
		String input2 = "aaaab";
		List<List<String>> resList = sol.partition(input2);
		Solution.printListII(resList);
		
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
