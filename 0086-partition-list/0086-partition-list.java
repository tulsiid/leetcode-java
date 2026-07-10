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
    public ListNode partition(ListNode head, int x) {
        ListNode smalldummy=new ListNode(0);
        ListNode largedummy=new ListNode(0);
        ListNode small=smalldummy;
        ListNode large=largedummy;
        ListNode curr=head;
        while(curr!=null)
        {
            if(curr.val<x)
            {
                small.next=curr;
                small=small.next;
            }
            else
            {
                large.next=curr;
                large=large.next;
            }
            curr=curr.next;
        }
        small.next=largedummy.next;
        large.next=null;
        return smalldummy.next;

        
    }
}