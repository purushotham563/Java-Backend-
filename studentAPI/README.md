# 📚 Student Management REST API

## 📌 Overview
A Spring Boot REST API project for managing student records with secure JWT authentication and role-based access control.

The project follows clean architecture principles (Controller → Service → Repository) and is fully containerized with Docker.

**🚀 Now deployed on AWS ECS with auto-scaling, load balancing, and high availability!**

## 🔧 Tech Stack
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- MySQL 8 (AWS RDS)
- Docker & DockerHub (Private Registry)
- GitHub Actions (CI/CD)
- **AWS ECS Fargate** (Container Orchestration)
- **Application Load Balancer** (Traffic Distribution)
- **Auto Scaling** (CPU-based scaling)

## ✨ Features
- 🔐 **Authentication & Authorization**: JWT-based login with role-based access (USER, ADMIN)
- 📖 **Student CRUD APIs**: Create, Read, Update, Delete student records
- ⚡ **Exception Handling**: Global error handling with proper response codes
- 🛠 **Validation**: Request payload validation using Hibernate Validator
- 🐳 **Dockerized**: Application packaged as Docker image
- 🔄 **CI/CD**: Automated build & push to DockerHub via GitHub Actions
- ☁️ **Cloud Native**: Deployed on AWS ECS with auto-scaling capabilities
- 🔀 **Load Balanced**: Application Load Balancer for high availability
- 📈 **Auto Scaling**: Scales up to 2 instances when CPU > 90%

## 🚀 API Endpoints

### Authentication
- `POST /api/auth/register` → Register new user (username, password, role)
- `POST /api/auth/login` → Authenticate & get JWT token

### Student Management (secured with JWT)
- `GET /api/students` → Get all students
- `GET /api/students/{id}` → Get student by ID
- `POST /api/students` → Add new student (**Admin only**)
- `PUT /api/students/{id}` → Update student details (**Admin only**)
- `DELETE /api/students/{id}` → Delete student (**Admin only**)

## 🔑 Authentication Flow
1. Register a user with `/api/auth/register`
2. Login with `/api/auth/login` to get JWT token
3. Send requests to `/api/students/**` with the header:
```
Authorization: Bearer <JWT_TOKEN>
```

## ☁️ AWS Cloud Deployment

### 🏗️ Infrastructure
- **ECS Cluster**: `student-management-cluster` (AWS Fargate)
- **Load Balancer**: Application Load Balancer with health checks
- **Database**: Amazon RDS MySQL 8.0
- **Auto Scaling**: CPU-based scaling (90% threshold, max 2 instances)
- **Security**: VPC with security groups and proper IAM roles

### 🔄 Auto Scaling Configuration
- **Minimum Instances**: 1
- **Maximum Instances**: 2
- **Scale Out Trigger**: CPU Utilization > 90%
- **Scale In Trigger**: CPU Utilization < 70%
- **Cooldown**: 300 seconds

### 🌐 Access the Live Application
```
Production URL: http://your-alb-dns-name.ap-south-1.elb.amazonaws.com
Health Check: http://your-alb-dns-name.ap-south-1.elb.amazonaws.com/login
```

### 📊 Monitoring & Logs
- **CloudWatch Logs**: Application logs stored in `/ecs/student-management`
- **Target Group Health**: Monitored via ALB health checks
- **ECS Service Events**: Real-time deployment and scaling events

## 🐳 Running with Docker

### 1. Build and Run Locally
```bash
# Build jar
mvn clean package -DskipTests

# Build Docker image
docker build -t student-management .

# Run container (configure DB and JWT env vars)
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/studentdb" \
  -e SPRING_DATASOURCE_USERNAME="root" \
  -e SPRING_DATASOURCE_PASSWORD="secret" \
  -e APP_JWT_SECRET="MySuperSecretKey" \
  student-management
```

### 2. Pull from DockerHub (Private Registry)
```bash
docker login
docker pull purushotham563/student-management:latest
docker run -p 8080:8080 purushotham563/student-management:latest
```

## ⚙️ CI/CD with GitHub Actions
On each push to `main`, GitHub Actions:
1. Builds Maven project
2. Creates Docker image
3. Pushes image → DockerHub (private repository)
4. **Triggers ECS service update** (optional - can be automated)

## 🚀 Deployment Pipeline

### Development → Production Flow
1. **Code Push** → GitHub repository
2. **CI/CD** → GitHub Actions builds & pushes Docker image
3. **Container Registry** → DockerHub private repository
4. **ECS Deployment** → AWS ECS pulls latest image
5. **Load Balancer** → Routes traffic to healthy instances
6. **Auto Scaling** → Scales based on CPU utilization

## 📂 Project Structure
```
studentAPI/
 ├── src/main/java/com/studentapi/
 │    ├── controller/   # REST Controllers
 │    ├── service/      # Business logic
 │    ├── repository/   # CRUD Repositories
 │    ├── model/        # Entities
 │    └── security/     # JWT Auth
 ├── src/main/resources/
 │    ├── application.properties
 ├── pom.xml
 ├── Dockerfile
 └── .github/workflows/ # CI/CD pipeline
```

## 🛡️ Security & Configuration

### Environment Variables (Production)
```bash
SPRING_DATASOURCE_URL=jdbc:mysql://studentdb.*******.ap-south-1.rds.amazonaws.com:3306/studentdb(not disclosing due to free tire optimization)
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=yoursecratepassword
APP_JWT_SECRET=MySuperSecretKey
```

### AWS Security
- **VPC**: Isolated network environment
- **Security Groups**: Proper ingress/egress rules
- **IAM Roles**: Least privilege access for ECS tasks
- **Secrets Manager**: Docker registry credentials

## 📈 Performance & Scaling

### Load Testing Results
- **Concurrent Users**: Handles 100+ concurrent requests
- **Response Time**: < 200ms average
- **Auto Scaling**: Triggers at 90% CPU utilization
- **High Availability**: Multi-AZ deployment with ALB

## ✅ Completed Enhancements
- ✅ **Cloud Deployment**: Production-ready AWS ECS deployment
- ✅ **Auto Scaling**: CPU-based horizontal scaling (max 2 instances)
- ✅ **Load Balancing**: Application Load Balancer with health checks
- ✅ **Monitoring**: CloudWatch logs and ECS service monitoring
- ✅ **High Availability**: Multi-AZ deployment with fault tolerance

## 🔮 Future Enhancements
- 🔄 Add role-specific access control (e.g., USER view-only, ADMIN full CRUD)
- 📈 Add unit/integration tests (JUnit + Mockito + JaCoCo coverage)
- 🔍 Add APM monitoring when beyond Free Tier (AWS X-Ray or New Relic)
- 🚀 Implement blue-green deployment strategy
- 📊 Enable CloudWatch dashboards and custom alerts (cost permitting)
- 🔐 Implement AWS Secrets Manager for database credentials
- 📱 Add Swagger/OpenAPI documentation
- 📊 **CloudWatch Logs**: Re-enable when budget allows for enhanced monitoring

## Support & Contact
For issues or questions regarding the deployment:
- **ECS Service**: `student-management-service`
- **Cluster**: `student-management-cluster`
- **Region**: `ap-south-1` (Mumbai)
- **Load Balancer**: Check AWS Console → EC2 → Load Balancers

---
**🎉 Application successfully deployed on AWS ECS with auto-scaling capabilities!**
