package com.isarithm.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users", schema = "auth")
public class User {
	@Id
	@Column(name = "users_id")
	@GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
	@GenericGenerator(
		name = "uuid2",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID id;

	@Column(name = "users_username", nullable = false, unique = true)
	private String username;

	@Column(name = "users_password")
	private String password;

	@Column(name = "users_salt")
	private String salt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<UserToken> userTokens = new ArrayList<>();
}
