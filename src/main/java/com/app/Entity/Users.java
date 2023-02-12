package com.app.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity // table creation
@Table(name = "users")
public class Users extends BaseEntity {

	
	public Users(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank(message = "password can not be blank") String password, String phone, String address,
			Long pincode, String city, String state, String status, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.status = status;
		this.role = role;
	}

	@NotBlank
	@Column(name = "first_name", length = 30, nullable = false)
	private String firstName;
	@NotBlank
	@Column(name = "last_name", length = 30)
	private String lastName;
	@NotBlank
	@Column(length = 30, unique = true, nullable = false)
	private String email;

	@NotBlank(message = "password can not be blank")
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 15)
	private String phone;

	@Column(length = 30)
	private String address;

	@Column(length = 30)
	private Long pincode;

	@Column(length = 30)
	private String city;

	@Column(length = 30)
	private String state;

	private String status = "Active";

	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Product> productlist;

	@OneToMany(mappedBy = "crop", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<CropInfo> cropList;

	@OneToMany(mappedBy = "couser", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<CombineUserOrder> combineOrderlist;
	

//	public List<CombineUserOrder> getCombineOrderlist() {
//		return combineOrderlist;
//	}
//
//	public void setCombineOrderlist(List<CombineUserOrder> combineOrderlist) {
//		this.combineOrderlist = combineOrderlist;
//	}
//
//	public List<CropInfo> getCropList() {
//		return cropList;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Long getPincode() {
//		return pincode;
//	}
//
//	public void setPincode(Long pincode) {
//		this.pincode = pincode;
//	}
//
//	public void setCropList(List<CropInfo> cropList) {
//		this.cropList = cropList;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public List<Product> getProductlist() {
//		return productlist;
//	}
//
//	public void setProductlist(List<Product> productlist) {
//		this.productlist = productlist;
//	}
//
//	@Override
//	public String toString() {
//		return "User ID: " + getId() + " [password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", email=" + email + ", phone=" + phone + ", role=" + role + ",city=" + city + ",state=" + state
//				+ "]";
//	}

}
