package com.ivanliu.jeetcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interview {

	/*
	 * 洗牌-法一：
	 */
	public void shuffle1(int[] cards) {
		
		int length = cards.length;
		for (int i = 0; i < length; ++i) {
			
			Random r = new Random();
			int value = r.nextInt(Integer.MAX_VALUE) % length;
			
			int temp = cards[i];
			cards[i] = cards[value];
			cards[value] = temp;
		}
	}
	
	/*
	 * 洗牌 - 法二：
	 */
	public void shuffle2(int[] cards) {
		
		int length = cards.length;
		for (int i = 0; i < length; ++i) {
			
			Random r = new Random();
			int value = i + r.nextInt(Integer.MAX_VALUE) % (length - i);
			
			int temp = cards[i];
			cards[i] = cards[value];
			cards[value] = temp;
		}
	}
	
	/*
	 * N + NN + NNN + NNNN + .... 
	 */
	public int getSUM(int N, int M) {
		
		int p = 1;
		int i = M;
		int sum = 0;
		while (i != 0) {
			
			sum += N * i * p;
			p = p * 10;
			i--;
		}
		
		return sum;
	}
	
	/*
	 * 
	 */
	class Interval {
		
		public int left;
		public int right;
		
		public Interval(String pair) {
			
			Pattern pa = Pattern.compile("\\[(\\-?[^0][0-9]*),(\\-?[^0][0-9]*)\\]");
			Matcher mc = pa.matcher(pair);
			if (mc.find()) {
				
				left = Integer.parseInt(mc.group(1));
				right = Integer.parseInt(mc.group(2));
			}
		}
		
		public Interval(int left, int right) {
			
			this.left = left;
			this.right = right;
		}
		
		public String toString() {
			
			return String.format("[%d,%d]", left, right);
		}
		
	}
	
	class IntervalList {
		
		private List<Interval> intervals = new ArrayList<Interval>();
		private int min = Integer.MIN_VALUE;
		private int max = Integer.MAX_VALUE;
		
		public void add(Interval it) {
			
			
			if (intervals.size() == 0) {
				
				min = it.left;
				max = it.right;
				intervals.add(it);
				return;
			}
			intervals.add(it);
			min = Math.min(it.left, min);
			max = Math.max(it.right, max);
			
		}
		
		public void unite() {
			
			int size = max - min + 1;
			int[] array = new int[size];
			
			for (int i = 0; i < intervals.size(); ++i) {
				
				Interval interval = intervals.get(i);
				for (int j = interval.left; j <= interval.right; ++j) {
					
					array[j - min] += 1;
				}
			}
			
			intervals.clear();
			boolean isNewInterval = false;
			int start = min;
			int end = max;
			int i = 0;
			for (; i < size; ++i) {
				
				if (isNewInterval)  {
					
					if (array[i] == 0) {
						
						end = i + min - 1;
						Interval interval = new Interval(start, end);
						intervals.add(interval);
						isNewInterval = false;
					}
				}
				else {
					
					if (array[i] != 0) {
						
						start = i + min;
						isNewInterval = true;
					}
				}
			}
			if (i >= size && isNewInterval == true) {
				
				end = i + min - 1;
				Interval interval = new Interval(start, end);
				intervals.add(interval);
				isNewInterval = false;
			}
		}
		
		public void intersect() {
			
			int size = max - min + 1;
			int[] array = new int[size];
			
			int hits = 2;
			
			for (int i = 0; i < intervals.size(); ++i) {
				
				Interval interval = intervals.get(i);
				for (int j = interval.left; j <= interval.right; ++j) {
					
					array[j - min] += 1;
				}
			}
			
			intervals.clear();
			boolean isNewInterval = false;
			int start = min;
			int end = max;
			int i = 0;
			for (; i < size; ++i) {
				
				if (isNewInterval)  {
					
					if (array[i] != hits) {
						
						end = i + min - 1;
						Interval interval = new Interval(start, end);
						intervals.add(interval);
						isNewInterval = false;
					}
				}
				else {
					
					if (array[i] == hits) {
						
						start = i + min;
						isNewInterval = true;
					}
				}
			}
			if (i >= size && isNewInterval == true) {
				
				end = i + min - 1;
				Interval interval = new Interval(start, end);
				intervals.add(interval);
				isNewInterval = false;
			}
		}
		
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			int size = intervals.size();
			for (int i = 0; i < size; ++i) {
				
				sb.append(intervals.get(i).toString());
				if (i != size - 1) {
					
					sb.append(" ");
				}
			}
			
			return sb.toString();
		}
	}
	
	public String mergeIntervals(String input) {
		
		String[] inputs = input.split(" ");
		
		List<Interval> intervals = new ArrayList<Interval>();
		//int min = Integer.MIN_VALUE;
		//int max = Integer.MAX_VALUE;
		for (int i = 0; i < inputs.length; ++i) {
			
			intervals.add(new Interval(inputs[i]));
		}
		
		if (intervals.size() < 2) return "invalid";
		
		Interval it1 = intervals.get(0);
		IntervalList itl = new IntervalList();
		for (int i = 1; i < intervals.size(); ++i) {
			
			itl.add(intervals.get(i));
		}
		itl.unite();
		
		itl.add(it1);
		itl.intersect();
		
		//it1.intersect(it2);
		return itl.toString();
	}
	
	/*
	 * anavebaron
	 * {a,an,ave,bar,baron,nave,on}
	 */
	private int result = 0;
	
	public int matchDictionary(String text, String dic) {
		
		result = 0;
		//String[] dic = new String[] {"a","an","ave","bar","baron","nave","on"};
		Hashtable<String, Integer> dictionary = new Hashtable<String, Integer>();
		dic = dic.substring(1, dic.length() - 1);
		String[] dics = dic.split(",");
		for (int i = 0; i < dics.length; ++i) {
			
			dictionary.put(dics[i], 0);
		}
		
		searchDictionary(text, dictionary);
		return result;
	}
	
	public void searchDictionary(String text, Hashtable<String, Integer> dictionary) {
		
		String[] dic = dictionary.keySet().toArray(new String[]{});
		
		if (text.length() == 0) {
			
			result++;
			return;
		}
		for (int i = 0; i < dic.length; ++i) {
			
			String prefix = dic[i];
			if (text.startsWith(prefix)) {
				
				String subtext = text.substring(prefix.length());
				searchDictionary(subtext, dictionary);
			}
		}
	}
	/*
	 * input: {1,7,12,47,161,0,9}
	 * output: 97471611210
	 */
	
	public String getMaxNumber(String input) {
		
		String numbers = input.substring(1, input.length() - 1);
		String[] nums = numbers.split(",");
		ArrayList<String> numArray = new ArrayList<String>();
		for (int i = 0; i < nums.length; ++i) {
			
			numArray.add(nums[i]);
		}
		StringBuilder sb = new StringBuilder();
		
		while (numArray.size() != 0) {
			
			String max = "0";
			int index = 0;
			for (int i = 0; i < numArray.size(); ++i) {
				
				String digit = numArray.get(i);
				if (digit.compareTo(max) > 0) {
					
					
					max = digit;
					index = i;
				}
			}
			sb.append(numArray.get(index));
			numArray.remove(index);
		}
		
		return sb.toString();
	}
	
	
	/*
	 * 全排列
	 */
	public ArrayList<String> resultArray = new ArrayList<String>();
	
	public String[] getFullCombination(String input) {
		
		String[] inputs = input.split(",");
		String res = "";
		for (int i = 0; i < inputs.length; ++i) {
			
			getCC(inputs, i, res);
		}
		
		return resultArray.toArray(new String[]{});
		
	}
	
	public void getCC(String[] list, int index, String res) {
		
		if (list.length == 1) {
			
			res += list[0];
			resultArray.add(res);
			return;
		}
		
		res += list[index];
		
		int size = list.length - 1;
		String[] nlist = new String[size];
		int j = 0;
		for (int i = 0; i < list.length; ++i) {
			
			if (i != index) {
				
				nlist[j++] = list[i]; 
			}
		}
		
		for (j = 0; j < nlist.length; ++j) {
			
			getCC(nlist, j, res);
		}
		
	}
	
	/*
	 * 顺时针遍历举证
	 */
	public void printMatrix(int[][] array, int width, int height) {
		
		int times = 0;
		int endWidth = width/2 + 1;
		int endHeight = height/2 + 1;
		int endTimes = Math.min(endWidth, endHeight);
		
		while (times < endTimes) {
			
			int r = times;
			int l = times;
			for (; l < width - times; ++l) {
				
				System.out.print(array[r][l] + " ");
			}
			l--;
			r++;
			
			for (; r < height - times; ++r) {
				
				System.out.print(array[r][l] + " ");
			}
			r--;
			l--;
			
			for (; l >= times; --l) {
				
				System.out.print(array[r][l] + " ");
			}
			l++;
			r--;
				
			for (; r > times; --r) {
				
				System.out.print(array[r][l] + " ");
			}
			
			times++;
		}
	}
}
