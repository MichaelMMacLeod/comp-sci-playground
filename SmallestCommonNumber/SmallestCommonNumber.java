public class SmallestCommonNumber {

	public static void main(String[] args) {

		int[] nums1 = {1, 2, 4, 6, 7, 9, 13, 14, 17};
		int[] nums2 = {0, 3, 5, 8, 9, 11, 12, 14, 17, 18, 20};

		System.out.println("The elegant way: " + elegant(nums1, nums2));
		System.out.println("The brute force way: " + bruteForce(nums1, nums2));
	}

	private static int bruteForce(int[] nums1, int[] nums2) {

		int smallest = nums2[nums2.length - 1];

		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {

				if (nums1[i] == nums2[j] && nums1[i] < smallest)
					smallest = nums1[i];
			}
		}
		return smallest;
	}

	private static int elegant(int[] nums1, int[] nums2) {

		int i = 0, j = 0;

		while (nums1[i] != nums2[j]) {

			if (nums1[i] > nums2[j])
				j++;
			else if (nums1[i] < nums2[j])
				i++;
		}

		return nums1[i];
	}
}