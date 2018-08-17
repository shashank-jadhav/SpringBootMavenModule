

package com.shashank.phhub.base.common.enums;

public enum Errors {
	
	/**
	 * Related to Login functionality.
	 * */
	SessionExpired("Please check the documentation for details on this error."),
	AccountLocked("Please check the documentation for details on this error."),
	AccountDisabled("Please check the documentation for details on this error."),
	UsernameNotFound("Username not registered."),
	BadCredentials("Please check the documentation for details on this error."),
	UserNotExist("User not exist with supplied user name you may need to sign up first."),
	AccountIsNotVerified("Account is not verified by mail."),
	UserNotFound("User not found with supplied id."),
	WorkOrderNotFound("Work Order not found with supplied workorderid."),
	LocationCanNotBeDeleted("Can not be deleted location because users work order is active at this location."),
	AccessDenied("Only Anmin can reopen the work order"),
	/**
	 * Related to user registration
	 * */
	UserAlreadyRegistered("Please check the documentation for details on this error."),
	InvalidWorkOrderUriUploadPhoto("Please check Work order details"),
	InvalidAccessPhotoUpload("Access denied."),
	
	/**
	 *  Related to user password
	 */
	PasswordDidNotNotMatched("Your Old Password did not matched."),

	/**
	 * Related to work order
	 */
	WorkOrderCancelledAlready("You have already closed the work order request."),
	WorkOrderAlreadyCancelled("The work order has been closed already."),
	WorkOrderDeniedAlready("You have already denied the cancellation request."),
	WorkOrderCancellationSentAlready("You have already sent the cancellation request."),
	WorkOrderOpenedAlready("Work order is open already ."),
	;

	String description;

	private Errors(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String getMessage() {
		return description;
	}

}
