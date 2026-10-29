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

    }

}
