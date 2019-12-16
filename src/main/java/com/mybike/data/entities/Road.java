package com.mybike.data.entities;

import com.mybike.data.entities.base.BaseEntity;
import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "road_bikes")
public class Road extends BaseEntity {

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
