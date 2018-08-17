

package com.shashank.phhub.base.common.enums;

public enum Results {

	/**
	 * Related to Login functionality.
	 */
	LoginSuccess("User has been granted a session."),
	UserRegisteredSuccessfully("User Registered sucessfully."),
	ProfileFetchSuccess("User will get the details of his/her profile."),
	UserUpdatedSuccessfully("User profile updated successfully"),
	PasswordChangedSuccessfully("Password has been changed."),
	PasswordResetSuccessfully("Password reset sucessfully password is sent to user's registered email id."),
	WorkOrderListFetchSuccess("User will get the list of work order data."),
	WorkOrderSaveSuccessfully("Work order saved successfully."),
	UserLocationSavedSuccessfully("New user location saved successfully"),
	UserLocationUpdatedSuccessfully("User location updated successfully."),
	UserLocationDeletedSuccessfully("User location deleted successfully"),
	WorkOrderUpdatedSuccessfully("Work Order details updated."),
	WorkOrderFetchedSuccessfully("Work Order details fetched succesfully."),
	WorkOrderListFetchedSuccessfully("Work order list fetched successfully."),
	PhantomContactsFetchedSuccessfully(" "),
	WorkOrderCancelRequested("Work Order cancellation requested successfully."),
	WorkOrderForAdminFetchedSuccessfully(" "),
	WorkOrderCancelled("Work order closed successfully."),
	WorkOrderDenied("Cancellation request has been denied."),
	WorkOrderMessageSaveSuccessfully(""),
	ConversationMessagesFetchedSuccessfully(""),
	LocationListFetchedsucessfullybyStatus(""),
	LocationTypeListAndEquipmentTypeList("List of location type and equpiment type"),
	WorkOrderDataForChatFetchSuccessfully(""),
	PieChartDataForChatFetchSuccessfully("Pie chart data"),
    WorkOrderReopened("Work order reopened successfully."),
    PositionInCompanyDataForDropDownFetchSuccessfully("Position in company data for drop down"),
    ManufacturerNameForDropDown("Manufacturer name for drop down"),
	MapAccessData("Map access data fetch successfully"),
	GetAllConfiguredData("Configured data fetch successfully."),
    ;

	String description;

	private Results(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return description;
	}

}
