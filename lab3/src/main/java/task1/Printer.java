package task1;

class Printer {
    private int id;

    Printer(int id) {
        this.id = id;
    }

    void print(){
        System.out.println("PRINTING TEXT ID: " + id);
    }
}
