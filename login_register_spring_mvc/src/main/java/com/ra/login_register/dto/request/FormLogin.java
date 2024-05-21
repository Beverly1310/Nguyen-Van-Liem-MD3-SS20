package com.ra.login_register.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormLogin {
	private String username;
	private String password;
}
