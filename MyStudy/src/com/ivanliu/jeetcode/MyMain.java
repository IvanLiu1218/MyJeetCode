package com.ivanliu.jeetcode;

import java.util.List;


import com.ivanliu.jeetcode.Solution.TreeNode;

public class MyMain {

	public static void main(String[] args) {
		
		//runJettCode();
		runInterview();

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
		
		iv.printMatrix(array3, 7, 5);
		
	}
	
	

}
