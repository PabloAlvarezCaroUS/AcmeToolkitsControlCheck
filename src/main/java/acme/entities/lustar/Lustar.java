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

package acme.entities.lustar;

import java.text.SimpleDateFormat;
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
public class Lustar extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(max = 100)
	protected String 			subject;
	
	@NotBlank
	@Length(max = 255)
	protected String 			summary;

	@Column(unique = true)
	@Pattern(regexp = "\\d{2,4}$")
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
	protected Money				income;
	
	@URL
	protected String			moreInfo;
	

	// Derived attributes -----------------------------------------------------
	
	public String getCode() {
		final String format = "MM-dd-yy";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String yearMonthDayDate = simpleDateFormat.format(this.creationMoment);
		String[] dateSplit = yearMonthDayDate.split("-");
		String year = dateSplit[2];
		String month = dateSplit[0];
		String day = dateSplit[1];
		return year + "-" + month + day + this.pattern;
	}

	// Relationships ----------------------------------------------------------
	

}
