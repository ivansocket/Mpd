package products;

import suppliers.Supplier;

public class Promo implements Product {

    private double oldprice;
    private double newprice;
    private double discount;
    private Product product;
    private Supplier supplier;

    public Promo(Product product, double discount) {

        newprice = product.getPrice() * (discount / 100);
        this.product = product;
        this.discount = discount;

    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getPrice() {
        return newprice;
    }

    @Override
    public int getPriceInCentimes() {
        return (int) (getPrice() * 100);
    }

    @Override
    public ProdType getType() { return product.getType(); }                         // get the product type ???????????????????

    @Override
    public double setPrice(double newprice) {
        return this.oldprice = newprice;
    }           // set the product price returning the old price

    @Override
    public Supplier getSupplier() {
        return supplier;
    }                        // get the supplier of the product

    @Override
    public void setSupplier(Supplier s) {
        this.supplier = s;
    }                       // set the supplier of the product




    @Override
    public int compareTo(Product p) {
        int res = this.getName().compareToIgnoreCase(p.getName());
        return res != 0 ? res
                : getPriceInCentimes() - p.getPriceInCentimes();
    }

}