public record BST<T extends Comparable<T>>(T elt, BST<T> left, BST<T> right){

    public static <T extends Comparable<T>> BST<T> insert(BST<T> tree, T elt){
        switch(tree){
            case null:
                return new BST<>(elt, null, null);
            case BST(T value, BST l, BST r):
                if(elt.compareTo(tree.elt) <= 0){
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
}
