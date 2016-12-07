public class SmallestCommonNumber {

	public static final int EXISTS = 1; // because you hate System.exit(0) apparently 

	public static void main(String[] args) {

		// contains common numbers
		int[] nums1 = {1, 2, 4, 6, 7, 9, 13, 14, 17};
		int[] nums2 = {0, 3, 5, 8, 9, 11, 12, 14, 17, 18, 20};
		// doesn't contain common numbers
		// int[] nums1 = {2, 4, 6, 8, 10, 12, 14, 16, 18};
		// int[] nums2 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};

		if (bruteForce(nums1, nums2)[EXISTS] == EXISTS) 
			System.out.println("Brute force way: " + bruteForce(nums1, nums2)[0]);
		if (elegant(nums1, nums2)[EXISTS] == EXISTS)
			System.out.println("Elegant way: " + elegant(nums1, nums2)[0]);
		else
			System.out.println("There is no common number");
	}

	private static int[] bruteForce(int[] nums1, int[] nums2) {

		int smallest = nums2[nums2.length - 1];
		boolean exists = false;

		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {

				if (nums1[i] == nums2[j] && nums1[i] < smallest) {
					smallest = nums1[i];
					exists = true;
				}
			}
		}

		int[] ans = {smallest, exists ? EXISTS : 0};

		return ans;
	}

	private static int[] elegant(int[] nums1, int[] nums2) {

		int i = 0, j = 0;
		boolean exists = true;

		try {
			while (nums1[i] != nums2[j]) {
				if (nums1[i] > nums2[j])
					j++;
				else if (nums1[i] < nums2[j])
					i++;
			}
		} catch (Exception e) { 
			exists = false;
		}

		int[] ans = {exists ? nums1[i] : 0, exists ? EXISTS : 0};

		return ans;
	}
}