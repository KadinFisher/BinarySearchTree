package tests;

import implementation.*;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Scanner;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Tests {
	
	@Test
	public void testingAdd01() throws TreeIsFullException {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 10);
		assertEquals(bst.toString(), "EMPTY TREE");
		bst.add(5, "a");
		bst.add(3, "a");
		bst.add(7, "a");
		bst.add(2, "a");
		bst.add(4, "a");
		bst.add(6, "a");
		bst.add(8, "a");
		String answer = bst.toString();
		String expectedAnswer = "{2:a}{3:a}{4:a}{5:a}{6:a}{7:a}{8:a}";
		assertEquals(expectedAnswer, answer);
		assertTrue(bst.getTreeSize() == 7);
	}
	
	@Test
	public void testingAdd02() throws TreeIsFullException {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 6);
		bst.add(5, "a");
		bst.add(3, "a");
		bst.add(7, "a");
		bst.add(2, "a");
		bst.add(4, "a");
		bst.add(6, "a");
		try {
			bst.add(8, "a");
			assertTrue(false);
		} catch(TreeIsFullException e) {
			assertTrue(true);
		}
	}
	
	// Testing to see if the minimum value is found.
	@Test
	public void testingGetMinimumKeyValue01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 10);
		try {
			KeyValuePair<Integer, String> exception = bst.getMinimumKeyValue();
			assertTrue(false);
		} catch (TreeIsEmptyException e) {
			assertTrue(true);
		}
		try {
			bst.add(5, "a");
			bst.add(3, "a");
			bst.add(7, "a");
			bst.add(2, "a");
			bst.add(4, "a");
			bst.add(6, "a");
			bst.add(8, "a");
			KeyValuePair<Integer, String> answer = bst.getMinimumKeyValue();
			KeyValuePair<Integer, String> expectedAnswer = new KeyValuePair<Integer, String>(2, "a");
			assertEquals(expectedAnswer.getKey(), answer.getKey());
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		} catch (TreeIsEmptyException e) {
			e.getStackTrace();
		}
	}
	
	@Test
	public void testingGetMaximumKeyValue01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 10);
		try {
			KeyValuePair<Integer, String> exception = bst.getMaximumKeyValue();
			assertTrue(false);
		} catch (TreeIsEmptyException e) {
			assertTrue(true);
		}
		try {
			bst.add(5, "a");
			bst.add(3, "a");
			bst.add(7, "a");
			bst.add(2, "a");
			bst.add(4, "a");
			bst.add(6, "a");
			bst.add(8, "a");
			KeyValuePair<Integer, String> answer = bst.getMaximumKeyValue();
			KeyValuePair<Integer, String> expectedAnswer = new KeyValuePair<Integer, String>(8, "a");
			assertEquals(expectedAnswer.getKey(), answer.getKey());
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		} catch (TreeIsEmptyException e) {
			e.getStackTrace();
		}
	}
	
	@Test
	public void testingFind01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = 
				new BinarySearchTree<Integer, String>(comparator, 10);
		assertNull(bst.find(5));
		try {
			bst.add(5, "a");
			bst.add(3, "a");
			bst.add(7, "a");
			bst.add(2, "a");
			bst.add(4, "a");
			bst.add(6, "a");
			bst.add(8, "a");
			KeyValuePair<Integer, String> answer = bst.find(4);
			KeyValuePair<Integer, String> expectedAnswer = new KeyValuePair<Integer, String>(4, "a");
			assertEquals(expectedAnswer.getKey(), answer.getKey());
			assertNull(bst.find(10));
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		}
	}
	
	// Find way to ensure testing that in-order traversing is occurring in the 
	// process method. Enjoy your date loser.
	@Test
	public void testingInorderProcess01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		Callback<Integer, String> callback = new Callback<Integer, String>() {
			@Override
			public void process(Integer key, String value) {
				key++;
				System.out.print(key + " ");
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 7);
		try {
			bst.add(5, "d");
			bst.add(3, "b");
			bst.add(7, "f");
			bst.add(2, "a");
			bst.add(4, "c");
			bst.add(6, "e");
			bst.add(8, "g");
		} catch (TreeIsFullException e) {
			
		}
		Scanner consoleReader = new Scanner(System.in);
		bst.processInorder(callback);
		System.out.println("\nType in the exact same thing as the line above "
				+ "and put a space at the end for the test to be valid.");
		String answer = consoleReader.nextLine();
		consoleReader.close();
		String expectedAnswer = "3 4 5 6 7 8 9 ";
		assertEquals(expectedAnswer, answer);
	}
	
	@Test
	public void testingSubtree01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 7);
		try {
			bst.subTree(3, 1);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			bst.subTree(null, 1);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		BinarySearchTree<Integer, String> answer1 = bst.subTree(3, 5);
		String expectedAnswer1 = "EMPTY TREE";
		assertEquals(expectedAnswer1, answer1.toString());
		try {
			bst.add(5, "a");
			bst.add(3, "a");
			bst.add(7, "a");
			bst.add(2, "a");
			bst.add(4, "a");
			bst.add(6, "a");
			bst.add(8, "a");
			BinarySearchTree<Integer, String> answer2 = bst.subTree(4, 8);
			String answer = answer2.toString();
			String expectedAnswer = "{4:a}{5:a}{6:a}{7:a}{8:a}";
			assertEquals(expectedAnswer, answer);
		} catch (TreeIsFullException e) {
			
		}
	}
	
	@Test
	public void testingGetLeavesValues01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 10);
		try {
			bst.add(5, "d");
			bst.add(3, "b");
			bst.add(7, "f");
			bst.add(2, "a");
			bst.add(4, "c");
			bst.add(6, "e");
			bst.add(8, "g");
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		}
		String answer = bst.getLeavesValues().toString();
		String expectedAnswer = "[a, c, e, g]";
		assertEquals(expectedAnswer, answer);
	}
	
	@Test
	public void testingDelete01() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer int1, Integer int2) {
				return int1.compareTo(int2);
			}
		};
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(comparator, 10);
		try {
			bst.add(5, "a");
			bst.add(3, "a");
			bst.add(7, "a");
			bst.add(2, "a");
			bst.add(4, "a");
			bst.add(6, "a");
			bst.add(8, "a");
			bst.add(9, "a");
			bst.add(1, "a");
			bst.delete(2);
			String answer1 = bst.toString();
			String expectedAnswer1 = "{1:a}{3:a}{4:a}{5:a}{6:a}{7:a}{8:a}{9:a}";
			assertEquals(expectedAnswer1, answer1);
			bst.delete(1);
			bst.delete(9);
			String answer2 = bst.toString();
			String expectedAnswer2 = "{3:a}{4:a}{5:a}{6:a}{7:a}{8:a}";
			bst.delete(5);
			assertEquals(expectedAnswer2, answer2);
			String answer3 = bst.toString();
			String expectedAnswer3 = "{3:a}{4:a}{6:a}{7:a}{8:a}";
			assertEquals(expectedAnswer3, answer3);
			assertEquals(bst.getTreeSize(), 5);
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		} catch (TreeIsEmptyException e) {
			e.printStackTrace();
		}
	}
}
