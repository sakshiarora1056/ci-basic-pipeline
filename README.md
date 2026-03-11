# CI Basic Pipeline

A complete CI/CD pipeline demonstration using GitHub Actions, Maven, and Docker.

## Project Structure

```
ci-basic-pipeline/
├── .github/workflows/
│   └── ci.yml              # GitHub Actions pipeline configuration
├── docker/
│   └── Dockerfile          # Docker image configuration
├── scripts/
│   ├── promote.sh          # Artifact promotion script
│   └── stop-workflow.sh    # Manual pipeline stop script
├── src/
│   ├── main/java/          # Java source code
│   └── test/java/          # Java test code
├── config/                 # Configuration files
├── Makefile               # Build automation
└── pom.xml                # Maven project configuration
```

## Build Targets

The Makefile provides the following targets:

- `make build` - Runs `mvn clean package`
- `make test` - Runs `mvn test`
- `make docker` - Builds Docker image `demo-app:latest`
- `make all` - Runs all targets: build, test, docker

## Pipeline Stages

The CI pipeline executes the following stages:

```
push (main/staging)
  ↓
build (Maven)
  ↓
test
  ↓
docker
  ↓
artifact upload
  ↓
promote (main branch only)
```

## GitHub Secrets Configuration

Add the following secrets in **Repo → Settings → Secrets → Actions** (if needed):

- `DOCKER_USER` - Docker Hub username (optional)
- `DOCKER_PASS` - Docker Hub password (optional)

These handle credentials/tokens securely.

## Running Locally

```bash
# Build the project
make build

# Run tests
make test

# Build Docker image
make docker

# Run all targets
make all
```

## Deployment

### Initial Setup

```bash
git add .
git commit -m "initial CI pipeline"
git push origin main
```

Push will trigger the pipeline automatically.

### Viewing Artifacts

Artifacts are stored in:
**GitHub → Actions → Workflow Run → Artifacts**

Example artifacts:
- `demo-app-1.0.0.jar`

## Managing Workflows

### Stop a Running Workflow

**Option 1 (UI)**:
- Go to GitHub → Actions → Running Workflow → Cancel

**Option 2 (CLI)**:
```bash
gh run cancel <run-id>
```

**Option 3 (Script)**:
```bash
bash scripts/stop-workflow.sh
```

## Scripts

### promote.sh
Promotes built artifacts to the release repository (main branch only).

### stop-workflow.sh
Manually stops the pipeline execution with exit code 1.

## Docker

The Docker image is based on OpenJDK 17 slim and runs the packaged JAR file.

Build and run manually:
```bash
docker build -t demo-app:latest docker/
docker run demo-app:latest
```

## Requirements

- Java 17
- Maven 3.x
- Docker
- Make

## License

MIT

