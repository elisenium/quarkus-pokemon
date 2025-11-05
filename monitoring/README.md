# Monitoring

### Stack de monitoring avec Prometheus et Grafana

- Démarrage du monitoring avec docker compose
```shell
  docker compose -f ./monitoring/docker-compose/monitoring.yml up
```

- Utiliser host.docker.internal pour communiquer en localhost dans un container
  - http://host.docker.internal:9090/
  - prometheus:9090

---

- UI Prometheus
  - http://localhost:9090/targets?search=
- UI Grafana
  - http://localhost:3000/d/523wqxqmz/http-overview?orgId=1&refresh=30s