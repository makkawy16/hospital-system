package com.example.hospital.data.model;

import com.google.gson.annotations.SerializedName;

public class Data{
	private String birthday;
	private String address;
	private String gender;
	private String mobile;
	private boolean verified;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("created_at")
	private String createdAt;/*created_at*/
	private String type;
	private String tokenType;
	private String accessToken;
	private String specialist;
	private int id;
	@SerializedName("first_name")
	private String firstName;
	private String email;
	private String status;

	public String getBirthday(){
		return birthday;
	}

	public String getAddress(){
		return address;
	}

	public String getGender(){
		return gender;
	}

	public String getMobile(){
		return mobile;
	}

	public boolean isVerified(){
		return verified;
	}

	public String getLastName(){
		return lastName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getType(){
		return type;
	}

	public String getTokenType(){
		return tokenType;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public String getSpecialist(){
		return specialist;
	}

	public int getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}

	public String getStatus(){
		return status;
	}
}
