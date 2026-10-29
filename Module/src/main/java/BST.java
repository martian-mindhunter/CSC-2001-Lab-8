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
                    System.out.println(tree.elt);
                    System.out.println("left");
                    return new BST<>(tree.elt, insert(tree.left, elt), tree.right);
                } else {
                    System.out.println(tree.elt);
                    System.out.println(tree.left);
                    System.out.println("right");
                    return new BST<>(tree.elt, tree.left, insert(tree.right, elt));
                }

        }
    }

    // Removes an elt from BST
    public static <T extends Comparable<T>> void delete(BST<T> tree, T elt){

    }
}
