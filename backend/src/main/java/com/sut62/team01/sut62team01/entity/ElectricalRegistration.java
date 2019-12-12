package com.SUTDominatory.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "ElectricalRegistration")
public class ElectricalRegistration{
    @Id
    @SequenceGenerator(name = "ElectricalRegistration_seq", sequenceName = "ElectricalRegistration_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ElectricalRegistration_seq")
    @Column(name = "ElectricalRegistration_ID", unique = true, nullable = true,insertable = true)    
    private @NotNull long id;
    private @NotNull Date electicalRegistrationdate;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    @JsonManagedReference
    private @NotNull RoomBooking roomBooking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Type Type;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Staff Staff;

    public ElectricalRegistration(){}
    public ElectricalRegistration(RoomBooking rb, Type t, Staff st){
        this.roomBooking = rb;
        this.Type = t;
        this.Staff = st;
        this.electicalRegistrationdate = new Date();
    }
}