# ✅ Java Spring Boot Software Testing - Test Coverage Summary

This file documents the detailed test coverage and topics learned during the implementation of Java software testing with Spring Boot, based on Amigoscode's course.

---

## 🧪 Test Coverage Summary

| **Layer**                 | **Type**             | **Tools Used**                                                                 |
|---------------------------|----------------------|---------------------------------------------------------------------------------|
| **Controller Layer**      | Integration Test     | MockMvc, SpringBootTest                                                         |
| **Service Layer**         | Unit Test            | JUnit 5, Mockito, ArgumentCaptor                                                |
| **Repository Layer**      | Unit Test            | JUnit 5, @DataJpaTest, Spring Data JPA                                          |
| **Payment Layer**         | Integration Test     | MockMvc, SpringBootTest, JUnit 5, Mockito                                       |
| **Utility Layer**         | Unit Test            | JUnit 5, TDD, @ParameterizedTest, @CsvSource                                    |
| **Stripe API Simulation** | Unit Test (Mocked)   | @ConditionalOnProperty, MockStripeService (to avoid real Stripe API)            |
| **Static Method Handling**| Design Strategy      | Avoided static methods or wrapped in mockable services for better testability   |

---

## 📚 Topics Learned

- ✅ Unit Testing with **JUnit 5**
- ✅ Behavioral Testing with **Mockito**, **BDDMockito**
- ✅ Argument capture and verification using **ArgumentCaptor**
- ✅ Integration Testing using **SpringBootTest**, **MockMvc**
- ✅ REST Controller Testing with **MockMvc**
- ✅ Service Layer and Repository Layer Testing
- ✅ In-memory DB testing with **H2** and **@DataJpaTest**
- ✅ Conditional configuration and mocking external APIs using **@ConditionalOnProperty**
- ✅ Avoiding real Stripe API interaction using **MockStripeService**
- ✅ Test-Driven Development (**TDD**)
- ✅ Parameterized Tests using **@ParameterizedTest** and **@CsvSource**
- ✅ Mocking and stubbing dependencies effectively for isolation

---

## 🛠 Tech Stack Used

- Java 17
- Spring Boot 3.x
- JUnit 5
- Mockito
- Spring Data JPA
- Maven
- H2 Database
- MockMvc

---

🧠 _"Good tests make you feel safe to change your code."_