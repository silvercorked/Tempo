package cist4830.unomaha.tempo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import cist4830.unomaha.tempo.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	List<User> findByName(String name);

	List<User> findByUsername(String username);
}