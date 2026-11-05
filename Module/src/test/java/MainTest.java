import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Spot spotA = new Spot("name", 0, 0);

    @Test
    void testSpot(){
        assertEquals(spotA, new Spot("name", 0, 0));
    }

    @Test
    void testCompareTo(){
        assertEquals(-1, spotA.compareTo(new Spot("less", -90, 0)));
        assertEquals(0, spotA.compareTo(new Spot("equl", 0, 0)));
        assertEquals(0, spotA.compareTo(new Spot("equl", 0, 30)));
        assertEquals(1, spotA.compareTo(new Spot("more", 90, 0)));
        assertEquals(0, spotA.compareTo(null));
    }

    BST<Spot> treeA = new BST<>(spotA, null, null);
    @Test
    void testBSTInsert(){
        Spot spotLeft = new Spot("left", -30, 0);
        treeA = BST.insert(treeA, spotLeft);
        assertEquals(new BST<Spot>(spotA,
                new BST<>(spotLeft, null, null),
                null),
                treeA);

        Spot spotRight = new Spot("rite", 30, 0);
        treeA = BST.insert(treeA, spotRight);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft, null, null),
                        new BST<>(spotRight, null, null)),
                treeA);

        Spot spotLeft2 = new Spot("left", -15,0);
        treeA = BST.insert(treeA, spotLeft2);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft, null, new BST<>(spotLeft2, null, null)),
                        new BST<>(spotRight, null, null)),
                treeA);

        Spot spotRight2 = new Spot("rite", 40, 0);
        treeA = BST.insert(treeA, spotRight2);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft, null, new BST<>(spotLeft2, null, null)),
                        new BST<>(spotRight, null, new BST<>(spotRight2, null, null))),
                treeA);

        Spot spotHead = new Spot("head", 0,0);
        treeA = BST.insert(treeA, spotHead);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft,
                                null,
                                new BST<>(spotLeft2, null, new BST<>(spotHead, null,null))),
                        new BST<>(spotRight,
                                null,
                                new BST<>(spotRight2, null, null))),
                treeA);

        Spot spotNull = null;
        treeA = BST.insert(treeA, spotNull);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft, null, new BST<>(spotLeft2, null, new BST<>(spotHead, null,null))),
                        new BST<>(spotRight, null, new BST<>(spotRight2, null, null))),
                treeA);


    }

    @Test
    void testBSTDelete(){

        Spot spotLeft = new Spot("left", -30, 0);
        Spot spotRight = new Spot("rite", 30, 0);
        Spot spotLeft2 = new Spot("left", -15,0);
        Spot spotRight2 = new Spot("rite", 40, 0);
        Spot spotHead = new Spot("head", 0,0);
        BST<Spot> delTree = new BST<Spot>(spotA,
                new BST<>(spotLeft,
                        null,
                        new BST<>(spotLeft2,
                                null, new BST<>(spotHead, null,null))),
                new BST<>(spotRight,
                        null,
                        new BST<>(spotRight2,
                                null, null)));

        delTree = BST.delete(delTree, spotHead);
        assertEquals(new BST<Spot>(spotA,
                        new BST<>(spotLeft, null, new BST<>(spotLeft2, null, null)),
                        new BST<>(spotRight, null, new BST<>(spotRight2, null, null))),
                delTree);
    }

    @Test
    void testBSTSearch(){

        Spot spotLeft = new Spot("left", -30, 0);
        Spot spotRight = new Spot("rite", 30, 0);
        Spot spotLeft2 = new Spot("left", -15,0);
        Spot spotRight2 = new Spot("rite", 40, 0);
        Spot spotHead = new Spot("head", 0,0);
        BST<Spot> searchTree = new BST<Spot>(spotA,
                new BST<>(spotLeft,
                        null,
                        new BST<>(spotLeft2,
                                null, new BST<>(spotHead, null,null))),
                new BST<>(spotRight,
                        null,
                        new BST<>(spotRight2,
                                null, null)));

        Spot leftCheck = BST.search(searchTree, spotLeft);
        Spot rightCheck = BST.search(searchTree, spotRight);
        Spot headCheck = BST.search(searchTree, spotHead);
        Spot spotNull = null;
        Spot nullCheck = BST.search(searchTree,spotNull);

        assertEquals(spotLeft, leftCheck);
        assertEquals(spotRight, rightCheck);
        assertEquals(spotHead, headCheck);
        assertEquals(spotNull, nullCheck);


    }

    @Test
    void testBSTTime(){

        int[] sizes = {1000, 100000, 1000000, 10000000, 50000000};

        for(int size : sizes){

            IO.println(size);

            Spot randomSpot = Spot.randomSpot();
            BST<Spot> initTree = BST.makeTree(size);

            long startTime = System.nanoTime();
            BST<Spot> testTree = BST.insert(initTree, randomSpot);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1000.0;

            IO.println(size + ": " + duration + " microseconds");

        }
    }

}
