# Quick Start Guide

## Option 1: Docker (Recommended)

### Prerequisites
- Docker and Docker Compose installed

### Steps
1. Clone the repository
2. Run:
   \`\`\`bash
   docker-compose up
   \`\`\`
3. Open browser: `http://localhost:3000`
4. Login with demo credentials:
   - Username: `student1`
   - Password: `password123`

## Option 2: Local Development

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.8+
- Oracle Database (or use H2 for testing)

### Backend Setup

1. Update database config in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
   spring.datasource.username=study_user
   spring.datasource.password=study_password
   \`\`\`

2. Run database schema:
   \`\`\`bash
   sqlplus study_user/study_password@ORCL < db/schema_oracle.sql
   \`\`\`

3. Start backend:
   \`\`\`bash
   mvn spring-boot:run
   \`\`\`
   Backend runs on: `http://localhost:8080`

### Frontend Setup

1. Install dependencies:
   \`\`\`bash
   npm install
   \`\`\`

2. Start frontend:
   \`\`\`bash
   npm run dev
   \`\`\`
   Frontend runs on: `http://localhost:3000`

### Automated Setup (Linux/Mac)
\`\`\`bash
chmod +x scripts/setup-local.sh
./scripts/setup-local.sh
\`\`\`

### Automated Setup (Windows)
\`\`\`cmd
scripts/setup-local.bat
\`\`\`

## Demo Credentials
- **Username:** student1
- **Password:** password123
- **Role:** student

## Troubleshooting

### Backend won't start
- Check if port 8080 is available: `lsof -i :8080`
- Verify Oracle is running
- Check Java version: `java -version`

### Frontend can't connect to backend
- Ensure backend is running on port 8080
- Check browser console for CORS errors
- Verify `http://localhost:8080/api/materials` is accessible

### Database connection issues
- For Docker: Wait for Oracle to be healthy (check logs)
- For local: Verify Oracle credentials and connection string
- For H2: No setup needed, it's in-memory

## API Documentation

### Authentication
- `POST /api/auth/login` - Login
- `POST /api/auth/register` - Register

### Materials
- `GET /api/materials` - List all materials
- `POST /api/materials` - Create material (Faculty)
- `GET /api/materials/{id}` - Get material details
- `PUT /api/materials/{id}` - Update material
- `DELETE /api/materials/{id}` - Delete material

### Events
- `GET /api/events` - List all events
- `POST /api/events` - Create event
- `GET /api/events/{id}` - Get event details

## Project Structure

\`\`\`
project-root/
├── app/                          # Next.js app directory
│   ├── login/                    # Login page
│   ├── register/                 # Registration page
│   ├── dashboard/                # Main dashboard
│   └── layout.tsx                # Root layout
├── components/                   # React components
│   ├── ui/                       # shadcn/ui components
│   ├── navbar.tsx                # Navigation bar
│   ├── materials-list.tsx        # Materials list component
│   └── events-list.tsx           # Events list component
├── lib/                          # Utilities
│   ├── api.ts                    # API client functions
│   └── utils.ts                  # Helper functions
├── src/main/java/                # Spring Boot backend
│   └── com/example/studymaterial/
│       ├── controller/           # REST controllers
│       ├── service/              # Business logic
│       ├── dao/                  # Data access layer
│       ├── model/                # Entity models
│       └── config/               # Configuration
├── db/                           # Database scripts
│   ├── schema_oracle.sql         # Database schema
│   └── sample_queries.sql        # Sample queries
├── docker-compose.yml            # Docker configuration
└── README.md                     # Documentation
\`\`\`

## Next Steps

1. Customize the application for your needs
2. Add more features (file uploads, notifications, etc.)
3. Deploy to production (Vercel for frontend, AWS/Azure for backend)
4. Set up CI/CD pipeline
5. Add comprehensive testing

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review API documentation
3. Check backend logs: `docker logs <container-id>`
4. Check frontend console: Browser DevTools
