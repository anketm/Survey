/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey.beans;

/**
 *
 * @author anket
 */
public class RestRequest {
    String id;
	String lastName;
	String firstName;
	String address;
	String city;
	String mobile;
	String mail;
	String gender; 
	String kyc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getKyc() {
		return kyc;
	}
	public void setKyc(String kyc) {
		this.kyc = kyc;
	}
	@Override
	public String toString() {
		return "{\"id:\"" + id + "\", \"lastName\":\"" + lastName + "\", \"firstName\":\"" + firstName + "\", \"address\":\"" + address
				+ "\", \"city\":\"" + city + "\", \"mobile\":\"" + mobile + "\", \"mail\":\"" + mail + "\", \"gender\":\"" + gender + "\", \"kyc\":\"" + kyc
				+ "\"}";
	}
}
