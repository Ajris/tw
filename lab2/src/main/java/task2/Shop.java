package task2;

import java.util.ArrayList;
import java.util.List;

class Shop {
    private Semaphore semaphore;
    private BinarySemaphore binarySemaphore;
    private List<Bucket> list;

    Shop(int bucket) {
        this.binarySemaphore = new BinarySemaphore();
        this.list = setList(bucket);
        this.semaphore = new Semaphore(bucket);
    }

    private List<Bucket> setList(int buckets){
        List<Bucket> tmp = new ArrayList<>();
        for(int i = 0; i < buckets; i++){
            tmp.add(new Bucket(i));
        }
        return tmp;
    }

    Bucket takeBucket() {
        semaphore.p();
        binarySemaphore.takeSemaphore();
        Bucket b = list.get(list.size() - 1);
        binarySemaphore.releaseSemaphore();
        return b;
    }

    void loseBucket(Bucket bucket) {
        binarySemaphore.takeSemaphore();
        list.add(bucket);
        binarySemaphore.releaseSemaphore();
        semaphore.v();
    }
}
