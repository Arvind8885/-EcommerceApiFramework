package endpoints;

public interface Order {

	String userPlaceOrder = "/order/user/{userId}";

	String usergetOrderAsPerCustomerId = "/order/user/{custId}";

	String userDeleteOrderAsUserId = "/order/user/{userId}";

	String userCancelOrderAsPerOrderId = "/order/user/cancel/{orderId}";

	String adminGetOrder = "/order/admin";

	String adminUpdateOrder = "/order/admin";

}
