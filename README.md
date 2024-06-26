## Start with
 
- `springboot` version ` 3.3.0 `
- application name : ai-jeju
- Language : java - 21
- Type : Gradle-Groovy
- Packagaing : Jar
- JDK : openJDK version 21.0.3

## Dependencies

  //JWT
	implementation 'io.jsonwebtoken:jjwt:0.9.1'
	implementation 'javax.xml.bind:jaxb-api:2.3.1'
	
 // JPA
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-authorization-server'
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
	
 //스프링 시큐리티를 사용하기 위한 스타터 추가
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.session:spring-session-core'
	
 //thymeleaf
	implementation'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'

  //webconfig
	implementation 'org.springframework.boot:spring-boot-starter-webflux'
	
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.mysql:mysql-connector-j'

	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'



 #application_properties

spring.application.name=ai-jeju
spring.jpa.open-in-view=false

# MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# DB Source URL
spring.datasource.url=jdbc:mysql://localhost:3306/ai_jeju?serverTimezone=UTC
# DB username
spring.datasource.username=root
# DB password
spring.datasource.password=root

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect


#jwt
jwt.issuer=rmfls4359@gmail.com
jwt.secret_key= study-springboot
react.app.url=http://localhost:3000
 

 
