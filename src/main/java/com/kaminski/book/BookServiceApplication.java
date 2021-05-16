package com.kaminski.book;

import com.kaminski.book.entity.Category;
import com.kaminski.book.entity.Profile;
import com.kaminski.book.entity.UserPlatform;
import com.kaminski.book.service.BookService;
import com.kaminski.book.service.CategoryService;
import com.kaminski.book.service.ProfileService;
import com.kaminski.book.service.UserPlatformService;
import com.kaminski.book.utils.HashGenerate;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}

@Component
@AllArgsConstructor
class CommandLiner implements CommandLineRunner {

	private JdbcTemplate jdbcTemplate;
	private HashGenerate hashGenerate;
	private ProfileService profileService;
	private UserPlatformService userPlatformService;

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("DELETE FROM tbl_profiles");
		jdbcTemplate.execute("DELETE FROM tbl_users");
		jdbcTemplate.execute("DELETE FROM tbl_users_profiles");
		jdbcTemplate.execute("DELETE FROM tbl_books");
		jdbcTemplate.execute("DELETE FROM tbl_categories");
		jdbcTemplate.execute("INSERT INTO tbl_categories(id, name) VALUES (1, 'ACAO')");
		jdbcTemplate.execute("INSERT INTO tbl_categories(id, name) VALUES (2, 'AVENTURA')");
		jdbcTemplate.execute("INSERT INTO tbl_profiles(id, name) VALUES (1, 'ADMIN')");

		UserPlatform userPlatform = UserPlatform.builder()
				.name("admin")
				.username("admin")
				.password(hashGenerate.generateBCrypt("admin"))
				.profiles(Arrays.asList(profileService.getProfile(1)))
				.build();
		userPlatformService.create(userPlatform);

	}
}
