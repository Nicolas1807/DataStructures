/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.datastructures;

/**
 *
 * @author mikic
 */
public class DataStructures {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(7);
        pq.add(6);
        pq.add(4);
        pq.add(12);
        pq.add(1);
        pq.add(24);
        pq.add(25);
        pq.add(12);
        pq.add(23);
        
        System.out.println("Wyjmujemy");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        
        
        
        pq.add(8);
        pq.add(21);
        pq.add(3);
        pq.add(18);
       
        System.out.println("Wyjmujemy");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        
        pq.add(1);
        pq.add(4);
        pq.add(4);
        pq.add(11);
  

        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // Prints persons in order of age (ascending)
        }
    }
}
