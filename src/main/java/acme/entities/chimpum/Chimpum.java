/*
 * Chirp.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.chimpum;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(max = 100)
	protected String 			title;

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	protected String			pattern;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;
	
	//period: at least starts one month after creating and lasts for one week
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				finishDate;
	
	@Valid
	@NotNull
	protected Money				budget;
	
	@URL
	protected String			link;
	

	// Derived attributes -----------------------------------------------------
	
	public String getCode() {
		String date = String.valueOf(this.creationMoment);
		//valor en string: 2022-08-15 12:03
		String[] splitDate = date.split(" ");
		String yearMonthDayDate = splitDate[0];
		return this.pattern + "-" + yearMonthDayDate;
	}

	// Relationships ----------------------------------------------------------
	

}
