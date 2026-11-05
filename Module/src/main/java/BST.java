public record BST<T extends Comparable<T>>(T elt, BST<T> left, BST<T> right){

    // Adds a new elt to BST & sorts elt into spot based on Longitude
    public static <T extends Comparable<T>> BST<T> insert(BST<T> tree, T elt){
        if(elt == null){
            return tree;
        }
        switch(tree){
            case null:
                return new BST<>(elt, null, null);
            case BST(T value, BST l, BST r):
                if(elt.compareTo(tree.elt) >= 0){
                    return new BST<>(tree.elt, insert(tree.left, elt), tree.right);
                } else {
                    return new BST<>(tree.elt, tree.left, insert(tree.right, elt));
                }

        }
    }

    // Removes an elt from BST and returns BST
    public static <T extends Comparable<T>> BST<T> delete(BST<T> tree, T elt) {
        if (tree == null || elt == null){
            return tree;
        }

        int compare = elt.compareTo(tree.elt);

        if (elt.equals(tree.elt)){
            if (tree.left == null){
                return tree.right;
            } else if (tree.right == null){
                return tree.left;
            }

            T replacement = getLeft(tree.left);
            return new BST<>(
                    replacement,
                    delete(tree.left, replacement),
                    tree.right
            );
        } else if (compare >= 0){
            return new BST<>(tree.elt, delete(tree.left, elt), tree.right);
        } else {
            return new BST<>(tree.elt, tree.left, delete(tree.right, elt));
        }
    }

    // Helper method returns a branch to the left node before it's left is null
    public static <T extends Comparable<T>> T getLeft(BST<T> tree){
        while(tree.left != null){
            tree = tree.left;
        }
        return tree.elt;
    }

    // Searches the BST and returns
    public static <T extends Comparable<T>> T search(BST<T> tree, T elt){
        if(tree == null || elt == null){
            return null;
        }
        if(elt.equals(tree.elt)){
            return tree.elt;
        }

        int compare = elt.compareTo(tree.elt);

        if(compare >= 0){
            return search(tree.left, elt);
        } else {
            return search(tree.right, elt);
        }
    }

    // Makes tree of random spots based on given size
    public static BST<Spot> makeTree(int size){
        BST<Spot> tree = null;
        for(int i = 0; i < size; i++){
            tree = BST.insert(tree, Spot.randomSpot());
        }
        return tree;
    }

    // Uses a BST and Longitude & finds spot closest to Longitude w/o being >
    public static Spot greatestLowerBound(BST<Spot> tree, double targetLong){
        Spot holdSpot = null;

        if(tree == null){
            return null;
        }

        while(tree != null){
            double currentLong = tree.elt().longitude();

            if(currentLong == targetLong){
                return tree.elt();
            } else if(currentLong < targetLong){
                holdSpot = tree.elt();
                tree = tree.right();
            } else {
                tree = tree.left();
            }
        }
        return holdSpot;
    }

}
