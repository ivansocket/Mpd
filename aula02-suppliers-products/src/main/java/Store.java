import products.Product;

import suppliers.Supplier;


import java.util.*;

/**
 * A container for suppliers and it's products
 */
public class Store {
    private List<Product> catalog;
    private Supplier[] suppliers;

    private final static Product[] emptyProducts = new Product[0];
    private final static Supplier[] emptySuppliers = new Supplier[0];

    public Store(Supplier... suppliers) {
        this.suppliers = suppliers;
        catalog = new LinkedList<>();
        catalog_second = new LinkedList<>();

        for (Supplier s : suppliers) {
            for (Product p : s.getProducts()) catalog.add(p);
            for (Product p : s.getProducts()) catalog_second.add(p);
        }


    }


    private List<Product> catalog_second;

    // queries from store

    public Product[] getAllElectronics() {
        List<Product> prods = new ArrayList<>();
        for (Product p : catalog) {
            if (p.getType() == Product.ProdType.ELECTRONIC) prods.add(p);
        }
        return prods.toArray(emptyProducts);
    }

    public Supplier[] getAllSuppliers() {
        Map<Supplier, Boolean> suppliersMap = new HashMap<>();
        for (Product p : catalog) {
            Supplier s = p.getSupplier();
            suppliersMap.computeIfAbsent(s, sup -> true);
        }
        for (Supplier s : suppliers) {
            suppliersMap.computeIfAbsent(s, sup -> true);
        }
        return suppliersMap.keySet().toArray(emptySuppliers);
    }

    public Product[] getAllProducts() {
        return catalog.toArray(emptyProducts);
    }

    public enum ProdType {FOOD, DRINK, DRUGSTORE, ELECTRONIC}



/*
    public void findMax (Product p, Map hm) {


            Product.ProdType tipoProd = p.getType();

            hm.put(tipoProd, p);                                             //Coloca o primeiro produto daquele tipo no mapa
            System.out.println(tipoProd);
            System.out.println(catalog_second.size());

            if (p.getType() == tipoProd && p.getPrice() > highestPrice) {        //Caso os produtos que encontre sejam do mesmo tipo faz a comparação
                highestPrice = p.getPrice();

                //hm.remove(tipoProd, p);                                          //Remove o mais barato e coloca o mais caro ou mantém em caso inverso
                hm.put(tipoProd, p);
                catalog_second.remove(p);                                       //Remove todos os produtos do tipo para o qual o mapa já foi criado não estragando o catalog original

            } else
                catalog_second.remove(p);



        return;
    }*/



    public Map<Product.ProdType, Product> mostExpensiveByType() {

        Map<Product.ProdType, Product> hm = new HashMap<>();      //FOOD, DRINK, DRUGSTORE, ELECTRONIC

        for (Product p : catalog_second) {

            if(!hm.containsKey(p.getType())){                       //Caso o mapa ainda não contenha aquele tipo de produto coloca-o no mapa
                hm.put(p.getType(),p);
            }

            if(p.getPrice()>hm.get(p.getType()).getPrice()){        //Caso o preço do produto seja superior ao que se encontrava no mapa do mesmo tipo, substitui

                hm.replace(p.getType(),p);
            }

        }
        return hm;
    }


}






