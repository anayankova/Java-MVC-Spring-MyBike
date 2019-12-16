package com.mybike.data.entities;

import com.mybike.data.entities.base.BaseEntity;
import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dh_bikes")
public class Downhill extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "frame", nullable = false)
    @Enumerated(EnumType.STRING)
    private Frame frame;

    @Column(name = "fork", nullable = false)
    @Enumerated(EnumType.STRING)
    private Fork fork;

    @Column(name = "tires", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tires tires;

    @Column(name = "brakes", nullable = false)
    @Enumerated(EnumType.STRING)
    private Brakes brakes;
}
