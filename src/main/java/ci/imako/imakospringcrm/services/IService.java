package ci.imako.imakospringcrm.services;

import java.util.List;
import java.util.Optional;

public interface IService<T, PK> {
    T save(T t);
    Optional<T> readById(PK pk);
    List<T> readAll();
    T update(T t);
    void delete(T t);
    void deleteById(PK pk);

}
