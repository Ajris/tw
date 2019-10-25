package task2;

class PersonPair {
    private Person p1;
    private Person p2;

    PersonPair(int num, String p1, String p2) {
        this.p1 = new Person(num, p1);
        this.p2 = new Person(num, p2);
    }

    Person getP1() {
        return p1;
    }

    Person getP2() {
        return p2;
    }
}
