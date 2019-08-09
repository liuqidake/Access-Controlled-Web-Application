# Access-Controlled Web Application With Spring Boot

This simple application server provides some basic features of user login logout and registration with Spring Security dependency.

## Prerequisites

* Spring boot 2.0.4 - server frame work
* Java 1.10
* Apache Maven - dependency management
* MySQL database - datasource

## Create a starter project

Using spring boot starter initializer https://start.spring.io to make a start project.

* Fill in GroupId and ArtifactId
* Dependencies required: Web, Security, Devtools, JPA, MySql, Thymeleaf

## Configure pom.xml

Add nekohtml dependency

```
<dependency>
      <groupId>net.sourceforge.nekohtml</groupId>
      <artifactId>nekohtml</artifactId>
</dependency>
```

## Create User and Role model connecting with MySQL database

A join table is made based on the id of User and role_id of Role as foreign keys

### User

```
@Entity
@Table(name="user")
public class User {
	
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name="username")
  private String username;

  @Column(name="password")
  private String password;

  @Column(name="passwordconfirm")
  private String passwordConfirm;

  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), 
          inverseJoinColumns = @JoinColumn(name="role_id"))
  private Set<Role> roles;

  @Column(name="enabled")
  private Integer enabled;
  ...
```
### Role
```
@Entity
@Table(name="role")
public class Role {
    
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="role_id")
  private Long id;

  @Column(name="name")
  private String name;
  ...
```

### Add MySQL Schema called Security

* Create User and Role tables locally (localhost:3306/Security)
* Tool: [MySQL Workbench](https://www.mysql.com/downloads/) - MySQL GUI tool

### Edit application.properties file to connect application with MySQL database

```
spring.datasource.url = jdbc:mysql://localhost:3306/Security
spring.datasource.username=root
spring.datasource.password = happy1357@
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

#hibernate configuration
spring.jpa.show-sql= true
spring.jpa.hibernate.ddl-auto= update
spring.jpa.properties.hibernate.dialet = org.hibernate.dialet.MySQL5Dialect
```

## Add repositories for User and Role 

```
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{	
	  User findByUsername(String username);
}
```
User could be found based on this method from user's username

```
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{	
    Role findByName(String name);
}
```
findByName method is used for adding authorities for user based on its role

## Add DAO Service

### UserService

```
@Override
public User findUserByUsername(String username) {
  return userRepository.findByUsername(username);
}

@Override
public void saveUser(User user) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setEnabled(1);	
  Role role = roleRepository.findByName("USER");		
  user.setRoles(new HashSet<>(Arrays.asList(role)));
  userRepository.save(user);		
}
```

BCryptPasswordEncoder is used for encrypt user password.

## User Controller 

* Get login page /login
* Get registration page /registration
* Post create new user /registration
* Get  direct to homepage after login /home

## Spring Security Configuration

* All requests are allowed to geta access to login and registration page.
* User logged in will be automatically directed to home page

```
private final String USER_QUERY = "select username, password, passwordConfirm, enabled from user r where r.username = ?";
private final String ROLE_QUERY	= "select * from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id = r.role_id";
```

* AuthenticationManagerBuilder was built based on the datatsource of Users and Roles
