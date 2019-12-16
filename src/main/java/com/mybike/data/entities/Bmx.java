package com.mybike.data.entities;

import com.mybike.data.entities.base.BaseEntity;
import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bmx_bikes")
public class Bmx extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "frame", nullable = false)
    @Enumerated(EnumType.STRING)
    private Frame frame;

    @Column(name = "tires", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tires tires;

}
