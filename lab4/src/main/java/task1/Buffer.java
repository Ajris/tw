package task1;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private List<BufferCell> bufferCells;

    Buffer(int size) {
        bufferCells = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            bufferCells.add(new BufferCell());
        }
    }

    int getResource(int resourceId, int id) throws InterruptedException {
        return this.bufferCells.get(resourceId).getResource(id);
    }

    void setResource(int resourceId, int newValue){
        this.bufferCells.get(resourceId).setValue(newValue);
    }

    void releaseResource(int resourceId, int id){
        this.bufferCells.get(resourceId).releaseResource(id);
    }

    int getBufferSize(){
        return bufferCells.size();
    }
}