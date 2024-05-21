package com.ra.login_register.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormRegister {
	private String fullName;
	private String username;
	private String password;
}
