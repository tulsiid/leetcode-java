/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode prevNode = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;

        int minDist = Integer.MAX_VALUE;

        while (curr.next != null) {

            // Is curr a critical point?
            boolean isCritical =
                (curr.val > prevNode.val && curr.val > curr.next.val) ||
                (curr.val < prevNode.val && curr.val < curr.next.val);

            if (isCritical) {

                if (first == -1) {
                    // First critical point
                    first = index;
                } else {

                    // Distance from previous critical point
                    minDist = Math.min(minDist, index - last);
                }

                // Current critical point becomes the latest one
                last = index;
            }

            prevNode = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        // Distance between first and last critical points
        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}