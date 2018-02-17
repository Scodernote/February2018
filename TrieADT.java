import java.util.Arrays;
import java.util.Random;

public class TrieADT {
	
	public static final int SIZE = 26;
	
	public static class Node {
		Node  [] children;
		boolean isEndOfWord;
		Node(){
			children = new Node[SIZE];
			isEndOfWord = false;
			for(int  i =0 ; i < SIZE ;i++){
				children[i] = null;
			}
		}
	}
	
	Node root = new Node();
	
	public void insert(String key){
		Node pCrawl = root;
		int index;
		
		for(int  i = 0 ; i < key.length() ;i++){
			index = key.charAt(i) - 'a';
			
			if(pCrawl.children[index] == null){
				pCrawl.children[index] = new Node();
			}
			pCrawl = pCrawl.children[index];
		}
		
		pCrawl.isEndOfWord = true;
	}
	
	public boolean search(String key){
		Node pCrawl = root;
		int index;
		
		for(int  i = 0 ; i <key.length() ;i++){
			index = key.charAt(i) - 'a';
			if(pCrawl.children[index] == null){
				return false;
			}
			pCrawl = pCrawl.children[index];
		}
		return pCrawl !=null && pCrawl.isEndOfWord;
	}
	
	
	public void display(){
		char [] keyArr = new char[30];
		displayHelper(root,keyArr,0);
	}
	
	public void displayHelper(Node pCrawl , char [] keyArr , int level){
		
		if(pCrawl.isEndOfWord){
			for(int i = 0 ; i < level ;i++){
				System.out.print(keyArr[i]);
			}
			System.out.println();
		}
		
		for(int  i  = 0 ; i < SIZE ;i++){
			if(pCrawl.children[i] != null){
				keyArr[level] = (char) (i + 'a');
				displayHelper(pCrawl.children[i],keyArr,level+1);
			}
		}
		
	}
		
	
	public boolean isFreeNode(Node pCrawl){
		
		for(int  i = 0; i< SIZE ;i++){
			if(pCrawl.children[i] != null){
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean deleteHelper(Node pCrawl , String word , int level){
		
		if(pCrawl != null){
			
			if(level == word.length()){
				if(pCrawl.isEndOfWord){
					pCrawl.isEndOfWord = false;
					if(isFreeNode(pCrawl)){
						return true;
					}
				}
				return false;
			} else {
				int index = word.charAt(level) -'a';
				if(deleteHelper(pCrawl.children[index],word,level+1)){
					pCrawl.children[index] = null;
					return !pCrawl.isEndOfWord && isFreeNode(pCrawl);
				}
			}
		}
		return false;
	}
	
	public void delete(String key){
		deleteHelper(root,key,0);
	}
	
	public static void main(String [] args){
		String [] arr = new String[10];
		Random rand = new Random(70);
		for(int  i  = 0 ;i < 10 ;i++){
			arr[i] = RandomStringGenerator.randomAlphaNumeric(3);
		}
		
		//System.out.println(Arrays.toString(arr));
		
		TrieADT trie = new TrieADT();
		
		for(int  i  = 0 ; i < 10 ; i++){
			trie.insert(arr[i]);
		}
		
		trie.display();
		String tp = arr[rand.nextInt(10)];
		System.out.println(tp);
		System.out.println(trie.search(tp));
		
		trie.delete(tp);
		
		trie.display();
	}
}