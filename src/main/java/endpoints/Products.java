package endpoints;

public interface Products {

	String addProducts = "/products";

	String getProducts = "/products";

	String getProductsAsPerPId = "/products/{productId}";

	String updateProductsAsPerPId = "/products/{productId}";

	String deleteProductsAsPerPId = "/products/{productId}";

	String addProductIdImage = "/products/{productId}/image";

	String getProductIdImage = "/products/{productId}/image";

	String getProductAsPerCategoryId = "/products/category/{categoryId}";

	
}
