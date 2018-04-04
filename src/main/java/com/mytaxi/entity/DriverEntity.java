package com.mytaxi.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import lombok.Data;
@Data
@Entity
@Table(name = "driver", uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = { "username" }))
public class DriverEntity {

	@Id
	@GeneratedValue
	@Column( name = "driver_id")
	private Long id;

	@Column(nullable = false, name = "date_created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "Username can not be null!")
	private String username;

	@Column(nullable = false)
	@NotNull(message = "Password can not be null!")
	private String password;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Embedded
	private GeoCoordinate coordinate;

	@Column(name = "date_coordinate_updated")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "online_status")
	private OnlineStatus onlineStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	private List<CarEntity> cars;

	private DriverEntity() {
	}

	public DriverEntity(String username, String password) {
		this.username = username;
		this.password = password;
		this.deleted = false;
		this.coordinate = null;
		this.dateCoordinateUpdated = null;
		this.onlineStatus = OnlineStatus.OFFLINE;
		this.cars=null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(OnlineStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public GeoCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(GeoCoordinate coordinate) {
		this.coordinate = coordinate;
		this.dateCoordinateUpdated = ZonedDateTime.now();
	}

}
