public class ProductManager {

    private ProductRepository repo;

    public ProductManager(ProductRepository repo) {
        this.repo = repo;
    }

    public void add(Product item) {
        repo.save(item);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];

        for (Product product: repo.findAll()) {
            if (matches(product, text)) {
                int copyToIndex = 0;
                Product[] resultNew = new Product[result.length + 1];
                for (Product prod : result){
                    resultNew[copyToIndex] = prod;
                    copyToIndex++;
                }
                resultNew[resultNew.length-1] = product;
                result = resultNew;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
