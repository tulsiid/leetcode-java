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
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null)
        {
            return head;
        }
        ListNode curr=head;
        int len=1;
        while(curr.next!=null)
        {
            curr=curr.next;
            len++;
        }
        k=k%len;
        if(k==0)
        {
            return head;
        }
        curr.next=head;
        ListNode newcurr=head;
        int steps=len-k;
        for(int i=1;i<steps;i++)
        {
            newcurr=newcurr.next;
        }
        ListNode newhead=newcurr.next;
        newcurr.next=null;
        return newhead;
        
    }
}