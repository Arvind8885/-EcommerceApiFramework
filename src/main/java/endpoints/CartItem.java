package endpoints;

public interface CartItem {

	String addProductToCart = "/cartitem";

	String getCartItemProduct = "/cartitem/{cartItemId}";

	String deleteCartItemProduct = "/cartitem/{cartItemId}";

}
