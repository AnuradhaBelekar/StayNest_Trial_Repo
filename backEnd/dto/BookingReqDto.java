package com.app.dto;

import java.time.LocalDate;

import com.app.entities.BookingStatus;
import com.app.entities.PaymentStatus;
import com.app.entities.Property;
import com.app.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BookingReqDto extends BaseDto{
	
	@NotNull
	private LocalDate checkInDate;
	
	@NotNull
	private LocalDate checkOutDate;
	
	@Min(1)
	private int numberOfGuests;
	
	
	

	
}
