package com.shashank.phhub.hib.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user", catalog = "mybootapp")
@FilterDef(name = "filterDeleted", parameters = @ParamDef(name = "suppliedIsDeleted", type = "boolean"))
@Filter(name = "filterDeleted", condition = ":suppliedIsDeleted = is_deleted")
@Getter
@Setter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 4642864878613368990L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password_hash")
	private String passwordHash;

	@Column(name = "pass_change_datetime")
	private LocalDateTime passwordChangeDatetime;

	@Column(name = "pass_change_millis_since_epoch")
	private Long passwordChangeMillis;

	@Column(name = "pass_change_timezone")
	private String passwordChangeTimezone;

	@Column(name = "role")
	private String role;

	@Column(name = "account_status")
	private String accountStatus;

	@Column(name = "auth_provider")
	private String authProvider;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "position_in_company")
	private String positionInCompany;

	@Column(name = "is_profile_updated")
	private boolean profileUpdated;

	@Column(name = "is_banned")
	private boolean banned;

	@Column(name = "ban_reason")
	private String banReason;

	@Column(name = "banned_datetime")
	private LocalDateTime bannedDatetime;

	@Column(name = "banned_timezone")
	private String bannedTimezone;

	@Column(name = "banned_millis_since_epoch")
	private Long bannedMillis;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "banning_admin_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private User banningAdmin;

	@Column(name = "banning_admin_user_id")
	private Long banningAdminUserId;

	@CreationTimestamp
	@Column(name = "create_timestamp")
	private LocalDateTime createTimestamp;

	@UpdateTimestamp
	@Column(name = "update_timestamp")
	private LocalDateTime updateTimestamp;

	@Column(name = "is_deleted")
	private boolean deleted;

	public User(String username, String fullName) {
		super();
		this.username = username;
		this.fullName = fullName;
	}

	public User() {
		super();
	}
	
	
	
	
	

}
