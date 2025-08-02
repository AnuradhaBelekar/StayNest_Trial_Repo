package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"images","blockoutDates","bookings","amenities"})


public class Property extends BaseEntity{
	
	@Column(name = "property_name",length = 100,nullable = false)
	private String propertyName;
	
	@Column(length = 200)
	private String description;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private boolean availablity;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,name = "property_type")
	private PropertyType propertyType;
	
	//for soft delete
	@Column(name="status",nullable = false)
	private boolean status=true;
	
	   @ManyToOne(optional = false)
	    @JoinColumn(name = "owner_id", nullable = false)
	    private User owner;
	
	@OneToMany(mappedBy = "property",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<PropertyImage> images=new ArrayList<>();
	
	@OneToMany(mappedBy = "property",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<PropertyBlockout> blockoutDates=new ArrayList<>();
	
	@OneToMany(mappedBy = "property",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Booking> bookings=new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(
	    name = "property_amenities",
	    joinColumns = @JoinColumn(name = "property_id"),
	    inverseJoinColumns = @JoinColumn(name = "amenity_id")
	)
	private List<Amenities> amenities = new ArrayList<>();
	
	
	@OneToOne(mappedBy = "property",cascade = CascadeType.ALL,orphanRemoval = true)
	private PropertyAddress propertyAddress;


	public Property(String propertyName, String description, double price, boolean availablity,
            PropertyType propertyType, PropertyAddress propertyAddress, boolean status, User owner) {
super();
this.propertyName = propertyName;
this.description = description;
this.price = price;
this.availablity = availablity;
this.propertyType = propertyType;
this.propertyAddress = propertyAddress;
this.status = status;
this.owner = owner;
}
	
	
}
