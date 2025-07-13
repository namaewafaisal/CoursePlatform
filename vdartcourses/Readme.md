# VDart Course Platform 

## This is the official documentation of Vdart course Webapp
### 3 Levels of Access
- ADMIN |
- FACULTY |
- USER   |

---

## API Endpoints (Backend)

### User APIs (`/api`)
- `POST /auth/signup` – Register user (params: username, email, password, domain)
- `POST /auth/login` – Login (params: username, password)
- `POST /auth/signup/post` – Register user (body: User JSON)
- `GET /users/{id}` – Get user by ID
- `GET /users/search?username=...` – Search users by username
- `GET /users/all` – List all users
- `PUT /users/update/{id}` – Update user (body: User JSON)
- `DELETE /users/delete/{id}` – Delete user

### Course APIs (`/api/courses`)
- `GET /all` – List all courses
- `GET /{id}` – Get course by ID
- `POST /add` – Add course (body: Course JSON)
- `GET /delete/{id}` – Delete course
- `GET /search/{keyword}` – Search courses
- `PUT /{id}` – Update course (body: Course JSON)
- `GET /coursekey/{courseKey}` - Search a course using courseKey
- `GET /{coursekey}/{subtopic}` – Get subtopic video URL Coursekey-oop, subtopic inheritance
- `PUT /update/{courseTitle}` – Update course by title (body: Course JSON)

### Quiz APIs (`/api/quizzes`)
- `GET /` – List all quizzes
- `GET /{id}` – Get quiz by ID
- `POST /post` – Add quiz (body: Quiz JSON)
- `GET /delete/{id}` – Delete quiz

### Certificate Template APIs (`/api/certificate-templates`)
- `GET /all` – List all certificate templates
- `GET /{courseId}` – Get template by course ID
- `GET /id/{id}` – Get template by template ID
- `POST /post` – Add template (body: CertificateTemplate JSON)
- `PUT /update/{id}` – Update template (body: CertificateTemplate JSON)

---

_See backend code for request/response details._

