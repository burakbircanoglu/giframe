package com.giframe;

import java.util.Random;


public class LinkedBag<T> implements BagInterface <T>{
private Node firstNode;
private int numberOfEntries;
Random randomGenerator;

public LinkedBag(T[] items, int numberOfItems) {
	this();
	for (int i = 0; i < numberOfItems; i++) {
		add(items[i]);
	}
	this.firstNode = null;
	this.numberOfEntries = numberOfItems;
}

public LinkedBag() {
	firstNode=null;
	numberOfEntries=0;

}



public class Node {
	private T data;
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	private Node next;
	
	private  Node(T dataPortion){
		this(dataPortion,null);
	}

	private Node(T dataPortion, Node nextNode) {
		data=dataPortion;
		next=nextNode;
	}

	
}

public boolean isFull() {
	
	return false;
}


public Node getFirstNode() {
	return firstNode;
}

public void setFirstNode(Node firstNode) {
	this.firstNode = firstNode;
}

public int getNumberOfEntries() {
	return numberOfEntries;
}

public void setNumberOfEntries(int numberOfEntries) {
	this.numberOfEntries = numberOfEntries;
}

public boolean isEmpty() {
	
	return true;
}

public boolean add(T newEntry){
	Node newNode=new Node(newEntry);
	newNode.next=firstNode;
	firstNode=newNode;
	numberOfEntries++;
	return true;
}

@Override
public int getCurrentSize() {
	
	return numberOfEntries;
}

@Override
public T remove() {
	randomGenerator=new Random();
	int index=randomGenerator.nextInt(numberOfEntries)+1;
	Node holderNode=firstNode;
	
	for (int i = 1; i <= index; i++) {
		holderNode=firstNode.next;
	}
	
	holderNode=firstNode;
	firstNode=firstNode.next;
	numberOfEntries--;
	return holderNode.data;
}

@Override
public boolean remove(T anEntry) {
	 if(getFrequencyOf(anEntry)==0)
	return false;
	 else{
		 Node holder=getReferenceTo(anEntry);
		 holder.data=firstNode.data;
		 firstNode=firstNode.next;
		 numberOfEntries--;
		 
		 return true;
	 }
}

@Override
public void clear() {
	while(numberOfEntries>0){
		remove();
		
	}
	
}

@Override
public int getFrequencyOf(T anEntry) {
	int freq=0;
	Node holder=firstNode;
	for (int i = 0; i <numberOfEntries; i++) {
		if(holder.data.equals(anEntry)){
			freq++;
		}
		holder=holder.next;
	}
	return freq;
}

@Override
public boolean contains(T anEntry) {
	
	return getFrequencyOf(anEntry)>0;
}

public T[] toArray() {
    @SuppressWarnings("Unchecked")
    T[] HolderArray=(T[]) new Object[numberOfEntries];
    Node holder=firstNode;
    
    for (int i = 0; i < numberOfEntries; i++) {
		HolderArray[i]=holder.data;
		holder=holder.next;
	}
    
    
    return HolderArray;
} // end toArray

@Override
public boolean equals(LinkedBag<T> aBag) {
	Node holder=aBag.firstNode;
	for (int i = 0; i < numberOfEntries; i++) {
		if(getFrequencyOf(holder.data)!=aBag.getFrequencyOf(holder.data))
			return false;
			
		holder=holder.next;
		
	}
	
	
	return true;
}

private Node getReferenceTo(T anEntry){
	Node holder=firstNode;
	for (int i = 0; i <numberOfEntries; i++) {
		if(holder.data==anEntry)
			return holder;
		holder=holder.next;
	}
	return null;
}


@Override
public boolean removeDuplicates(){
	Node holder=firstNode;
	
	for (int i = 0; i < numberOfEntries; i++) {
		int a=getFrequencyOf(holder.data);
		System.out.println(a);
		while(a>1){
			remove(holder.data);
			a--;
	}
	holder=holder.next;
	}
	
	
	return true;
}

public boolean appearance(T newEntry){
	
	return true;
}
@Override
public boolean duplicateAll() {
	Node holder=firstNode;
	@SuppressWarnings("Unchecked")
	T[] duplicates=(T[]) new Object[numberOfEntries];
	
	for (int i = 0; i <numberOfEntries; i++) {
		
		
		if(getFrequencyOf(holder.data)>1){
			
			
		}
	}
	
	
	
	
	
	return false;
}	
	
	
	
}
