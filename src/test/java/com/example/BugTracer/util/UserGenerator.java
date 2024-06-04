package com.example.BugTracer.util;

import com.example.BugTracer.dto.UserDTO;
import com.sb.factorium.FakerGenerator;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class UserGenerator extends FakerGenerator<UserDTO> {
  private final Faker faker = new Faker();
  @Override
  protected UserDTO make() {
    UserDTO userDTO = new UserDTO();
    String username = faker.name().username();
    String password = faker.expression("#{examplify 'abcdeskjdnf123456'}");
    String email = faker.expression("#{examplify 'abcsbdnfk'}") + "@mail.com";

    userDTO.setPassword(password);
    userDTO.setUsername(username);
    userDTO.setEmail(email);

    return userDTO;
  }
}
