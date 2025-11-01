# Real-Time Payment Fraud Detection (AI + Kafka + Microservices)

**Author:** Pavan Kumar Jonnabhatla — *Java | Cloud | Generative AI Architect*

## Services
- `transaction-service` — Ingests transactions & publishes to Kafka
- `feature-service` — Enrichment (Redis/DB placeholders)
- `decision-engine` — Combines ML score + rules (calls FastAPI scoring)
- `ml-scoring-service` — Python FastAPI scoring endpoint

## Run (dev)
- Java services: `mvn spring-boot:run`
- ML service: `pip install -r requirements.txt && uvicorn app:app --reload --port 8001`

## Next
- Add Kafka/MSK, Redis, PostgreSQL, EKS/Terraform as needed.
