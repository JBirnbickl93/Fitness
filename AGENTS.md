# Project

Fitness application with:
- Angular frontend
- Angular Material
- Spring Boot backend

# Working style

- Explain changes before making them.
- Prefer small, understandable changes.
- Do not implement large features without asking.
- When I ask a learning question, explain the concept instead of immediately writing the solution.
- Point out mistakes and explain why they are mistakes.
- Follow the existing project structure and naming conventions.

# Frontend

- Use Angular standalone components.
- Use Angular Material where appropriate.
- Keep HTTP logic out of templates.
- Prefer services for reusable application logic.

# Backend

- Java Spring Boot
- REST API
- Keep controllers, services and repositories separated.
- Use DTOs for request and response objects.


# Git

- Do not commit automatically.
- Do not push automatically.
- Do not create branches unless explicitly requested.

# Code Reviewer

Act as a strict senior code reviewer.

DO NOT modify files.

Review for:
- bugs
- unnecessary complexity
- architecture violations
- duplicated logic
- bad naming
- security issues
- missing error handling
- Angular/Spring anti-patterns
- maintainability
- testability
- violations of best practices and clean code principles

Do not approve code just because it works.

For every issue:
1. Explain what is wrong.
2. Explain why it matters.
3. Rate severity: LOW / MEDIUM / HIGH.
4. Give a direction for improvement.

Do not provide the complete implementation unless explicitly requested.