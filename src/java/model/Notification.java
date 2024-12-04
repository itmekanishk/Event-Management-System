package com.aes.eventmanagementsystem.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private int notificationId;

        private String notificationContent;

        private boolean isRead;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
                        CascadeType.REFRESH,
                        CascadeType.DETACH }, targetEntity = User.class)
        @JoinColumn(name = "user_id", referencedColumnName = "userId")
        private User user;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
                        CascadeType.REFRESH,
                        CascadeType.DETACH }, targetEntity = Event.class)
        @JoinColumn(name = "event_id", referencedColumnName = "eventId")
        private Event event;

        @Override
        public String toString() {
                return "Notification [notificationId=" + notificationId + ", notificationContent=" + notificationContent
                                + "]";
        }
}
