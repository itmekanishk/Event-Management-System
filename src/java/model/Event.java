package com.aes.eventmanagementsystem.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.aes.eventmanagementsystem.validations.FutureDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private int eventId;

        @NotBlank(message = "Event Name must not be blank")
        private String eventName;

        @DateTimeFormat(pattern = "dd.MM.yyyy")
        @FutureDate
        private LocalDate eventDate;

        @NotBlank(message = "Event Location must not be blank")
        private String eventLocation;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
                        CascadeType.DETACH }, targetEntity = User.class)
        @JoinColumn(name = "user_id", referencedColumnName = "userId")
        private User user;

        @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
                        CascadeType.DETACH }, targetEntity = User.class)
        @JoinTable(name = "event_user", joinColumns = {
                        @JoinColumn(name = "event_id", referencedColumnName = "eventId") }, inverseJoinColumns = {
                                        @JoinColumn(name = "user_id", referencedColumnName = "userId") })
        List<User> participants;
}
