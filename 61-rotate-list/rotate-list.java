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

        if(head == null || head.next == null || k == 0)return head;

        //Find length of list
        ListNode temp = head;
        int n = 1;

        while(temp.next != null){
            temp = temp.next;
            n++;
        }

        k = k % n;
        if(k == 0) return head;


        ListNode fast = head;

        for(int i = 0; i < k; i++){
            fast = fast.next;
        }

        ListNode slow = head;

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
        
    }
}