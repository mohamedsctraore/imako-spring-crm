package ci.imako.imakospringcrm.services;

import ci.imako.imakospringcrm.domain.User;

import java.util.Optional;

public interface IUserService extends IService<User, Long> {
    Optional<User> readByEmail(String email);
}
