package com.aes.eventmanagementsystem.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.aes.eventmanagementsystem.annotation.FieldsValueMatch;
import com.aes.eventmanagementsystem.annotation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@FieldsValueMatch.List({
                @FieldsValueMatch(field = "pwd", fieldMatch = "confirmPwd", message = "Passwords do not match!"),
                @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Email do not match!")
})
public class User extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private int userId;

        @NotBlank(message = "Username must not be blank")
        @Size(min = 3, message = "Username must be atleast 3 characters")
        private String username;

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Please provide a valid email address")
        private String email;

        @NotBlank(message = "Confirm Email must not be blank")
        @Email(message = "Please provide a valid confirm email address")
        @Transient
        @JsonIgnore
        private String confirmEmail;

        @NotBlank(message = "Password must not be blank")
        @Size(min = 5, message = "Password must be atleast 5 characters")
        @PasswordValidator
        private String pwd;

        @NotBlank(message = "Confirm Password must not be blank")
        @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
        @Transient
        @JsonIgnore
        private String confirmPwd;

        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Role.class)
        @JoinTable(name = "user_role", joinColumns = {
                        @JoinColumn(name = "user_id", referencedColumnName = "userId") }, inverseJoinColumns = {
                                        @JoinColumn(name = "role_id", referencedColumnName = "roleId") })
        private Set<Role> roles = new HashSet<>();

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        List<Event> events;
}
