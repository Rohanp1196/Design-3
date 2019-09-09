class Node{
    
    int key;
    int value;
    
    Node next;
    Node prev;
    
    public Node(int key, int value){
        
        this.key=key;
        this.value=value;
    }
    
    
}

class LRUCache {

    int capacity;
    int count =0;   
    
    Node head;
    Node tail;
    
    HashMap<Integer, Node> map;
        
    public LRUCache(int capacity) {
        
        map = new HashMap<>();
        this.capacity=capacity;
        
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev= head;
        head.prev=null;
        tail.next=null;
        
    }
    
    public void removeNode(Node node){
        
        node.prev.next=node.next;
        node.next.prev=node.prev;
        
    }
    
    public void addToHead(Node node){
        
        node.next=head.next;
        head.next=node;
        node.prev=head;
        node.next.prev=node;
        
        
    }
    
    public int get(int key) {
        
        if(map.containsKey(key)){
            
            Node temp = map.get(key);
            removeNode(temp);
            addToHead(temp);
            return temp.value;
        }
        return -1;   //If not present in hash map
        
    }
    
    public void put(int key, int value) {
        
        
        if(map.containsKey(key)){   //If already in hashmap
            
            Node temp = map.get(key);
            temp.value= value;
            removeNode(temp);
            addToHead(temp);
            
            
        }
        else{
            
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            
            //if capacity not full
            if(count<capacity){
                
                addToHead(newNode);
                count++;
            }
            else{
                //capacity is full
                
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                addToHead(newNode);
                map.remove(tailPrev.key);
            }
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */