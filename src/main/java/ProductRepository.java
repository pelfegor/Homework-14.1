public class ProductRepository {

    private Product[] items = new Product[0];

    public void save(Product item) {
        Product[] tmp = new Product [items.length +1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length -1] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public Product findById(int id) {
        for(Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (id < 0) {
            throw new NegativeIdException(
                    "ID не может быть отрицательным: " + id
            );
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        if (findById(id) != null) {
            for (Product item : items) {
                if (item.getId() != id) {
                    tmp[copyToIndex] = item;
                    copyToIndex++;
                }
            }
            items = tmp;
        } else {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
    }
}
