import java.util.ArrayList;

public class ProductTree {
    private TreeNode root;

    public ProductTree() {
        this.root = null;
    }

    public void insertProduct(Product product) {
        TreeNode newNode = new TreeNode(product);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode current = root;
        TreeNode parent = null;

        while (current != null) {
            parent = current;
            if (product.getId() < current.product.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (product.getId() < parent.product.getId()) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public Product searchProduct(int productId) {
        TreeNode current = root;

        while (current != null) {
            if (productId == current.product.getId()) {
                return current.product;
            } else if (productId < current.product.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void deleteProduct(int productId) {
        TreeNode current = root;
        TreeNode parent = null;

        while (current != null && current.product.getId() != productId) {
            parent = current;
            if (productId < current.product.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return;
        }

        if (current.left == null || current.right == null) {
            TreeNode child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            TreeNode successorParent = current;
            TreeNode successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.product = successor.product;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
    }

    public ArrayList<Product> inOrderTraversal() {
        ArrayList<Product> result = new ArrayList<Product>();
        ArrayList<TreeNode> stack = new ArrayList<TreeNode>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }

            current = stack.remove(stack.size() - 1);
            result.add(current.product);
            current = current.right;
        }

        return result;
    }

    public ArrayList<Product> displayByCategory(String category) {
        ArrayList<Product> result = new ArrayList<Product>();
        ArrayList<TreeNode> stack = new ArrayList<TreeNode>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }

            current = stack.remove(stack.size() - 1);

            if (current.product.getCategory().equalsIgnoreCase(category)) {
                result.add(current.product);
            }

            current = current.right;
        }

        return result;
    }

    public ArrayList<Product> displayLowStock() {
        ArrayList<Product> result = new ArrayList<Product>();
        ArrayList<TreeNode> stack = new ArrayList<TreeNode>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }

            current = stack.remove(stack.size() - 1);

            if (current.product.lowStock()) {
                result.add(current.product);
            }

            current = current.right;
        }

        return result;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
