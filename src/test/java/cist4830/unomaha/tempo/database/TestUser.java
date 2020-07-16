package cist4830.unomaha.tempo.database;

import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

@JdbcTest
@ComponentScan
public class TestUser {

    private static final String ALICE_NAME = "Alice";
    private static final String ALICE_USERNAME = "alice@test.com";
    private static final String BOB_NAME = "Bob";
    private static final String BOB_USERNAME = "bob@test.com";
    private static final int ONE_User = 1;
    private static final int TWO_UserS = 2;

    private UserRepository userRepository;

    private static User alice;
    private static User bob;
/*
	@BeforeAll
	public static void setUp(){
		alice = new User();
		alice.setName(ALICE_NAME);
		alice.setUsername(ALICE_USERNAME);

		bob = new User();
		bob.setName(BOB_NAME);
		bob.setUsername(BOB_USERNAME);
		
	}

  @Test
	public void create_shouldReturnValidUser_whenAddingNewUser() {
		userRepository = new UserRepository();
		System.out.println("\n\n" + alice.toString() + "\n\n\n");
		Assertions.assertNotNull(alice);
		Assertions.assertNotNull(userRepository);
		userRepository.create(alice);
		Assertions.assertTrue(alice.getId() != null);

		Optional<User> result = userRepository.findById(alice.getId());
		Assertions.assertTrue(result != null);
		Assertions.assertTrue(alice.getName().equals(ALICE_NAME));
		Assertions.assertTrue(alice.getUsername().equals(ALICE_USERNAME));
	}*/
}
